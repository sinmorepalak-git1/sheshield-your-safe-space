package com.example.sheshield.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sheshield.ui.components.BottomNavBar
import com.example.sheshield.ui.components.FakeMap
import com.example.sheshield.ui.components.ScreenHeader
import com.example.sheshield.ui.components.StatBox
import com.example.sheshield.ui.components.shadow
import com.example.sheshield.ui.theme.SOS
import com.example.sheshield.ui.viewmodels.ContactViewModel

@Composable
fun LocationScreen(navController: NavController, contactViewModel: ContactViewModel = viewModel()) {
    val contacts by contactViewModel.allContacts.collectAsState(initial = emptyList())
    
    Scaffold(
        topBar = {
            ScreenHeader(
                title = "Live Location",
                subtitle = "Sharing with ${contacts.size} contacts",
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            FakeMap()

            // Map Overlay Buttons
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(44.dp)
                        .background(MaterialTheme.colorScheme.surface, CircleShape)
                        .shadow(elevation = 4.dp, shape = CircleShape)
                ) {
                    Icon(Icons.Default.Navigation, contentDescription = "Recenter", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(44.dp)
                        .background(MaterialTheme.colorScheme.surface, CircleShape)
                        .shadow(elevation = 4.dp, shape = CircleShape)
                ) {
                    Icon(Icons.Default.Share, contentDescription = "Share", tint = MaterialTheme.colorScheme.primary)
                }
            }

            // Bottom Status Card
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    // Pull bar
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(48.dp)
                            .height(6.dp)
                            .background(MaterialTheme.colorScheme.outlineVariant, CircleShape)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            val infiniteTransition = rememberInfiniteTransition(label = "ping")
                            val pingScale by infiniteTransition.animateFloat(
                                initialValue = 1f,
                                targetValue = 2.5f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1500),
                                    repeatMode = RepeatMode.Restart
                                ), label = "pingScale"
                            )
                            val pingAlpha by infiniteTransition.animateFloat(
                                initialValue = 0.4f,
                                targetValue = 0f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1500),
                                    repeatMode = RepeatMode.Restart
                                ), label = "pingAlpha"
                            )
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .scale(pingScale)
                                    .alpha(pingAlpha)
                                    .background(Color(0xFF22C55E), CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(Color(0xFF22C55E), CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Sharing live location", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(
                                text = "MG Road, Bengaluru · Updated just now",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp
                            )
                        }
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = SOS),
                            shape = RoundedCornerShape(20.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text("Stop", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        StatBox(label = "ACCURACY", value = "6 m", modifier = Modifier.weight(1f))
                        StatBox(label = "SPEED", value = "4 km/h", modifier = Modifier.weight(1f))
                        StatBox(label = "BATTERY", value = "78%", modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.People, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (contacts.isEmpty()) "No one " else contacts.joinToString(", ") { it.name } + " ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "can see you",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
