package com.example.sheshield.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PhoneDisabled
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sheshield.ui.components.ScreenHeader
import com.example.sheshield.ui.theme.GradientPrimaryEnd
import com.example.sheshield.ui.theme.GradientPrimaryStart
import com.example.sheshield.ui.theme.SOS

data class Caller(val name: String, val sub: String, val startColor: Color, val endColor: Color)

val callers = listOf(
    Caller("Dad", "Mobile", Color(0xFFFDA4AF), Color(0xFFF472B6)),
    Caller("Boss", "Work", Color(0xFFC4B5FD), Color(0xFFA855F7)),
    Caller("Mom", "Home", Color(0xFFF0ABFC), Color(0xFFF472B6)),
    Caller("Unknown", "Private", Color(0xFFCBD5E1), Color(0xFF64748B))
)

val delays = listOf("5 sec", "30 sec", "1 min", "5 min")

@Composable
fun FakeCallScreen(navController: NavController) {
    var selectedCaller by remember { mutableIntStateOf(0) }
    var selectedDelay by remember { mutableIntStateOf(0) }
    var isPreviewing by remember { mutableStateOf(false) }

    if (isPreviewing) {
        IncomingCallPreview(
            caller = callers[selectedCaller],
            onEnd = { isPreviewing = false }
        )
    } else {
        Scaffold(
            topBar = {
                ScreenHeader(
                    title = "Fake Call",
                    subtitle = "Stage an escape route",
                    onBackClick = { navController.popBackStack() }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp)
            ) {
                Text(
                    text = "CALLER",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(140.dp)
                ) {
                    itemsIndexed(callers) { index, caller ->
                        val isSelected = selectedCaller == index
                        Card(
                            onClick = { selectedCaller = index },
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.05f) 
                                               else MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary 
                                        else MaterialTheme.colorScheme.outlineVariant
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            brush = Brush.linearGradient(listOf(caller.startColor, caller.endColor)),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = caller.name.take(1), color = Color.White, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(text = caller.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Text(text = caller.sub, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "DELAY",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    delays.forEachIndexed { index, delay ->
                        val isSelected = selectedDelay == index
                        Surface(
                            onClick = { selectedDelay = index },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(
                                1.dp, 
                                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                            ),
                            color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
                        ) {
                            Text(
                                text = delay,
                                modifier = Modifier.padding(vertical = 12.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { isPreviewing = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(listOf(GradientPrimaryStart, GradientPrimaryEnd)),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Schedule fake call", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "The call will look real with vibration & ringtone. Tap to preview it now.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun IncomingCallPreview(caller: Caller, onEnd: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "call_ripples")
    val rippleScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 2.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Restart
        ), label = "ripple"
    )
    val rippleAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Restart
        ), label = "alpha"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFF9FF), Color(0xFFFCE4EC))
                )
            )
            .padding(horizontal = 24.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "INCOMING CALL",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 4.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(144.dp)
                        .scale(rippleScale)
                        .alpha(rippleAlpha)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(144.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(caller.startColor, caller.endColor)),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = caller.name.take(1), color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(text = caller.name, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.ExtraBold)
            Text(text = "mobile · India", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape)
                    .shadow(4.dp, CircleShape)
            ) {
                Icon(Icons.Default.Message, contentDescription = "Message")
            }
            
            IconButton(
                onClick = onEnd,
                modifier = Modifier
                    .size(64.dp)
                    .background(SOS, CircleShape)
                    .shadow(8.dp, CircleShape)
            ) {
                Icon(Icons.Default.PhoneDisabled, contentDescription = "Decline", tint = Color.White, modifier = Modifier.size(32.dp))
            }

            IconButton(
                onClick = onEnd,
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFF4CAF50), CircleShape)
                    .shadow(8.dp, CircleShape)
            ) {
                Icon(Icons.Default.Phone, contentDescription = "Accept", tint = Color.White, modifier = Modifier.size(32.dp))
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape)
                    .shadow(4.dp, CircleShape)
            ) {
                Icon(Icons.Default.Videocam, contentDescription = "Video")
            }
        }
    }
}
