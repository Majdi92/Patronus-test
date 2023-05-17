package de.patronus.test.data.mapper

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import de.patronus.test.data.fakes.FakeUsers
import de.patronus.test.data.models.AddressEntity
import de.patronus.test.data.utils.DataBaseTest
import de.patronus.test.domain.models.Address
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserMapperTest: DataBaseTest() {
    @Mock
    lateinit var addressMapper: AddressMapper

    lateinit var userMapper: UserMapper

    @Before
    fun setup()
    {
        userMapper = UserMapper(addressMapper)
    }

    @Test
    fun `map  user entity to user should return converted user`() =
        dispatcher.runBlockingTest {
            val fakeUser = FakeUsers.getUser()
            Mockito.`when`(addressMapper.mapToEntity(fakeUser.address!!)) doReturn AddressEntity(
                "",
                ""
            )
            val user = userMapper.mapToEntity(fakeUser)
            TestCase.assertEquals(user.id, "1")
            TestCase.assertEquals(user.firstName, "John")
            TestCase.assertEquals(user.gender, "Male")
            verify(addressMapper, times(1)).mapToEntity(any())
        }
}