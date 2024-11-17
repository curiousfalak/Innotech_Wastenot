package com.example.wastenot.Screens



import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Sliding Animated Image Carousel
        SlidingImageCarousel(
            imageResIds = listOf(
                R.drawable.basket, // Replace with your actual image resources
                R.drawable.dry,
                R.drawable.cheese,
                R.drawable.green
            )
        )

        Spacer(modifier = Modifier.height(16.dp)) // Spacing between the carousel and the card

        // Metrics Card Section
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
}

@Composable
fun SlidingImageCarousel(imageResIds: List<Int>) {
    var currentImageIndex by remember { mutableStateOf(0) }
    var isVisible by remember { mutableStateOf(true) }

    // Infinite looping logic
    LaunchedEffect(Unit) {
        while (true) {
            // Keep the current image visible for a longer time
            delay(1000) // Delay time to keep the image visible for 6 seconds
            isVisible = false // Fade out the current image
            delay(500) // Delay for 1 second before transitioning
            currentImageIndex = (currentImageIndex + 1) % imageResIds.size // Move to the next image
            isVisible = true // Fade in the new image
            delay(6000) // Keep the new image visible for 6 seconds
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
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)), // Fade-in animation
            exit = fadeOut(animationSpec = tween(durationMillis = 1000)) // Fade-out animation
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
fun MetricItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
    }
}
