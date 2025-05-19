package com.example.styleloop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.styleloop.ui.theme.screens.cart.CartScreen
import com.example.styleloop.ui.theme.screens.checkout.CheckoutScreen
import com.example.styleloop.ui.theme.screens.home.HomeScreen
import com.example.styleloop.ui.theme.screens.login.LoginScreen
import com.example.styleloop.ui.theme.screens.orderconfirmation.OrderConfirmationScreen
import com.example.styleloop.ui.theme.screens.product.AddProductScreen
import com.example.styleloop.ui.theme.screens.product.EditProductScreen
import com.example.styleloop.ui.theme.screens.productDetail.ProductDetailsScreen
import com.example.styleloop.ui.theme.screens.productlist.ProductListScreen
import com.example.styleloop.ui.theme.screens.signup.SignupScreen
import com.example.styleloop.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController= rememberNavController(),  // Correct parameter name here
    startDestination: String = LOGIN
) {
    NavHost(
        modifier = modifier,
        navController = navController,  // Use navController here
        startDestination = startDestination
    ) {
        // Splash
        composable(SPLASH) {
            SplashScreen(navController)
        }

        // Login
        composable(LOGIN ) {
            LoginScreen(navController)
        }

        // Signup
        composable(SIGNUP ) {
            SignupScreen(navController)
        }

        // Home
        composable(HOME) {
            HomeScreen(navController)
        }

        // Product List
        composable(PRODUCT_LIST) {
            ProductListScreen(navController)
        }

        // Product Details (with dynamic productName)
        composable(PRODUCT_DETAILS){
            ProductDetailsScreen(productName = String())
        }

        // Cart
        composable(CART) {
            CartScreen(navController)
        }

        // Checkout
        composable(CHECKOUT) {
            CheckoutScreen(navController)
        }

        // Order Confirmation
        composable(ORDER_CONFIRMATION) {
            OrderConfirmationScreen(navController)
        }

        composable(ADD_PRODUCT) {
            AddProductScreen(navController = navController)
        }
        composable(EDIT_PRODUCT) {
            val productId = ""
            EditProductScreen(navController = navController, productId = productId)
        }

        // Edit Product (optional screen with nav arguments)
        composable("${EDIT_PRODUCT}/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            EditProductScreen(navController, productId)
        }
 }
}
