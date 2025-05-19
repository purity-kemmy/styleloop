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
    val product = sampleProducts.find { it.name == productName }

    if (product == null) {
        Text("Product not found", modifier = Modifier.padding(16.dp))
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
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Add to cart action */ }) {
            Text("Add to Cart")
        }
    }
}

// Sample data
val sampleProducts = listOf(
    Product("Men's T-Shirt", 19.99.toString(), "https://example.com/men_tshirt.jpg"),
    Product("Men's Jeans", 39.99.toString(), "https://example.com/men_jeans.jpg"),
    Product("Women's Dress", 49.99.toString(), "https://example.com/women_dress.jpg")
)

