package de.patronus.test.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.patronus.test.data.UserRepositoryImpl
import de.patronus.test.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideUserRepository(userRepository: UserRepositoryImpl) : UserRepository = userRepository

}