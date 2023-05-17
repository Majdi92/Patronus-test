package de.patronus.test.remote.mappers

import de.patronus.test.data.models.UserEntity
import de.patronus.test.remote.models.UserModel
import javax.inject.Inject

class UserEntityMapper @Inject constructor(private val addressMapper: AddressEntityMapper) :
    EntityMapper<UserModel, UserEntity> {
    override fun mapFromModel(model: UserModel): UserEntity {
        return UserEntity(
            model.id,
            model.currentLatitude,
            model.currentLongitude,
            model.firstName!!,
            model.lastName!!,
            model.stickers,
            model.gender!!,
            model.phoneNumber!!,
            addressMapper.mapFromModel(model.address),
            model.image
        )
    }
}