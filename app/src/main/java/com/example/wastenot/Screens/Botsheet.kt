package com.example.wastenot.Screens

import Profile
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.OnboardingScreen
import com.example.dairy
import com.example.testing.fruit


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Botsheet() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route
            var selectedIcon by remember { mutableStateOf(Icons.Default.Home) }

            if (currentRoute == "home" || currentRoute == "retailer" || currentRoute == "profile") {
                TopAppBar(
                    title = { Text("WasteNot") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF199712),
                        titleContentColor = Color.White
                    ),
                    actions = {
                        IconButton(
                            onClick = {
                                selectedIcon = Icons.Default.Person
                                navController.navigate("profile") {
                                    popUpTo("home") { inclusive = false }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = if (selectedIcon == Icons.Default.Person) Color.White else Color.Gray
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            if (currentRoute == "home" || currentRoute == "retailer" ) {
                BottomAppBar(containerColor = Color(0xFF199712)) {
                    val bottomBarItems = listOf(
                        Triple(Icons.Default.Home, "Home", "home"),
                        Triple(Icons.Default.ShoppingCart, "Retailer", "retailer"),
                        Triple(Icons.Default.Person, "Profile", "profile")
                    )

                    bottomBarItems.forEach { (icon, description, route) ->
                        IconButton(
                            onClick = {
                                navController.navigate(route) {
                                    popUpTo("home") { inclusive = false }
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = description,
                                tint = if (currentRoute == route) Color.White else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "onboarding",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("onboarding") {
                OnboardingScreen {
                    navController.navigate("login")
                }
            }
            composable("login") {
                LoginScreen {
                    navController.navigate("home")
                }
            }
            composable("home") {
                Home()
            }
            composable("retailer") {
                Inventory { category ->
                    when (category) {
                        "Fruits" -> navController.navigate("itemselect")
                        "Dairy Products" -> navController.navigate("dairy")
                        "Bakery Items" -> navController.navigate("bakeryinvent")
                        "Packaged Items" -> navController.navigate("packagedite")
                        else -> navController.navigate("itemselect") // Default
                    }
                }
            }
            composable("profile") { Profile(navController) }

            // Dynamic destination screens
            composable("itemselect") {
                fruit()
            }
            composable("dairy") {
                dairy() // Dairy composable in dairy.kt
            }
            composable("bakeryinvent") {
                bakery() // Bakery composable in bakeryinvent.kt
            }
            composable("packagedite") {
                packaged() // Packaged composable in packagedit.kt
            }
        }
    }
}

