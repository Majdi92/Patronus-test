package de.patronus.test.presentation.util

import de.patronus.test.presentation.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestContextProvider : CoroutineContextProvider {
    val test = TestCoroutineDispatcher()

    override val io: CoroutineDispatcher = test

    override val default: CoroutineDispatcher = test

    override val main: CoroutineDispatcher = test
}