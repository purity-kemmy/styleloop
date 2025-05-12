package com.example.styleloop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.styleloop.navigation.AppNavHost
import com.example.styleloop.navigation.Routes
import com.example.styleloop.ui.theme.StyleloopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StyleloopTheme {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    startDestination = Routes.SPLASH // ✅ Start with Splash Screen
                )
            }
        }
    }
}
