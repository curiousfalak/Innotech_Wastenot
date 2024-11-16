package com.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transport.R

@Composable
fun OnboardingScreen(onFinished:()->Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter= painterResource(id= R.drawable.logo),

                    contentDescription = "App logo",
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = "WASTE NOT",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onFinished) {
                    Text(text = "Skip", color = Color.Gray, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(id = R.drawable.fruitbag),
                contentDescription = "Earth image",
                modifier = Modifier
                    .size(400.dp)
                    .padding(top = 50.dp, start = 8.dp)
            )

            Text(
                text = "Track",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 40.dp)
            )

            Text(
                text ="Streamlining journeys, minimizing waste—delivering a sustainable tomorrow." ,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Handle button click */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF199712)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = "Start Tracking",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}