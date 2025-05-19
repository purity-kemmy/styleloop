package com.example.styleloop.ui.theme.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.styleloop.R
import com.example.styleloop.navigation.PRODUCT_LIST
import com.example.styleloop.navigation.SPLASH
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        // Wait for 2 seconds before navigating
        delay(2000)

        // Navigate to the Home screen and pop the splash screen from the backstack
        navController.navigate(PRODUCT_LIST) {
            popUpTo(SPLASH) { inclusive = true }
        }
    }

    // Main splash screen UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Display App Logo (ensure img_1 is in your drawable)
            Image(
                painter = painterResource(id = R.drawable.img_1), // ✅ Ensure this is a valid image in your resources
                contentDescription = "App Logo",
                modifier = Modifier.size(700.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            // Display App Name
            Text(
                text = "Styleloop",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    // Preview the SplashScreen
    SplashScreen(navController = rememberNavController())
}
