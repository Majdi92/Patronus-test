package de.patronus.test.data.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class DataBaseTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher

    val exceptionHandler = TestCoroutineExceptionHandler()
}