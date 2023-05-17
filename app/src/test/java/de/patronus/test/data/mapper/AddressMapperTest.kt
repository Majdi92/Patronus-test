package de.patronus.test.data.mapper

import de.patronus.test.data.fakes.FakeUsers
import de.patronus.test.data.utils.DataBaseTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddressMapperTest : DataBaseTest() {
    lateinit var addressMapper: AddressMapper

    @Before
    fun setUp() {
        addressMapper = AddressMapper()
    }

    @Test
    fun `map  address entity to address should return converted address`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val fakeAddress = FakeUsers.getUsers()[0].address

            // Act (When)
            val sut = addressMapper.mapFromEntity(fakeAddress!!)

            // Assert (Then)
            TestCase.assertEquals(sut.city, "FooCity")
        }

    @Test
    fun `map  address to address entity should return converted address`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val fakeAddress = FakeUsers.getUser().address

            // Act (When)
            val sut = addressMapper.mapToEntity(fakeAddress!!)

            // Assert (Then)
            TestCase.assertEquals(sut.city, "FooCity")
        }



}