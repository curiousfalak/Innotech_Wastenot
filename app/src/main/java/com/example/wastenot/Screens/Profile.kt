import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wastenot.ui.theme.WasteNotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WasteNotTheme   {
                ProfileScreen()

            }
        }
    }
}


@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Light beige background
            .padding(16.dp)
    ) {
        // Profile Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My Profile",
                modifier = Modifier.padding(start = 120.dp, top = 18.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,

                color = Color.Black,
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile Image and Name
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Profile Image
            Card(
                shape = CircleShape,
                modifier = Modifier.size(100.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box( // Use Box for better control
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { /* Search Action */ } // Add clickable behavior to the entire card
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Account Icon",
                        modifier = Modifier
                            .fillMaxSize(), // Ensures the Icon fills the Card
                        tint = Color.White

                    )

                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Name
            Text(
                text = "Diana",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Edit Profile Button
            Button(
                onClick = { /* Handle Edit Profile */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF5F5F5),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(50.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(2.dp)
            ) {
                Text(text = "Edit Profile", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Options Section
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileOptionRow("Wallet", onClick = { /* Handle Wallet Click */ })

            ProfileOptionRow("Support", onClick = { /* Handle Support Click */ })

            ProfileOptionRow("Payment", onClick = { /* Handle Payment Click */ })
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Information Section
        Text(
            text = "Your Information",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))





        ProfileInfoCard("Contact Details")
        Spacer(modifier = Modifier.height(10.dp))
        ProfileInfoCard("Food Saved")
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = {  }, // Navigate to login screen
            modifier = Modifier.fillMaxWidth() ,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Logout", color = Color.White)
        }

    }
}

@Composable
fun ProfileOptionRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
        IconButton(onClick = { /* Search Action */ }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Search Icon"
            )
        }
    }
}

@Composable
fun ProfileInfoCard(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}