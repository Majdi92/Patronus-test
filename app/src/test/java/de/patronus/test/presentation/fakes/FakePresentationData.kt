package de.patronus.test.presentation.fakes

import de.patronus.test.domain.models.Address
import de.patronus.test.domain.models.User
import de.patronus.test.presentation.fakes.FakeValueFactory.randomString

object FakePresentationData {
    fun getUsers(
        size: Int,
        isRandomId: Boolean = true,
    ): List<User> {
        val users = mutableListOf<User>()
        repeat(size) {
            users.add(createUser(isRandomId))
        }
        return users
    }

    private fun createUser(isRandomId: Boolean): User {
        return User(
            id = if (isRandomId) randomString() else "1",
            currentLatitude = 0.0,
            currentLongitude = 0.0,
            firstName = randomString(),
            lastName = randomString(),
            null,
            gender = randomString(),
            randomString(),
            address = Address(
                street = randomString(),
                city = randomString(),
                state = randomString(),
                zip = randomString(),
                country = randomString()
            )
        )
    }


}