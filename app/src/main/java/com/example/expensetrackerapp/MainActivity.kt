package com.example.expensetrackerapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerapp.expense_feature.presentation.constant.Screens
import com.example.expensetrackerapp.expense_feature.presentation.ui.theme.ExpenseTrackerAppTheme
import com.example.expensetrackerapp.expense_feature.presentation.user.UserScreen
import com.example.expensetrackerapp.expense_feature.presentation.user.UserViewModel
import com.example.expensetrackerapp.expense_feature.presentation.user.WelcomeScreen

class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerAppTheme {
                    val navController = rememberNavController()
                    val startDestination = if (userExists()) Screens.UserScreen.route else Screens.WelcomeScreen.route

                    NavHost(navController = navController, startDestination = startDestination){
                        composable(
                            route = Screens.WelcomeScreen.route
                        ) {
                            WelcomeScreen(
                                navController = navController
                            )
                        }
                        composable(route = Screens.UserScreen.route) {
                            UserScreen(navController = navController)
                        }
                    }

            }
        }
    }
    private fun userExists(): Boolean {
        return sharedPreferences.contains("user_id") && sharedPreferences.getInt("user_id", -1) != -1

    }
}




