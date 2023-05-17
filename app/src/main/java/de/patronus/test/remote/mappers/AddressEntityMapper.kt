package de.patronus.test.remote.mappers

import de.patronus.test.data.models.AddressEntity
import de.patronus.test.remote.models.AddressModel
import javax.inject.Inject

class AddressEntityMapper @Inject constructor() : EntityMapper<AddressModel?, AddressEntity> {
    override fun mapFromModel(model: AddressModel?): AddressEntity {
        return AddressEntity(model?.street, model?.city, model?.state, model?.zip, model?.country)
    }
}