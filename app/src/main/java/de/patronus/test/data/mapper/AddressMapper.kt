package de.patronus.test.data.mapper

import de.patronus.test.data.models.AddressEntity
import de.patronus.test.domain.models.Address
import javax.inject.Inject

class AddressMapper  @Inject constructor(): Mapper<AddressEntity,Address> {
    override fun mapFromEntity(type: AddressEntity): Address {
       return Address(street = type.street,city = type.city, state = type.state, zip = type.zip, country = type.country)
    }

    override fun mapToEntity(type: Address): AddressEntity {
       return AddressEntity(street = type.street,city = type.city, state = type.state, zip = type.zip, country = type.country)
    }
}