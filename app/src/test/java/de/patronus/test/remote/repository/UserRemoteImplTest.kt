package de.patronus.test.remote.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import de.patronus.test.remote.api.UserService
import de.patronus.test.remote.fakes.FakeRemoteData
import de.patronus.test.remote.mappers.UserEntityMapper
import de.patronus.test.remote.utils.RemoteBaseTest
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
class UserRemoteImplTest  : RemoteBaseTest(){

    @Mock
    lateinit var characterService: UserService

    @Mock
    lateinit var mapper: UserEntityMapper

    lateinit var sut: UserRemoteImpl

    val userId = "1"


    @Before
    fun setUp() {
        sut = UserRemoteImpl(characterService, mapper)
    }

    @Test
    fun `get users should return response with list size 7 from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = FakeRemoteData.getResponse(7)
            Mockito.`when`(characterService.getUsers()) doReturn response

            // Act (When)
            val users = sut.getUsers()

            // Assert (Then)
            TestCase.assertEquals(users.size, 7)
            verify(mapper, Mockito.times(7)).mapFromModel(any())
        }

    @Test
    fun `get users should return error from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(characterService.getUsers()) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getUsers() }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(characterService, Mockito.times(1)).getUsers()
        }

    @Test
    fun `get character should return response from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = FakeRemoteData.getUserModel(false)
            Mockito.`when`(characterService.getUserDetail(userId)) doReturn response
            Mockito.`when`(mapper.mapFromModel(response)) doReturn FakeRemoteData.getUserEntity(false)
            // Act (When)
            val character = sut.getUserDetails(userId)

            // Assert (Then)
            TestCase.assertEquals(character.id, "1")
            TestCase.assertTrue(character.firstName.isNotEmpty())
            verify(characterService, Mockito.times(1)).getUserDetail(userId)
            verify(mapper, Mockito.times(1)).mapFromModel(any())
        }

    @Test
    fun `get character should return error response from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(characterService.getUserDetail(userId)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getUserDetails(userId) }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(characterService, Mockito.times(1)).getUserDetail(userId)
        }


}