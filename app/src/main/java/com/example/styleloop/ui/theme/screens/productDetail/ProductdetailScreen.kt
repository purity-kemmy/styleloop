package com.example.styleloop.ui.theme.screens.productDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.styleloop.models.Product

@Composable
fun ProductDetailsScreen(productName: String) {
    // For demo, fetch product info based on name — in real apps use an ID or viewmodel
    val product = getSampleProducts().find { it.name == productName }

    if (product == null) {
        Text("Product not found")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberAsyncImagePainter(product.imageUrl),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = product.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = "$${product.price}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* TODO: Add to cart logic */ }) {
            Text("Add to Cart")
        }
    }
}

// Dummy product list (you likely already have this)
fun getSampleProducts(): List<Product> {
    return listOf(
        Product("Men's T-Shirt", 19.99, "Comfortable cotton t-shirt", "https://example.com/men_tshirt.jpg"),
        Product("Men's Jeans", 39.99, "Stylish blue jeans", "https://example.com/men_jeans.jpg"),
        Product("Women's Dress", 49.99, "Elegant evening dress", "https://example.com/women_dress.jpg")
    )
}
