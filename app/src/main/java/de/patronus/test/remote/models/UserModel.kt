package de.patronus.test.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class UserModel(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("currentLatitude")
    var currentLatitude: Double? = null,

    @SerializedName("currentLongitude")
    var currentLongitude: Double? = null,

    @SerializedName("firstName")
    var firstName: String? = null,

    @SerializedName("lastName")
    var lastName: String? = null,

    @SerializedName("stickers")
    var stickers: List<String>? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("phoneNumber")
    var phoneNumber: String? = null,

    @SerializedName("address")
    var address: AddressModel? = null,

    @SerializedName("imageUrl")
    var image: String? = null
) : Serializable
