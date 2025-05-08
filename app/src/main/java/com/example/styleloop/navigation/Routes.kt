package com.example.styleloop.navigation


object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val HOME = "home"
    const val CATEGORY_LIST = "category_list"
    const val PRODUCT_LIST = "product_list/{categoryName}"  // Example dynamic route
    const val PRODUCT_DETAILS = "product_details/{productName}"

}