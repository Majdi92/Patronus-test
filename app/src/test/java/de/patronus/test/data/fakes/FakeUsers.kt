package de.patronus.test.data.fakes

import de.patronus.test.data.models.AddressEntity
import de.patronus.test.data.models.UserEntity
import de.patronus.test.domain.models.Address
import de.patronus.test.domain.models.User

object FakeUsers {
    fun getUsers(): List<UserEntity> = listOf(
        UserEntity(
            "1",
            0.0,
            0.0,
            "John",
            "Doe",
            null,
            "Male",
            "+4912345678",
            AddressEntity("FooStreet, 4", "FooCity", "FooState", "0000", "FakeCountry"),
            "https://dummyurl.png"
        ),
        UserEntity(
            "2",
            1.1,
            1.1,
            "Jane",
            "Doe",
            null,
            "Female",
            "+4912345678",
            AddressEntity("FooStreet, 9", "FooCity", "FooState", "0000", "FakeCountry"),
            "https://dummyurl.png"
        )
    )

    fun getUser(): User {
        return User(
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

    }
}