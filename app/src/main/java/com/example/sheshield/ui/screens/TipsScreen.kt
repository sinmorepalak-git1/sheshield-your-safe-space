package com.example.sheshield.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sheshield.ui.components.BottomNavBar
import com.example.sheshield.ui.components.ScreenHeader
import com.example.sheshield.ui.theme.GradientPrimaryEnd
import com.example.sheshield.ui.theme.GradientPrimaryStart

data class SafetyTip(
    val icon: ImageVector,
    val title: String,
    val desc: String,
    val tint: Color,
    val iconColor: Color
)

val tips = listOf(
    SafetyTip(Icons.Default.Shield, "Self defense basics", "Strike vulnerable spots: eyes, throat, knees.", Color(0xFFFFF1F2), Color(0xFFE11D48)),
    SafetyTip(Icons.Default.DirectionsWalk, "Walking alone at night", "Stay on well-lit streets, share live location.", Color(0xFFFDF4FF), Color(0xFFC026D3)),
    SafetyTip(Icons.Default.DirectionsCar, "Riding a cab safely", "Verify number plate & share trip with family.", Color(0xFFFAF5FF), Color(0xFF9333EA)),
    SafetyTip(Icons.Default.Key, "Public transport", "Sit near the driver or in crowded compartments.", Color(0xFFFDF2F8), Color(0xFFDB2777)),
    SafetyTip(Icons.Default.MonitorHeart, "Stay calm in panic", "Breathe 4-7-8 to think clearly and respond.", Color(0xFFF5F3FF), Color(0xFF7C3AED)),
    SafetyTip(Icons.Default.Phone, "Know the helplines", "Women: 1091 · Police: 100 · Emergency: 112", Color(0xFFFFF1F2), Color(0xFFE11D48))
)

@Composable
fun TipsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            ScreenHeader(
                title = "Safety Tips",
                subtitle = "Small habits, big difference",
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                FeaturedTip()
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "ALL TIPS",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            
            itemsIndexed(tips) { _, tip ->
                TipItem(tip)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun FeaturedTip() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(GradientPrimaryStart, GradientPrimaryEnd)
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "TIP OF THE DAY",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp
                )
                Text(
                    text = "Trust your instincts",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "If a situation feels unsafe — it probably is. Move toward people, light, and exits.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                
                Button(
                    onClick = { },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Read more", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun TipItem(tip: SafetyTip) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(tip.tint, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = tip.icon,
                contentDescription = null,
                tint = tip.iconColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = tip.title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = tip.desc,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
