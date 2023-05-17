package de.patronus.test.presentation.fakes

import java.util.UUID
import kotlin.random.Random

object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

}