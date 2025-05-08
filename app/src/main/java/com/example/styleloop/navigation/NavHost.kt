package com.example.styleloop.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.styleloop.ui.theme.screens.login.LoginScreen
import com.example.styleloop.ui.theme.screens.signup.SignUpScreen
import com.example.styleloop.ui.theme.screens.home.HomeScreen
import com.example.styleloop.ui.theme.screens.category.CategoryListScreen // Example, create this if needed

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(Routes.SIGNUP) {
            SignUpScreen(navController = navController)
        }
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }
        composable(Routes.PRODUCT_LIST ) {
            ProductListScreen(navController = navController)
        }
        composable("product_details/{productName}") { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            ProductDetailsScreen(productName = productName)
        }


    }
}
