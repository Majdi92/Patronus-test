package de.patronus.test.remote.mappers

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import de.patronus.test.data.models.UserEntity
import de.patronus.test.remote.fakes.FakeRemoteData
import de.patronus.test.remote.utils.RemoteBaseTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserEntityMapperTest : RemoteBaseTest(){

    @Mock
    lateinit var mapper: AddressEntityMapper

    lateinit var sut: UserEntityMapper

    @Before
    fun setUp() {
        sut = UserEntityMapper(mapper)
    }

    @Test
    fun `map model to entity should return converted object`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val userModel = FakeRemoteData.getUserModel(false)
            Mockito.`when`(mapper.mapFromModel(userModel.address)) doReturn FakeRemoteData.getUserEntity(
                false
            ).address
            // Act (When)
            val character = sut.mapFromModel(userModel)

            // Assert (Then)
            MatcherAssert.assertThat(
                character,
                CoreMatchers.instanceOf(UserEntity::class.java)
            )
            TestCase.assertEquals(character.id, "1")
            TestCase.assertTrue(character.firstName.isNotEmpty())
            verify(mapper).mapFromModel(any())
        }


}