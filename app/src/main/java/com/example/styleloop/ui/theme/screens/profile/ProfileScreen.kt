package com.example.styleloop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.styleloop.data.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val isAuthenticated by authViewModel.isAuthenticated.observeAsState(false)
    val user by authViewModel.user.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isAuthenticated) {
                Text(text = "Name: ${user?.name ?: "Unknown"}", style = MaterialTheme.typography.titleLarge)
                Text(text = "Email: ${user?.email ?: "No email"}", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = {
                    authViewModel.logout()
                    navController.navigate("login_screen")
                }) {
                    Text("Logout")
                }
            } else {
                Text("You are not logged in.")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navController.navigate("login_screen")
                }) {
                    Text("Go to Login")
                }
            }
        }
    }
}
