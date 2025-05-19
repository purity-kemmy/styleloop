package com.example.styleloop.ui.theme.screens.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.styleloop.data.PropertyViewModel
import com.example.styleloop.models.Product

@Composable
fun ProductListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel = remember { PropertyViewModel(navController, context) }

    val selectedProduct = remember { mutableStateOf(Product()) }
    val productList = remember { mutableStateListOf<Product>() }

    LaunchedEffect(Unit) {
        viewModel.fetchAllProducts(selectedProduct, productList)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("addProduct")
            }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(productList) { product ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = "Price: $${product.price}")
                    Text(text = "Location: ${product.location}")
                    Row {
                        Button(onClick = {
                            navController.navigate("editProduct/${product.id}")
                        }) {
                            Text("Edit")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            viewModel.deleteProduct(product.id)
                        }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
