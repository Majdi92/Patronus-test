package de.patronus.test.domain.models

data class Address(
    var street: String? = null,
    var city: String? = null,
    var state: String? = null,
    var zip: String? = null,
    var country: String? = null
)
{
    override fun toString(): String {
        return "$street, $zip $city"
    }
}