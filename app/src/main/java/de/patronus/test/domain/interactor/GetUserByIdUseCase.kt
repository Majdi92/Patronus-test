package de.patronus.test.domain.interactor

import de.patronus.test.domain.models.User
import de.patronus.test.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetUserByIdBaseUseCase = BaseUseCase<String, Flow<User>>

class GetUserByIdUseCase @Inject constructor(private val userRepository: UserRepository) :
    GetUserByIdBaseUseCase {
    override suspend fun invoke(params: String) = userRepository.getUserDetails(params)
}