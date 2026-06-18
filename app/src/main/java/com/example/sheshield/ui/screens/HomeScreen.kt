package com.example.sheshield.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sheshield.navigation.Screen
import com.example.sheshield.ui.components.BottomNavBar
import com.example.sheshield.ui.theme.*
import com.example.sheshield.ui.viewmodels.ContactViewModel
import com.example.sheshield.ui.viewmodels.HomeViewModel

data class QuickAction(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val tint: Color,
    val iconColor: Color
)

val quickActions = listOf(
    QuickAction(Screen.Contacts.route, "Emergency Contacts", Icons.Default.People, Color(0xFFFFF1F2), Color(0xFFE11D48)),
    QuickAction(Screen.Location.route, "Live Location", Icons.Default.Place, Color(0xFFFDF4FF), Color(0xFFC026D3)),
    QuickAction(Screen.Nearby.route, "Nearby Help", Icons.Default.LocalHospital, Color(0xFFFAF5FF), Color(0xFF9333EA)),
    QuickAction(Screen.FakeCall.route, "Fake Call", Icons.Default.Phone, Color(0xFFFDF2F8), Color(0xFFDB2777)),
    QuickAction(Screen.Tips.route, "Safety Tips", Icons.Default.Book, Color(0xFFF5F3FF), Color(0xFF7C3AED)),
    QuickAction(Screen.VoiceShake.route, "Voice & Shake", Icons.Default.Mic, Color(0xFFFFF1F2), Color(0xFFE11D48))
)

@Composable
fun HomeScreen(
    navController: NavController,
    contactViewModel: ContactViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
) {
    val contacts by contactViewModel.allContacts.collectAsState(initial = emptyList())
    val userProfile by homeViewModel.userProfile.collectAsState()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            HeaderSection(
                userName = homeViewModel.getGreetingName(userProfile),
                userInitial = homeViewModel.getUserInitial(userProfile),
                contactsCount = contacts.size
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                SOSSection(navController)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                QuickActionsSection(navController)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                CheckInCard()
            }
        }
    }
}

@Composable
fun HeaderSection(userName: String, userInitial: String, contactsCount: Int) {
    val isDark = isSystemInDarkTheme()
    
    // Theme-aware gradient to ensure text readability in both modes
    val topGradientColor = if (isDark) Color(0xFF2D242D) else Color(0xFFFFF5F8)
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(topGradientColor, MaterialTheme.colorScheme.background)
                )
            )
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Good evening",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isDark) Color.LightGray.copy(alpha = 0.7f) else Color.Gray
                )
                Text(
                    text = "$userName 👋",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) Color.White else Color(0xFF1A1A1A)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFF9A8D4), Color(0xFFC084FC))
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userInitial,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Success.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                .border(1.dp, Success.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Success, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                val statusText = if (contactsCount > 0) "You're in a safe zone" else "Safety setup incomplete"
                Text(
                    text = statusText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "$contactsCount contacts watching · Location ON",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun SOSSection(navController: NavController) {
    val infiniteTransition = rememberInfiniteTransition(label = "sos_ripples")
    
    val rippleScale1 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "ripple1"
    )
    val rippleAlpha1 by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "alpha1"
    )

    val rippleScale2 by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing, delayMillis = 800),
            repeatMode = RepeatMode.Restart
        ), label = "ripple2"
    )
    val rippleAlpha2 by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing, delayMillis = 800),
            repeatMode = RepeatMode.Restart
        ), label = "alpha2"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "HOLD TO ACTIVATE",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Box(
            modifier = Modifier
                .size(200.dp)
                .clickable { navController.navigate(Screen.SOS.route) },
            contentAlignment = Alignment.Center
        ) {
            // Ripples
            Box(
                modifier = Modifier
                    .size(176.dp)
                    .scale(rippleScale1)
                    .alpha(rippleAlpha1)
                    .background(Color(0xFFEF4444).copy(alpha = 0.4f), CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .scale(rippleScale2)
                    .alpha(rippleAlpha2)
                    .background(Color(0xFFEF4444).copy(alpha = 0.3f), CircleShape)
            )
            
            // SOS Button
            Box(
                modifier = Modifier
                    .size(176.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFFF87171), Color(0xFFDC2626)),
                            center = androidx.compose.ui.geometry.Offset(100f, 100f)
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SOS",
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-1).sp
                    )
                    Text(
                        text = "EMERGENCY",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 3.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Text(
            text = "Tap once to start a 5-second countdown. Your trusted contacts will be alerted.",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun QuickActionsSection(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Quick actions",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        TextButton(onClick = { }) {
            Text(
                "Customize",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.height(260.dp) // Fixed height to fit in column
    ) {
        items(quickActions) { action ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(action.route) },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(action.tint, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = action.icon,
                            contentDescription = null,
                            tint = action.iconColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = action.label,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CheckInCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Daily check-in at 9:00 PM",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "We'll ping you when you reach home. Tap to adjust.",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
