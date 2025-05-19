package com.example.styleloop.ui.theme.screens.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.styleloop.models.Product
import com.example.styleloop.ui.theme.StyleloopTheme

// Sample product data
fun getSampleProducts(category: String): List<Product> {
    return when (category) {
        "Men" -> listOf(
            Product("Men's T-Shirt", 19.99.toString(), "https://example.com/men_tshirt.jpg"),
            Product("Men's Jeans", 39.99.toString(), "https://example.com/men_jeans.jpg")
        )
        "Women" -> listOf(
            Product("Women's Dress", 49.99.toString(), "https://example.com/women_dress.jpg"),
            Product("Women's Skirt", 29.99.toString(), "https://example.com/women_skirt.jpg")
        )
        else -> emptyList()
    }
}

@Composable
fun ProductListScreen(navController: NavController, categoryName: String = "Men") {
    val products = getSampleProducts(categoryName)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Products in $categoryName",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(products) { product ->
                ProductItem(product = product, onClick = {
                    navController.navigate("product_details/${product.name}")
                })
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
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
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductList() {
    StyleloopTheme {
        // Note: Previews don’t support NavController, so we call version without it
        ProductListScreenPreviewOnly()
    }
}

// This version is only for previews
@Composable
fun ProductListScreenPreviewOnly(categoryName: String = "Men") {
    val products = getSampleProducts(categoryName)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Products in $categoryName",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(products) { product ->
                ProductItem(product = product, onClick = {})
            }
        }
    }
}
