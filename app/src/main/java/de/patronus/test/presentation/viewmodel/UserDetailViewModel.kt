package de.patronus.test.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import de.patronus.test.domain.interactor.GetUserByIdUseCase
import de.patronus.test.domain.models.User
import de.patronus.test.presentation.utils.CoroutineContextProvider
import de.patronus.test.presentation.utils.UiAwareLiveData
import de.patronus.test.presentation.utils.UiAwareModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

sealed class UserDetailUIModel : UiAwareModel() {
    object Loading : UserDetailUIModel()
    data class Error(var error: String = "") : UserDetailUIModel()
    data class Success(val data: User) : UserDetailUIModel()
}

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val userDetailUseCase: GetUserByIdUseCase
) : BaseViewModel(contextProvider) {

    val TAG = "UserDetailViewModel"

    private val _user = UiAwareLiveData<UserDetailUIModel>()
    private var user: LiveData<UserDetailUIModel> = _user


    fun getUser(): LiveData<UserDetailUIModel> {
        return user
    }


    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _user.postValue(UserDetailUIModel.Error(exception.message ?: "Error"))
    }

    fun getUserDetail(userId: String) {
        _user.postValue(UserDetailUIModel.Loading)
        launchCoroutineIO {
            loadUser(userId)
        }
    }

    private suspend fun loadUser(userId: String) {
        userDetailUseCase(userId).collect { _user.postValue(UserDetailUIModel.Success(it)) }
    }

}