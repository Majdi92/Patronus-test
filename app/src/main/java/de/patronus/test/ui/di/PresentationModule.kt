package de.patronus.test.ui.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.patronus.test.presentation.utils.CoroutineContextProvider
import de.patronus.test.presentation.utils.CoroutineContextProviderImp
import de.patronus.test.presentation.utils.PresentationPreferencesHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun providePresentationPreferenceHelper(@ApplicationContext context: Context): PresentationPreferencesHelper {
        return PresentationPreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideCoroutineContextProvider(contextProvider: CoroutineContextProviderImp): CoroutineContextProvider =
        contextProvider
}