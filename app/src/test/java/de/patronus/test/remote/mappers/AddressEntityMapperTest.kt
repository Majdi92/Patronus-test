package de.patronus.test.remote.mappers

import de.patronus.test.data.models.AddressEntity
import de.patronus.test.remote.fakes.FakeRemoteData
import de.patronus.test.remote.utils.RemoteBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddressEntityMapperTest  : RemoteBaseTest(){

    lateinit var sut: AddressEntityMapper

    @Before
    fun setUp() {
        sut = AddressEntityMapper()
    }

    @Test
    fun `map model to entity should return converted object`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val addressModel = FakeRemoteData.getUserModel(false).address

            // Act (When)
            val addressEntity = sut.mapFromModel(addressModel)

            // Assert (Then)
            MatcherAssert.assertThat(
                addressEntity,
                CoreMatchers.instanceOf(AddressEntity::class.java)
            )
        }


}