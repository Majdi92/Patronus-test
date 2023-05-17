package de.patronus.test.remote.mappers

interface EntityMapper<M, E> {
    fun mapFromModel(model:M) : E
}