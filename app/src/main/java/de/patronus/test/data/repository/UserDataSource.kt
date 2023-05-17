package de.patronus.test.data.repository

import de.patronus.test.data.models.UserEntity

interface UserDataSource {
    suspend fun getUsers(): List<UserEntity>
    suspend fun getUserDetails(userId: String): UserEntity
}