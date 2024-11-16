package com.example.wasn

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image as Image




@Composable

fun Inventory() {

    val imageList = listOf(
        imageItem(R.drawable.fruits1, "Fruits"),
        imageItem(R.drawable.dairy, "Dairy Products"),
        imageItem(R.drawable.bakery, "Bakery Items"),
        imageItem(R.drawable.meatandpoultry, "Meat & Poultry"),
        imageItem(R.drawable.packaged, "Packaged Items"),
        imageItem(R.drawable.seafood, "SeaFood"),
        imageItem(R.drawable.vegetables,"Vegetables"),
        imageItem(R.drawable.img_7,"Frozen Food")

    )


    // Animate the rotation angle
    val rotationAngle by animateFloatAsState(
        targetValue = 360f, // Rotate to 15 degrees
        animationSpec = repeatable(
            iterations = Int.MAX_VALUE, // Infinite loop
            animation = tween(
                durationMillis = 3000, // Duration for one full cycle (half wave)
                easing = LinearEasing // Smooth, continuous easing
            )
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 80.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {

                    Text(
                        "HELLO,",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Row {
                        Text(
                            "RETAILER",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF199712),
                            fontSize = 40.sp
                        )
                        Image(
                            painter = painterResource(id = R.drawable.img_6),
                            contentDescription = "hello", contentScale = ContentScale.Fit,

                            modifier=Modifier
                                .graphicsLayer(
                                    rotationZ = rotationAngle)


                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Number of columns
                modifier = Modifier
                    .fillMaxSize()

                    .padding(8.dp),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(imageList) { item ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {}

                            .border(1.dp, Color(0x80199712), RoundedCornerShape(8.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0x80199712)
                        ),
                        // Card takes full width of the grid cell
                        // Elevation for a shadow effect
                        // Rounded corners
                    ) {
                        Image(
                            painter = painterResource(id = item.imageId),
                            contentDescription ="image", contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(160.dp)// Image takes full width of the card
                                .height(80.dp)   // You can set a fixed height if needed
                                .padding(8.dp)   // Padding inside the image
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






