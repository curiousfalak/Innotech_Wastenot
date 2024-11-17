import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Profile(navController: NavController) {
    var areNotificationsEnabled by remember { mutableStateOf(true) } // State for toggle

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Align all child elements to the center horizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Profile Icon Section
        ProfileHeader() // Profile icon is now centered

        Spacer(modifier = Modifier.height(24.dp))

        // Details Section
        ProfileDetails()

        Spacer(modifier = Modifier.height(24.dp))

        // Preferences Section
        PreferencesColumn(areNotificationsEnabled) { newValue ->
            areNotificationsEnabled = newValue // Update the state on toggle
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Logout Button
        Button(
            onClick = { navController.navigate("login") }, // Navigate to login screen
            modifier = Modifier.fillMaxWidth() .padding(top=100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.6f))
        ) {
            Text("Logout", color = Color.White)
        }
    }
}

@Composable
fun ProfileHeader() {
    // Center the profile icon
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center // Align the content to the center
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Icon",
            modifier = Modifier
                .size(80.dp)
                .background(Color.LightGray, CircleShape), // Profile icon with background
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ProfileDetails() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally // Align details to center
    ) {
        Card(modifier = Modifier.fillMaxWidth() .height(150.dp),
                colors = CardDefaults.cardColors(
                containerColor = Color.LightGray // Light Blue
                ),
            ) {
            Column {

                Text("User Name", style = MaterialTheme.typography.titleMedium, color = Color.White)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    "Retailer / NGO Name",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    "Email: user@example.com",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }

    }
}

@Composable
fun PreferencesColumn(
    areNotificationsEnabled: Boolean,
    onNotificationToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        PreferenceItem("Push notifications", areNotificationsEnabled) { newValue ->
            onNotificationToggle(newValue)
        }


        // Add additional preferences or options here

    }
}

@Composable
fun PreferenceItem(
    title: String,
    switchChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.bodyLarge)
        Switch(
            checked = switchChecked,
            onCheckedChange = onCheckedChange
        )
    }
}
