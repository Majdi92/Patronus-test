package de.patronus.test.remote.api

import de.patronus.test.remote.models.UserModel
import de.patronus.test.remote.models.UserResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users")
    suspend fun getUsers(): UserResponseModel

    @GET("/users/{id}")
    suspend fun getUserDetail(@Path("id") id: String): UserModel
}