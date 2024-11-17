package com.example.wastenot.Screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastenot.R

data class ImageItem(val imageId: Int, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inventory() {
    val imageList = listOf(
        ImageItem(R.drawable.fruits1, "Fruits"),
        ImageItem(R.drawable.dairy, "Dairy Products"),
        ImageItem(R.drawable.bakery, "Bakery Items"),
        ImageItem(R.drawable.meatandpoultry, "Meat & Poultry"),
        ImageItem(R.drawable.packaged, "Packaged Items"),
        ImageItem(R.drawable.seafood, "Seafood"),
        ImageItem(R.drawable.vegetables, "Vegetables"),
        ImageItem(R.drawable.img_7, "Frozen Food")
    )

    val searchQuery = remember { mutableStateOf("") }

    val rotationAngle by animateFloatAsState(
        targetValue = 360f,
        animationSpec = repeatable(
            iterations = Int.MAX_VALUE,
            animation = tween(
                durationMillis = 5000,
                easing = LinearEasing
            )
        )
    )

    val filteredImageList = imageList.filter {
        it.description.lowercase().contains(searchQuery.value.lowercase())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "HELLO,",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "RETAILER",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF199712),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.img_6),
                            contentDescription = "hello",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .graphicsLayer(rotationZ = rotationAngle)
                                .height(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                label = { Text("Search for food items...") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    if (searchQuery.value.isNotEmpty()) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Clear Search",
                            tint = Color.Gray,
                            modifier = Modifier.clickable { searchQuery.value = "" }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                singleLine = true,
                colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE8F5E9),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    cursorColor = Color(0xFF199712),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(filteredImageList) { item ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                // Handle item click
                            }
                            .border(1.dp, Color(0x80199712), RoundedCornerShape(8.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x80199712)
                        )
                    ) {
                        Image(
                            painter = painterResource(id = item.imageId),
                            contentDescription = "image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.5f)
                                .padding(8.dp)
                        )
                        Text(
                            text = item.description,
                            modifier = Modifier.padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
