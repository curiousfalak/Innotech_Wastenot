package com.example.wastenot.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    // Sample initial data for food avoided and food transported
    val foodAvoidedFromWastage = listOf(20, 15, 25, 18, 30, 12, 22) // Amount of food avoided from wastage
    val foodTransportedToNgo = listOf(35, 40, 50, 45, 60, 38, 55) // Amount of food transported to NGOs

    // Combine totals for the pie chart
    val totalAvoided = foodAvoidedFromWastage.sum()
    val totalTransported = foodTransportedToNgo.sum()

    // Simulate data changes over time (for example, updating every 2 seconds)
    var avoided by remember { mutableStateOf(totalAvoided) }
    var transported by remember { mutableStateOf(totalTransported) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            avoided += (-5..5).random() // Randomly simulate more food avoided from waste
            transported += (-5..5).random() // Randomly simulate more food transported to NGOs
        }
    }

    // Calculate percentages
    val total = (avoided + transported).coerceAtLeast(1) // Avoid division by zero
    val avoidedPercentage = (avoided.toFloat() / total) * 360f
    val transportedPercentage = (transported.toFloat() / total) * 360f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Food Avoided from Wastage vs Food Transported to NGO",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Pie Chart
        Canvas(
            modifier = Modifier
                .size(250.dp)
                .padding(16.dp)
        ) {
            // Draw the "Food Avoided from Wastage" segment (Red color)
            drawArc(
                color = Color.Red,
                startAngle = -90f,
                sweepAngle = avoidedPercentage,
                useCenter = true
            )
            // Draw the "Food Transported to NGO" segment (Green color)
            drawArc(
                color = Color(0xFF86CB84),
                startAngle = -90f + avoidedPercentage,
                sweepAngle = transportedPercentage,
                useCenter = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Improved Legend
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            LegendItem(color = Color.Red, label = "Food Avoided from Wastage: ${avoided} kg")
            Spacer(modifier = Modifier.height(8.dp))
            LegendItem(color = Color(0xFF86CB84), label = "Food Transported to NGO: ${transported} kg")
        }

        Text(
            text = "This pie chart represents how much food is avoided from wastage and how much is transported to NGOs.",
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
        modifier = Modifier.padding(4.dp)
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
