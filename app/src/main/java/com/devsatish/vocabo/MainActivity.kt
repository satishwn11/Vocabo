package com.devsatish.vocabo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.devsatish.vocabo.screens.RootScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        window.statusBarColor = Color(0xFFF9F3F9).toArgb()
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        setContent {
            RootScreen()
        }
    }
}

