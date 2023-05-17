package de.patronus.test.data.source

import de.patronus.test.data.repository.UserDataSource
import javax.inject.Inject

open class UserDataSourceFactory @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
) {
    open suspend fun getDataStore(): UserDataSource {
        return getRemoteDataSource()
    }

    fun getRemoteDataSource(): UserDataSource {
        return remoteDataSource
    }
}