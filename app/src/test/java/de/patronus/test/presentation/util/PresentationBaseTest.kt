package de.patronus.test.presentation.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class PresentationBaseTest {

    @get:Rule
    open val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher = TestContextProvider()
}