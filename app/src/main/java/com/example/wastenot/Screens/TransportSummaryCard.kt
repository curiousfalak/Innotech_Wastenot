package com.example.wastenot.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wastenot.R

@Composable
fun TransportSummaryCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Adjust the padding as needed
            .background(Color.White) // Optional: To mimic the background outside the card
    )



    {
        Spacer(modifier= Modifier.height(100.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp), // Rounded corners
            colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color.White),
            elevation = androidx.compose.material3.CardDefaults.elevatedCardElevation(4.dp) // Use elevatedCardElevation in Material 3
        )
        {
            Column(
                modifier = Modifier.padding(16.dp) // Padding inside the card
            ) {
                Text("Transport summary", style = MaterialTheme.typography.titleLarge,fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))

                // Example items with images from drawable
                listOf(
                    R.drawable.apple to "Apples - Quantity: 50",
                    R.drawable.banana to "Banana - Quantity: 100",
                    R.drawable.watermelon to "Watermelon - Quantity: 20"
                ).forEach { (drawableRes, item) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = drawableRes),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = item, style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Pickup details", style = MaterialTheme.typography.titleMedium)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Account Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 8.dp)
                    )
                    Column {
                        Text("Location: 493 Main St")
                        Text("Date: March 1, 2023")
                        Text("Time: 2:45 PM")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Donation details", style = MaterialTheme.typography.titleMedium)
                Text("Total value: ₹1000.00")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Schedule Pickup */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50) // Replace with your desired hex code
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Connect to NGO", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
