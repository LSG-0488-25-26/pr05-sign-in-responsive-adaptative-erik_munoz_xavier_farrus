package com.example.responsiveadaptive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.responsiveadaptive.ui.theme.ResponsiveAdaptiveTheme
import com.example.responsiveadaptive.view.LoginScreen
import com.example.responsiveadaptive.view.RegisterScreen
import com.example.responsiveadaptive.view.RegisterSuccessScreen
import com.example.responsiveadaptive.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    private val vm: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ResponsiveAdaptiveTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()
                    val windowSize = calculateWindowSizeClass(this)

                    NavHost(
                        navController = navController,
                        startDestination = Routes.Register.route
                    ) {
                        composable(Routes.Register.route) {
                            RegisterScreen(
                                navController = navController,
                                vm = vm,
                                windowSize = windowSize
                            )
                        }
                        composable(Routes.RegisterSuccess.route) {
                            RegisterSuccessScreen(navController)
                        }
                        composable(Routes.Login.route) {
                            LoginScreen(
                                navController = navController,
                                vm = vm,
                                windowSize = windowSize
                            )
                        }
                    }
                }
            }
        }
    }
}
