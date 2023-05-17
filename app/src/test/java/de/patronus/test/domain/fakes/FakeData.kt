package de.patronus.test.domain.fakes
import de.patronus.test.data.models.AddressEntity
import de.patronus.test.domain.models.Address
import de.patronus.test.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getUsers(): Flow<List<User>> = flow {
        val users = listOf(
            User(
                "1",
                0.0,
                0.0,
                "John",
                "Doe",
                null,
                "Male",
                "+4912345678",
                Address("FooStreet, 4", "FooCity", "FooState", "0000", "FakeCountry"),
                "https://dummyurl.png"
            ),
            User(
                "2",
                1.1,
                1.1,
                "Jane",
                "Doe",
                null,
                "Female",
                "+4912345678",
                Address("FooStreet, 9", "FooCity", "FooState", "0000", "FakeCountry"),
                "https://dummyurl.png"
            )
        )
    }

    fun getUser(): Flow<User> = flow {
        emit(
            User(
                "1",
                0.0,
                0.0,
                "John",
                "Doe",
                null,
                "Male",
                "+4912345678",
                Address("FooStreet, 4", "FooCity", "FooState", "0000", "FakeCountry"),
                "https://dummyurl.png"
            )
        )
    }
}