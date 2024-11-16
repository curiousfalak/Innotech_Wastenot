package com.example.wastenot

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Botsheet() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WasteNot") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF199712),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = Color(0xFF199712)) {
                val bottomBarItems = listOf(
                    Triple(Icons.Default.Home, "Home", "home")
                )

                bottomBarItems.forEach { (icon, description, route) ->
                    IconButton(
                        onClick = {
                            navController.navigate(route) {
                                popUpTo("home") { inclusive = true }
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = description,
                            tint = Color.Gray
                        )
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(onNavigateToLogin: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Onboarding") })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onNavigateToLogin) {
                Text("Get Started")
            }
        }
    }
}






