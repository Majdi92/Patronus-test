package de.patronus.test.remote.repository

import de.patronus.test.data.models.UserEntity
import de.patronus.test.data.repository.UserRemote
import de.patronus.test.remote.api.UserService
import de.patronus.test.remote.mappers.UserEntityMapper
import javax.inject.Inject

class UserRemoteImpl @Inject constructor(
    private val userService: UserService,
    private val userMapper: UserEntityMapper
) : UserRemote {
    override suspend fun getUsers(): List<UserEntity> {
        return userService.getUsers().users.map {
            userModel -> userMapper.mapFromModel(userModel)
        }
    }

    override suspend fun getUserDetails(userId: String): UserEntity {
        return userMapper.mapFromModel(userService.getUserDetail(userId))
    }
}