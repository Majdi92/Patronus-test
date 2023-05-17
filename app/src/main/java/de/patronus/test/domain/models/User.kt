package de.patronus.test.domain.models

data class User(
    var id:String? = null,
    var currentLatitude: Double? = null,
    var currentLongitude: Double? = null,
    var firstName: String,
    var lastName: String,
    var stickers: List<String>? = null,
    var gender: String,
    var phoneNumber: String,
    var address: Address? = null,
    var image:String? = null
)
{
    override fun toString(): String {
        return "User(id=$id, currentLatitude=$currentLatitude, currentLongitude=$currentLongitude, firstName='$firstName', lastName='$lastName', stickers=$stickers, gender='$gender', phoneNumber='$phoneNumber', address=$address, image=$image)"
    }
}


