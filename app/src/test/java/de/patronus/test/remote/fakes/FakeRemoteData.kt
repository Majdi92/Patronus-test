package de.patronus.test.remote.fakes

import de.patronus.test.data.models.AddressEntity
import de.patronus.test.data.models.UserEntity
import de.patronus.test.presentation.fakes.FakeValueFactory
import de.patronus.test.remote.models.AddressModel
import de.patronus.test.remote.models.UserModel
import de.patronus.test.remote.models.UserResponseModel

object FakeRemoteData {

    fun getResponse(size: Int, isRandomId: Boolean = true): UserResponseModel {
        return UserResponseModel(getFakeCharacterModel(size, isRandomId))
    }

    private fun getFakeCharacterModel(size: Int, isRandomId: Boolean): List<UserModel> {
        val characters = mutableListOf<UserModel>()
        repeat(size) {
            characters.add(getUserModel(isRandomId))
        }
        return characters
    }

    fun getUserModel(isRandomId: Boolean): UserModel {
        return UserModel(
            id = if (isRandomId) FakeValueFactory.randomString() else "1",
            currentLatitude = 0.0,
            currentLongitude = 0.0,
            firstName = FakeValueFactory.randomString(),
            lastName = FakeValueFactory.randomString(),
            null,
            gender = FakeValueFactory.randomString(),
            FakeValueFactory.randomString(),
            address = AddressModel(
                street = FakeValueFactory.randomString(),
                city = FakeValueFactory.randomString(),
                state = FakeValueFactory.randomString(),
                zip = FakeValueFactory.randomString(),
                country = FakeValueFactory.randomString()
            )
        )
    }

    fun getUserEntity(isRandomId: Boolean): UserEntity {
        return UserEntity(
            id = if (isRandomId) FakeValueFactory.randomString() else "1",
            currentLatitude = 0.0,
            currentLongitude = 0.0,
            firstName = FakeValueFactory.randomString(),
            lastName = FakeValueFactory.randomString(),
            null,
            gender = FakeValueFactory.randomString(),
            FakeValueFactory.randomString(),
            address = AddressEntity(
                street = FakeValueFactory.randomString(),
                city = FakeValueFactory.randomString(),
                state = FakeValueFactory.randomString(),
                zip = FakeValueFactory.randomString(),
                country = FakeValueFactory.randomString()
            )
        )
    }


}