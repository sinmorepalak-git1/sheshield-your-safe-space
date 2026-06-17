package com.example.sheshield.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sheshield.ui.components.BottomNavBar
import com.example.sheshield.ui.components.FakeMap
import com.example.sheshield.ui.components.ScreenHeader
import com.example.sheshield.ui.theme.GradientPrimaryEnd
import com.example.sheshield.ui.theme.GradientPrimaryStart

data class NearbyPlace(
    val name: String,
    val type: String,
    val distance: String,
    val eta: String,
    val color: Color
)

val nearbyPlaces = listOf(
    NearbyPlace("Cubbon Park Police Station", "Police", "0.4 km", "5 min", Color(0xFF2563EB)),
    NearbyPlace("Manipal Hospital", "Hospital", "0.9 km", "8 min", Color(0xFF059669)),
    NearbyPlace("Fire Station MG Road", "Fire", "1.2 km", "11 min", Color(0xFFEA580C)),
    NearbyPlace("Women's Help Center", "NGO", "1.6 km", "14 min", Color(0xFFDB2777))
)

val filters = listOf("All", "Police", "Hospital", "Pharmacy", "Fire", "NGO")

@Composable
fun NearbyScreen(navController: NavController) {
    var selectedFilter by remember { mutableStateOf("All") }

    Scaffold(
        topBar = {
            ScreenHeader(
                title = "Nearby Help",
                subtitle = "Within 2 km of you",
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Map Preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                FakeMap()
            }

            // Filters
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filters) { filter ->
                    val isSelected = selectedFilter == filter
                    Surface(
                        onClick = { selectedFilter = filter },
                        shape = CircleShape,
                        color = if (isSelected) Color.Transparent else MaterialTheme.colorScheme.secondaryContainer,
                        modifier = if (isSelected) Modifier.background(
                            brush = Brush.linearGradient(listOf(GradientPrimaryStart, GradientPrimaryEnd)),
                            shape = CircleShape
                        ) else Modifier
                    ) {
                        Text(
                            text = filter,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }

            // Places List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(nearbyPlaces) { _, place ->
                    NearbyPlaceCard(place)
                }
            }
        }
    }
}

@Composable
fun NearbyPlaceCard(place: NearbyPlace) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(place.color, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = place.type.take(3).uppercase(),
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = place.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = "${place.distance} · ${place.eta} away",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(36.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f), CircleShape)
            ) {
                Icon(
                    Icons.Default.Navigation,
                    contentDescription = "Directions",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFF4CAF50).copy(alpha = 0.15f), CircleShape)
            ) {
                Icon(
                    Icons.Default.Phone,
                    contentDescription = "Call",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
