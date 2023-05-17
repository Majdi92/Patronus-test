package de.patronus.test.data.mapper

import de.patronus.test.data.models.UserEntity
import de.patronus.test.domain.models.User
import javax.inject.Inject

class UserMapper @Inject constructor(private val addressMapper: AddressMapper) :
    Mapper<UserEntity, User> {
    override fun mapFromEntity(type: UserEntity): User {
        return User(
            type.id,
            type.currentLatitude,
            type.currentLongitude,
            type.firstName,
            type.lastName,
            type.stickers,
            type.gender,
            type.phoneNumber,
            addressMapper.mapFromEntity(type.address!!),
            type.image
        )
    }

    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(
            type.id,
            type.currentLatitude,
            type.currentLongitude,
            type.firstName,
            type.lastName,
            type.stickers,
            type.gender,
            type.phoneNumber,
            addressMapper.mapToEntity(type.address!!),
            type.image
        )
    }
}