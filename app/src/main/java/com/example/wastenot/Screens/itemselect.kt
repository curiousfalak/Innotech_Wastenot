package com.example.wastenot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun fruit(onSaveChanges: () -> Unit) {
    val items = listOf(
        InventoryItem("Banana", "Stored in 14°C, 90% RH, Plant Hormones: 10 ppm", R.drawable.banana, 40, 5),
        InventoryItem("Apple", "Stored in 4°C, 90% RH, Plant Hormones: 30 ppm", R.drawable.apple, 120, 30),
        InventoryItem("Orange", "Stored in 8°C, 90% RH, Plant Hormones: 20 ppm", R.drawable.orange, 60, 20),
        InventoryItem("Grapes", "Stored in 2°C, 90% RH, Plant Hormones: 10 ppm", R.drawable.graphes, 150, 15),
        InventoryItem("Mango", "Stored in 12°C, 90% RH, Plant Hormones: 15 ppm", R.drawable.mango, 100, 10),
        InventoryItem("Strawberry", "Stored in 0°C, 95% RH, Plant Hormones: 5 ppm", R.drawable.stawberry, 200, 3),
        InventoryItem("Pineapple", "Stored in 7°C, 90% RH, Plant Hormones: 20 ppm", R.drawable.pineapple, 80, 12),
        InventoryItem("Watermelon", "Stored in 10°C, 85% RH, Plant Hormones: 10 ppm", R.drawable.watermelon, 50, 7),
        InventoryItem("Kiwi", "Stored in 0°C, 95% RH, Plant Hormones: 5 ppm", R.drawable.kiwi, 300, 25),
        InventoryItem("Peach", "Stored in 1°C, 90% RH, Plant Hormones: 10 ppm", R.drawable.peach, 180, 14)
    )

    val quantities = remember { mutableStateMapOf<String, Int>() }

    items.forEach { item ->
        if (!quantities.containsKey(item.name)) quantities[item.name] = 0
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text(
                text = "Inventory",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /* Search Action */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(items.size) { index ->
                val item = items[index]
                InventoryItemRow(
                    item = item,
                    quantity = quantities[item.name] ?: 0,
                    onIncrease = { quantities[item.name] = (quantities[item.name] ?: 0) + 1 },
                    onDecrease = {
                        val currentQty = quantities[item.name] ?: 0
                        if (currentQty > 0) quantities[item.name] = currentQty - 1
                    }
                )
                if (index < items.size - 1) {
                    Divider(color = Color.Gray, thickness = 0.5.dp, modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }

        // Save Changes Button
        Button(
            onClick = onSaveChanges ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green Color
        ) {
            Text(text = "Save Changes", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun InventoryItemRow(
    item: InventoryItem,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp) // Increased size
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = item.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = item.description, fontSize = 12.sp, color = Color.Gray)
            Text(text = "₹${item.price}", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Text(text = "Expires in ${item.expiryDays} days", fontSize = 12.sp, color = Color.Red)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onDecrease,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Decrease",
                    tint = Color.Black
                )
            }

            Text(
                text = quantity.toString(),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            IconButton(
                onClick = onIncrease,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Increase",
                    tint = Color.Black
                )
            }
        }
    }
}

data class InventoryItem(
    val name: String,
    val description: String,
    val imageRes: Int,
    val price: Int,
    val expiryDays: Int
)
