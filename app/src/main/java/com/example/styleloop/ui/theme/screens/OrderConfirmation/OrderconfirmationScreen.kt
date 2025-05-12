package com.example.styleloop.ui.theme.screens.orderconfirmation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationScreen(navController: NavController) {
    // Example data for order confirmation
    val orderNumber = "123456"
    val totalPrice = 149.97
    val itemCount = 3

    // Scaffold with AppBar
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Order Confirmation") })
        }
    ) { paddingValues ->
        // Content of the Order Confirmation Screen
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Confirmation Message
            Text(
                text = "Thank you for your order!",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Order Summary
            Text("Order Number: $orderNumber", fontSize = 18.sp)
            Text("Items Ordered: $itemCount", fontSize = 18.sp)
            Text("Total Amount: $${"%.2f".format(totalPrice)}", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(24.dp))

            // Button to navigate back to the Home screen (or any other screen)
            Button(
                onClick = {
                    navController.navigate("home_screen") // Replace with the route to your home screen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Home")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Optional: Button to view the order details
            Button(
                onClick = {
                    navController.navigate("order_details_screen") // Navigate to an order details screen (optional)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Order Details")
            }
        }
    }
}
