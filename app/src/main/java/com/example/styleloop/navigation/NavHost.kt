package com.example.styleloop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.styleloop.navigation.Routes.SIGNUP
import com.example.styleloop.ui.screens.ProfileScreen
import com.example.styleloop.ui.theme.screens.cart.CartScreen
import com.example.styleloop.ui.theme.screens.checkout.CheckoutScreen
import com.example.styleloop.ui.theme.screens.home.HomeScreen
import com.example.styleloop.ui.theme.screens.login.LoginScreen
import com.example.styleloop.ui.theme.screens.orderconfirmation.OrderConfirmationScreen
import com.example.styleloop.ui.theme.screens.productDetail.ProductDetailsScreen
import com.example.styleloop.ui.theme.screens.productlist.ProductListScreen
import com.example.styleloop.ui.theme.screens.signup.SignUpScreen
import com.example.styleloop.ui.theme.screens.splash.SplashScreen


@Composable
fun AppNavHost(navController: NavHostController, startDestination: String=SIGNUP) {
    NavHost(navController = navController, startDestination = startDestination) {

        // Splash
        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        // Login
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }

        // Signup
        composable(Routes.SIGNUP) {
            SignUpScreen(navController = navController)
        }

        // Home
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        // Product List
        composable(Routes.PRODUCT_LIST) {
            ProductListScreen()
        }

        // Product Details with productName as a path argument
        composable(Routes.PRODUCT_DETAILS) { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            ProductDetailsScreen(productName = productName)
        }

        // Cart
        composable(Routes.CART) {
            CartScreen(navController = navController)
        }

        // Checkout
        composable(Routes.CHECKOUT) {
            CheckoutScreen(navController = navController)
        }

        // Order Confirmation
        composable(Routes.ORDER_CONFIRMATION) {
            OrderConfirmationScreen(navController = navController)
        }
        composable(Routes.PROFILE) {
            ProfileScreen(navController = navController)
        }
    }
}
