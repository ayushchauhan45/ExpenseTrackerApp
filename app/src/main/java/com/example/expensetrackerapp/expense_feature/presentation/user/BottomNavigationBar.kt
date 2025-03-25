package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.expensetrackerapp.expense_feature.presentation.constant.Screens

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        Screens.UserScreen,
        Screens.AllTransactionsScreen
    )

    val icons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.AccountBox,
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar(containerColor = BottomAppBarDefaults.containerColor,
        contentColor = contentColorFor(BottomAppBarDefaults.containerColor),
        tonalElevation = BottomAppBarDefaults.ContainerElevation) {
        navItems.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    Icon(imageVector = icons[index], contentDescription = screen.route)
                },
                label = { Text(screen.route) }
            )
        }
    }
}