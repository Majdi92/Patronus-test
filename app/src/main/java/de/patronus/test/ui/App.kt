package de.patronus.test.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import de.patronus.test.presentation.utils.PresentationPreferencesHelper
import de.patronus.test.ui.core.theme.ThemeUtils
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var themeUtils: ThemeUtils

    @Inject
    lateinit var preferencesHelper: PresentationPreferencesHelper

    override fun onCreate() {
        super.onCreate()
        initNightMode()
    }

    private fun initNightMode() {
        themeUtils.setNightMode(preferencesHelper.isNightMode)
    }

}