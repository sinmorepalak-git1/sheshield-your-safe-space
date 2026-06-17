package com.example.sheshield.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sheshield.ui.screens.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Forgot : Screen("forgot")
    object Home : Screen("home")
    object SOS : Screen("sos")
    object Contacts : Screen("contacts")
    object Location : Screen("location")
    object Nearby : Screen("nearby")
    object FakeCall : Screen("fake_call")
    object Tips : Screen("tips")
    object VoiceShake : Screen("voice_shake")
    object Profile : Screen("profile")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Signup.route) {
            SignupScreen(navController)
        }
        composable(Screen.Forgot.route) {
            ForgotScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.SOS.route) {
            SOSScreen(navController)
        }
        composable(Screen.Contacts.route) {
            ContactsScreen(navController)
        }
        composable(Screen.Location.route) {
            LocationScreen(navController)
        }
        composable(Screen.Nearby.route) {
            NearbyScreen(navController)
        }
        composable(Screen.FakeCall.route) {
            FakeCallScreen(navController)
        }
        composable(Screen.Tips.route) {
            TipsScreen(navController)
        }
        composable(Screen.VoiceShake.route) {
            VoiceShakeScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}
