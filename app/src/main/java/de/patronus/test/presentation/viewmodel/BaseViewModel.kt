package de.patronus.test.presentation.viewmodel

import androidx.lifecycle.ViewModel
import de.patronus.test.presentation.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(val contextProvider: CoroutineContextProvider) : ViewModel() {
    private val job: Job = Job()
    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.io + job + coroutineExceptionHandler) {
            block()
        }
    }

    public override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}