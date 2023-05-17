package de.patronus.test.domain.repository

import de.patronus.test.domain.models.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun getUsers(): Flow<List<User>>
    suspend fun getUserDetails(userId: String): Flow<User>

}