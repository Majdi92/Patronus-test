package de.patronus.test.data

import de.patronus.test.data.mapper.UserMapper
import de.patronus.test.data.repository.UserRemote
import de.patronus.test.data.source.UserDataSourceFactory
import de.patronus.test.domain.models.User
import de.patronus.test.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSourceFactory: UserDataSourceFactory,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun getUsers(): Flow<List<User>> = flow {
        val usersList = dataSourceFactory.getRemoteDataSource().getUsers().map { userEntity ->
            userMapper.mapFromEntity(userEntity)
        }
        emit(usersList)
    }

    override suspend fun getUserDetails(userId: String): Flow<User> = flow {
        val user = dataSourceFactory.getRemoteDataSource().getUserDetails(userId)
        emit(userMapper.mapFromEntity(user))
    }
}