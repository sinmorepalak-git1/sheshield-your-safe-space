package com.example.sheshield.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sheshield.data.User
import com.example.sheshield.navigation.Screen
import com.example.sheshield.ui.components.BottomNavBar
import com.example.sheshield.ui.components.ScreenHeader
import com.example.sheshield.ui.theme.GradientPrimaryEnd
import com.example.sheshield.ui.theme.GradientPrimaryStart
import com.example.sheshield.ui.theme.SOS
import com.example.sheshield.ui.viewmodels.AuthViewModel
import com.example.sheshield.ui.viewmodels.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel()
) {
    val userProfile by profileViewModel.userProfile.collectAsState()
    val isLoading by profileViewModel.isLoading.collectAsState()
    val updateStatus by profileViewModel.updateStatus.collectAsState()

    var darkMode by remember { mutableStateOf(false) }
    var notifications by remember { mutableStateOf(true) }
    var stealthMode by remember { mutableStateOf(true) }
    var showEditDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(updateStatus) {
        updateStatus?.let { result ->
            if (result.isSuccess) {
                Toast.makeText(context, "Profile updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Update failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
            }
            profileViewModel.resetUpdateStatus()
        }
    }

    Scaffold(
        topBar = {
            ScreenHeader(
                title = "Profile & Settings",
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        if (isLoading && userProfile == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    userProfile?.let { user ->
                        ProfileHeader(user = user, onEditClick = { showEditDialog = true })
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    StatsRow()
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    SettingsGroup(title = "Preferences") {
                        SettingsRow(
                            icon = if (darkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                            label = "Dark mode",
                            trailing = { Switch(checked = darkMode, onCheckedChange = { darkMode = it }) }
                        )
                        SettingsRow(
                            icon = Icons.Default.Notifications,
                            label = "Notifications",
                            sub = "SOS, check-in & safety alerts",
                            trailing = { Switch(checked = notifications, onCheckedChange = { notifications = it }) }
                        )
                        SettingsRow(
                            icon = Icons.Default.Mic,
                            label = "Voice & shake",
                            sub = "Hands-free SOS triggers",
                            onClick = { navController.navigate(Screen.VoiceShake.route) }
                        )
                        SettingsRow(
                            icon = Icons.Default.Language,
                            label = "Language",
                            sub = "English (India)"
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    SettingsGroup(title = "Privacy") {
                        SettingsRow(
                            icon = Icons.Default.Lock,
                            label = "Stealth mode",
                            sub = "Disguise app icon as calculator",
                            trailing = { Switch(checked = stealthMode, onCheckedChange = { stealthMode = it }) }
                        )
                        SettingsRow(
                            icon = Icons.Default.Lock,
                            label = "App lock",
                            sub = "Require PIN to open"
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    SettingsGroup(title = "Support") {
                        SettingsRow(icon = Icons.Default.Help, label = "Help & FAQ")
                        SettingsRow(icon = Icons.Default.Info, label = "About SheShield", sub = "Version 1.0.0")
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    OutlinedButton(
                        onClick = { 
                            authViewModel.logout()
                            navController.navigate(Screen.Login.route) { popUpTo(0) } 
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, SOS.copy(alpha = 0.3f)),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = SOS.copy(alpha = 0.05f))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Logout, contentDescription = null, tint = SOS, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Log out", color = SOS, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        if (showEditDialog) {
            EditProfileDialog(
                user = userProfile,
                onDismiss = { showEditDialog = false },
                onSave = { name, phone ->
                    profileViewModel.updateUserProfile(name, phone)
                    showEditDialog = false
                }
            )
        }
    }
}

@Composable
fun ProfileHeader(user: User, onEditClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(listOf(GradientPrimaryStart, GradientPrimaryEnd))
                )
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.White.copy(alpha = 0.25f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val initial = if (user.name.isNotEmpty()) user.name[0].uppercaseChar().toString() else "?"
                Text(initial, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(user.name.ifEmpty { "User" }, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text(user.email, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                if (user.phone.isNotEmpty()) {
                    Text(user.phone, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                }
            }
            Surface(
                onClick = onEditClick,
                color = Color.White.copy(alpha = 0.2f),
                shape = CircleShape
            ) {
                Text(
                    "Edit",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun EditProfileDialog(
    user: User?,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var name by remember { mutableStateOf(user?.name ?: "") }
    var phone by remember { mutableStateOf(user?.phone ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Profile") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(name, phone) },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Changes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProfileStatBox(label = "CONTACTS", value = "5", modifier = Modifier.weight(1f))
        ProfileStatBox(label = "SAFE ZONES", value = "3", modifier = Modifier.weight(1f))
        ProfileStatBox(label = "CHECK-INS", value = "42", modifier = Modifier.weight(1f))
    }
}

@Composable
fun ProfileStatBox(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontWeight = FontWeight.Black, fontSize = 18.sp)
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun SettingsGroup(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        Surface(
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 1.dp
        ) {
            Column(content = content)
        }
    }
}

@Composable
fun SettingsRow(
    icon: ImageVector,
    label: String,
    sub: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            if (sub != null) {
                Text(text = sub, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }
        }
        if (trailing != null) {
            trailing()
        } else {
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(18.dp))
        }
    }
}
