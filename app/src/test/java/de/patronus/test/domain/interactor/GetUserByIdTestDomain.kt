package de.patronus.test.domain.interactor

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import de.patronus.test.data.fakes.FakeUsers
import de.patronus.test.data.utils.DataBaseTest
import de.patronus.test.domain.fakes.FakeData
import de.patronus.test.domain.repository.UserRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetUserByIdTestDomain : DataBaseTest() {

    @Mock
    lateinit var userRepository: UserRepository

    lateinit var sut: GetUserByIdUseCase

    @Before
    fun setup()
    {
        sut = GetUserByIdUseCase(userRepository)
    }

    @Test
    fun `get user with id should return success result with user detail`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val userId = "1"
            whenever(userRepository.getUserDetails(userId)) doReturn FakeData.getUser()

            // Act (When)
            val user = sut(userId).single()

            // Assert (Then)
            TestCase.assertEquals(user.id, userId)
            TestCase.assertEquals(user.firstName, "John")
            verify(userRepository, times(1)).getUserDetails(userId)
        }

    @Test
    fun `get user with id should return error result with exception`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val userId = "1"
            whenever(userRepository.getUserDetails(userId)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut(userId).single() }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(userRepository, times(1)).getUserDetails(userId)
        }

}