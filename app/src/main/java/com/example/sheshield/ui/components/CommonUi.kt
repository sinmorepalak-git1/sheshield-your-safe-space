package com.example.sheshield.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FakeMap() {
    val gridColor = Color(0xFFE2E8F0)
    val roadColor = Color(0xFFCBD5E1)
    val parkColor = Color(0xFFDCFCE7)
    
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            
            // Grid
            val step = 40.dp.toPx()
            for (x in 0..(width / step).toInt()) {
                drawLine(gridColor, start = Offset(x * step, 0f), end = Offset(x * step, height), strokeWidth = 1f)
            }
            for (y in 0..(height / step).toInt()) {
                drawLine(gridColor, start = Offset(0f, y * step), end = Offset(width, y * step), strokeWidth = 1f)
            }
            
            // Parks
            drawCircle(parkColor, radius = 60.dp.toPx(), center = Offset(120.dp.toPx(), 100.dp.toPx()))
            drawCircle(parkColor, radius = 80.dp.toPx(), center = Offset(width - 80.dp.toPx(), height - 140.dp.toPx()))
            
            // Roads
            val roadPath = Path().apply {
                moveTo(0f, height * 0.3f)
                quadraticTo(width * 0.3f, height * 0.25f, width * 0.5f, height * 0.35f)
                quadraticTo(width * 0.8f, height * 0.45f, width, height * 0.4f)
            }
            drawPath(roadPath, roadColor, style = Stroke(width = 14.dp.toPx(), cap = androidx.compose.ui.graphics.StrokeCap.Round))
            
            drawLine(roadColor, start = Offset(80.dp.toPx(), 0f), end = Offset(100.dp.toPx(), height), strokeWidth = 10.dp.toPx())
            drawLine(roadColor, start = Offset(width - 120.dp.toPx(), 0f), end = Offset(width - 160.dp.toPx(), height), strokeWidth = 10.dp.toPx())
        }
        
        // User Marker
        Box(
            modifier = Modifier.align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            val infiniteTransition = rememberInfiniteTransition(label = "marker_ping")
            val markerPingScale by infiniteTransition.animateFloat(
                initialValue = 0.8f,
                targetValue = 2.4f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000),
                    repeatMode = RepeatMode.Restart
                ), label = "pingScale"
            )
            val markerPingAlpha by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000),
                    repeatMode = RepeatMode.Restart
                ), label = "pingAlpha"
            )
            
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .scale(markerPingScale)
                    .alpha(markerPingAlpha)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.White, CircleShape)
                    .padding(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary, CircleShape))
            }
        }
    }
}

@Composable
fun StatBox(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}
