package de.patronus.test.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.patronus.test.BuildConfig
import de.patronus.test.data.repository.UserRemote
import de.patronus.test.remote.api.ServiceFactory
import de.patronus.test.remote.api.UserService
import de.patronus.test.remote.repository.UserRemoteImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideBlogService(): UserService {
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideUserRemote(userRemote: UserRemoteImpl): UserRemote {
        return userRemote
    }
}