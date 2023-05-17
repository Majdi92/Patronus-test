package de.patronus.test.data.source

import de.patronus.test.data.models.UserEntity
import de.patronus.test.data.repository.UserDataSource
import de.patronus.test.data.repository.UserRemote
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userRemote: UserRemote) :
    UserDataSource {
    override suspend fun getUsers(): List<UserEntity> {
        return userRemote.getUsers()
    }

    override suspend fun getUserDetails(userId: String): UserEntity {
        return userRemote.getUserDetails(userId)
    }

}