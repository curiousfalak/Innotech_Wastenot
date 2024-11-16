package com.example.wasn
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image as Image


@Composable
fun LoginScreen(onLoginSuccess: ()->Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var Profession by remember { mutableStateOf("") }
    Box( // Wrap everything in a Box for background color
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Set the desired background color here
    ) {

        Column(
            modifier = Modifier.fillMaxSize() .padding(top=100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_3),
                contentDescription = "icon",
                Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Name icon")
                },

                )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email Address") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email icon")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },

                )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = Profession,
                onValueChange = { Profession = it },
                label = { Text(text = "Profession") },

                )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {Log.i("Credentials", "Email=$email , Password=$password")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF199712),
                    contentColor = Color.White
                ),

            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Or Sign In with", fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { } )
            OutlinedButton(
                onClick = onLoginSuccess,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // Google button is usually white
                    contentColor = Color.Black
                ),
                // Optional: Adds shadow for a raised effect
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Google Icon
                    Image(
                        painter = painterResource(id = R.drawable.img_4), // Replace with your Google logo drawable
                        contentDescription = "Google Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Text
                    Text(
                        text = "Continue with Google",

                        color = Color.Black
                    )
                }
            }
        }

            }
        }






