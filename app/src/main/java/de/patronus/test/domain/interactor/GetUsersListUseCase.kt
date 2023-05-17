package de.patronus.test.domain.interactor

import de.patronus.test.domain.models.User
import de.patronus.test.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetUsersListBaseUseCase = BaseUseCase<Unit, Flow<List<User>>>

class GetUsersListUseCase @Inject constructor(private val userRepository: UserRepository) :
    GetUsersListBaseUseCase {
    override suspend fun invoke(params: Unit) = userRepository.getUsers()

}