package de.patronus.test.data.models

data class UserEntity(
    var id:String? = null,
    var currentLatitude: Double? = null,
    var currentLongitude: Double? = null,
    var firstName: String,
    var lastName: String,
    var stickers: List<String>? = null,
    var gender: String,
    var phoneNumber: String,
    var address: AddressEntity? = null,
    var image:String? = null
)