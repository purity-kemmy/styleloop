package com.example.styleloop.ui.theme.screens.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.styleloop.models.Product
import com.example.styleloop.ui.theme.StyleloopTheme
import androidx.compose.material3.Text
import androidx.navigation.NavController


// Sample product data
fun getSampleProducts(category: String): List<Product> {
    return when (category) {
        "Men" -> listOf(
            Product("Men's T-Shirt", 19.99, "Comfortable cotton t-shirt", "https://example.com/men_tshirt.jpg"),
            Product("Men's Jeans", 39.99, "Stylish blue jeans", "https://example.com/men_jeans.jpg")
        )
        "Women" -> listOf(
            Product("Women's Dress", 49.99, "Elegant evening dress", "https://example.com/women_dress.jpg"),
            Product("Women's Skirt", 29.99, "Casual summer skirt", "https://example.com/women_skirt.jpg")
        )
        else -> emptyList() // Default case for other categories
    }
}

@Composable
fun ProductListScreen(categoryName: String = "Men") {
    // Fetch the list of products for the selected category
    val products = getSampleProducts(categoryName)

    // Layout for Product List screen
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "Products in $categoryName",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        // LazyColumn to display products
        LazyColumn {
            items(products) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Handle product click, you can add navigation here if needed
            },

    ) {
        Row (modifier = Modifier.padding(16.dp)) {
            // Display product image using Coil (you can replace this with your image loader)
            val painter = rememberAsyncImagePainter(product.imageUrl)
            Image(
                painter = painter,
                contentDescription = product.name,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$${product.price}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = product.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductList() {
    StyleloopTheme {
        ProductListScreen(categoryName = "Men")
    }
}
