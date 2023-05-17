package de.patronus.test.data.repository

import de.patronus.test.data.models.UserEntity

interface UserRemote {
    suspend fun getUsers(): List<UserEntity>
    suspend fun getUserDetails(userId: String): UserEntity
}