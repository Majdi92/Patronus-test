package de.patronus.test.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressModel(
    @SerializedName("street")
    var street: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("state")
    var state: String? = null,
    @SerializedName("zip")
    var zip: String? = null,
    @SerializedName("country")
    var country: String? = null
) : Serializable