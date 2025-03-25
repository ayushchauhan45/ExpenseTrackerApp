package com.example.expensetrackerapp

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerapp.expense_feature.presentation.constant.Screens
import com.example.expensetrackerapp.expense_feature.presentation.transaction.AddTransactionScreen
import com.example.expensetrackerapp.expense_feature.presentation.ui.theme.ExpenseTrackerAppTheme
import com.example.expensetrackerapp.expense_feature.presentation.user.BottomNavigationBar
import com.example.expensetrackerapp.expense_feature.presentation.user.UserScreen
import com.example.expensetrackerapp.expense_feature.presentation.user.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerAppTheme {

                    val navController = rememberNavController()
                    val startDestination = if (userExists()) Screens.UserScreen.route else Screens.WelcomeScreen.route
                    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route
                if (currentScreen == Screens.WelcomeScreen.route || currentScreen == Screens.AddTransactionScreen.route) {
                    NavHost(navController = navController, startDestination = startDestination) {
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
                        composable(route = Screens.AddTransactionScreen.route) {
                            AddTransactionScreen(navController = navController)
                        }

                    }
                }else{
                    Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate(Screens.AddTransactionScreen.route){
                                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                shape = CircleShape,
                                containerColor = Color(0xFFB7D9A4), // Light Green
                                elevation = FloatingActionButtonDefaults.elevation(6.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Black)
                            }
                        },
                        bottomBar = { BottomNavigationBar(navController) },
                        floatingActionButtonPosition = FabPosition.Center
                    ) {paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = Screens.UserScreen.route,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable(route = Screens.UserScreen.route) {
                                UserScreen(navController = navController)
                            }
                            composable(route = Screens.AllTransactionsScreen.route) {

                            }
                        }

                    }
                }

            }
        }
    }
    private fun userExists(): Boolean {
        return sharedPreferences.contains("user_id") && sharedPreferences.getInt("user_id", -1) != -1
    }
}




