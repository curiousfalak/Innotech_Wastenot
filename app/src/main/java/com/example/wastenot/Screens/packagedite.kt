package com.example.wastenot.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.example.wastenot.R


data class packagedit(
    val name: String,
    val description: String,
    val image: Int,
    val price:String,
    val expire:String,
)


@Composable
fun packaged(){
    val items = listOf(
        packagedit("Potato Chips", "Stored at 30°C,60% RH",  R.drawable.img_8,"₹70","Expiry in 7 days"),
        packagedit("Instant Noodles","Stored at 35°C,60%RH",R.drawable.img_13,"₹50","Expiry in 4 days"),
        packagedit("Popcorn", "Stored at 30°C, 50% RH", R.drawable.img_9,"₹30","Expiry in 30 days"),
        packagedit("Muffinn", "Stored at 25°C, 60% RH", R.drawable.img_10,"₹40","Expiry in 20 days"),
        packagedit("Fruit Juices", "Stored at 40°C, 70% RH", R.drawable.img_11,"₹70","Expiry in 30 days"),
        packagedit("Granola Bars", "Stored at 28°C, 50% RH", R.drawable.img_12,"₹40","Expiry in 30 days"),
        packagedit("Pretzels","Stored at 25°C, 50% RH",R.drawable.img_14,"₹30","Expiry in 40 days"),
        packagedit("Canned Soups","Stored at 25°C, 50% RH",R.drawable.img_15,"₹30","Expiry in 40 days")


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
            onClick = { /* Save Action */ },
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
    item: packagedit,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Image(
            painter = painterResource(id = item.image),
            contentDescription = item.name,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))


        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = item.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = item.description, fontSize = 12.sp, color = Color.Gray)
            Text(text = item.price, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
            Text(text = item.expire, fontSize = 12.sp, color = Color.Red)
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



