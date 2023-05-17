package de.patronus.test.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import de.patronus.test.domain.interactor.GetUsersListUseCase
import de.patronus.test.domain.models.User
import de.patronus.test.presentation.utils.CoroutineContextProvider
import de.patronus.test.presentation.utils.UiAwareLiveData
import de.patronus.test.presentation.utils.UiAwareModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

sealed class UsersListUIModel : UiAwareModel() {
    object Loading : UsersListUIModel()
    data class Error(var error: String = "") : UsersListUIModel()
    data class Success(val data: List<User>) : UsersListUIModel()
}

@HiltViewModel
class UsersListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val usersListUseCase: GetUsersListUseCase
) : BaseViewModel(contextProvider) {

    private val _usersList = UiAwareLiveData<UsersListUIModel>()
    private var usersList: LiveData<UsersListUIModel> = _usersList

    fun getUsers(): LiveData<UsersListUIModel> {
        return usersList
    }

    fun getAllUsers()
    {
        launchCoroutineIO {
            loadUsers()
        }
    }

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _usersList.postValue(UsersListUIModel.Error(exception.message ?: "Error"))
    }

    private suspend fun loadUsers()
    {
        usersListUseCase(Unit).collect {
            _usersList.postValue(UsersListUIModel.Success(it))

        }
    }

}