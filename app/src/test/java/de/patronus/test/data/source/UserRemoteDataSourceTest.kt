package de.patronus.test.data.source

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import de.patronus.test.data.fakes.FakeUsers
import de.patronus.test.data.repository.UserRemote
import de.patronus.test.data.utils.DataBaseTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRemoteDataSourceTest : DataBaseTest() {

    @Mock
    lateinit var userRemote: UserRemote

    lateinit var sut: UserRemoteDataSource

    @Before
    fun setUp() {
        sut = UserRemoteDataSource(userRemote)
    }

    @Test
    fun `get users should return users from remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            Mockito.`when`(userRemote.getUsers()) doReturn FakeUsers.getUsers()

            // Act (When)
            val characters = sut.getUsers()

            // Assert (Then)
            TestCase.assertEquals(characters.size, 2)
            verify(userRemote, times(1)).getUsers()
        }

    @Test
    fun `get users should return error`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(userRemote.getUsers()) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getUsers() }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(userRemote, times(1)).getUsers()
        }

    @Test
    fun `get user with user-id should return users from remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val userId = "1"
            Mockito.`when`(userRemote.getUserDetails(userId)) doReturn FakeUsers.getUsers()[0]

            // Act (When)
            val characters = sut.getUserDetails(userId)

            // Assert (Then)
            TestCase.assertEquals(characters.firstName, "John")
            verify(userRemote, times(1)).getUserDetails(userId)
        }


    @Test
    fun `get character with character-id should return error`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val userId = "1"
            whenever(userRemote.getUserDetails(userId)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getUserDetails(userId) }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(userRemote, times(1)).getUserDetails(userId)
        }


}