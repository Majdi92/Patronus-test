package de.patronus.test.remote.models

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("customers")
    val users: List<UserModel>
)