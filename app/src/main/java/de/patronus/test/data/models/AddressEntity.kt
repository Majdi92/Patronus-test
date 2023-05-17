package de.patronus.test.data.models

data class AddressEntity(
    var street: String? = null,
    var city: String? = null,
    var state: String? = null,
    var zip: String? = null,
    var country: String? = null
)