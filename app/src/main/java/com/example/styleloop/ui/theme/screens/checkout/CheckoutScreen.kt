package com.example.styleloop.ui.theme.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.styleloop.R
import com.example.styleloop.ui.theme.screens.cart.CartItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {
    // Example list of cart items
    val cartItems = remember {
        listOf(
            CartItem(id = 1, name = "Denim Jacket", price = 59.99, imageRes = R.drawable.img_2, quantity = 1),
            CartItem(id = 2, name = "Leather Boots", price = 89.99, imageRes = R.drawable.img_3, quantity = 2)
        )
    }

    // Calculate total price
    val totalPrice = cartItems.sumOf { it.price * it.quantity }

    // Scaffold with AppBar and BottomBar
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Checkout") })
        },
        bottomBar = {
            BottomAppBar(
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total: $${"%.2f".format(totalPrice)}", fontSize = 18.sp)
                        Button(onClick = {
                            // Handle checkout action (e.g., navigate to payment screen)
                            navController.navigate("payment_screen")
                        }) {
                            Text("Proceed to Payment")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        // Content of the Checkout Screen
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(cartItems) { item ->
                CartItemRow(item = item)
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name)
            Text("Price: $${item.price}")
            Text("Quantity: ${item.quantity}")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(navController = rememberNavController())
}

