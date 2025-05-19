package com.example.styleloop.ui.theme.screens.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.styleloop.data.PropertyViewModel
import com.example.styleloop.models.Product

@Composable
fun AddProductScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel = remember { PropertyViewModel(navController, context) }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
        OutlinedTextField(value = imageUrl, onValueChange = { imageUrl = it }, label = { Text("Image URL") })
        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val product = Product(
                name = name,
                title = title,
                description = description,
                location = location,
                price = price.toDoubleOrNull() ?: 0.0,
                imageUrl = imageUrl
            )
            viewModel.addProduct(product) {
                if (it) navController.popBackStack()
            }
        }) {
            Text("Add Product")
        }
    }
}
