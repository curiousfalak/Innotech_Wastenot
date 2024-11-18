package com.example.wastenot.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wastenot.R
import kotlinx.coroutines.delay

@Composable
fun Home() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sliding Animated Image Carousel
        item {
            SlidingImageCarousel(
                imageResIds = listOf(
                    R.drawable.basket,
                    R.drawable.dry,
                    R.drawable.cheese,
                    R.drawable.green
                )
            )
        }

        // Metrics Card Section
        item {
            MetricsCard()
        }

        // Food Graph Section
        item {
            FoodGraph()
        }
    }
}

@Composable
fun SlidingImageCarousel(imageResIds: List<Int>) {
    var currentImageIndex by remember { mutableStateOf(0) }
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            isVisible = false
            delay(500)
            currentImageIndex = (currentImageIndex + 1) % imageResIds.size
            isVisible = true
            delay(6000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000))
        ) {
            Image(
                painter = painterResource(id = imageResIds[currentImageIndex]),
                contentDescription = "Sliding Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun MetricsCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFF4CAF50), // Green border color
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = Color(0xFFF8FAFC),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Track...",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )

        Text(
            text = "Reducing Food Wastage, One Journey at a Time",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MetricItem(value = "5 tons", label = "Saved")
            MetricItem(value = "20 routes", label = "Routes")
            MetricItem(value = "15%", label = "Reduced")
        }
    }
}

@Composable
fun MetricItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun FoodGraph() {
    val initialFoodWasted = listOf(20, 15, 30, 25, 10, 12, 18) // Initial values for food wasted
    val initialFoodTransported = listOf(40, 35, 50, 45, 30, 32, 38) // Initial values for food transported
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val maxValue = (initialFoodWasted + initialFoodTransported).maxOrNull() ?: 1

    // State to hold the animated data
    var foodWasted by remember { mutableStateOf(initialFoodWasted) }
    var foodTransported by remember { mutableStateOf(initialFoodTransported) }

    // Simulate data changes over time
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            foodWasted = foodWasted.map { it + (-5..5).random() }
            foodTransported = foodTransported.map { it + (-5..5).random() }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Weekly Food Stats",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(days.size) { index ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    // Animate wasted food bar height
                    val wastedHeight by animateFloatAsState(
                        targetValue = (foodWasted[index].coerceAtLeast(0).toFloat() / maxValue * 200),
                        animationSpec = tween(durationMillis = 1000)
                    )

                    Box(
                        modifier = Modifier
                            .height(wastedHeight.dp)
                            .width(20.dp)
                            .background(Color.Red)
                            .clip(RoundedCornerShape(4.dp))
                    )
                    Text(
                        text = "${foodWasted[index].coerceAtLeast(0)} kg",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Animate transported food bar height
                    val transportedHeight by animateFloatAsState(
                        targetValue = (foodTransported[index].coerceAtLeast(0).toFloat() / maxValue * 200),
                        animationSpec = tween(durationMillis = 1000)
                    )

                    Box(
                        modifier = Modifier
                            .height(transportedHeight.dp)
                            .width(20.dp)
                            .background(Color.Green)
                            .clip(RoundedCornerShape(4.dp))
                    )
                    Text(
                        text = "${foodTransported[index].coerceAtLeast(0)} kg",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Green,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = days[index],
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LegendItem(color = Color.Red, label = "Food Wasted (kg)")
            LegendItem(color = Color.Green, label = "Food Transported (kg)")
        }

        Text(
            text = "This graph shows the weekly statistics of food wastage and transportation.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun LegendItem(color: Color, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}
