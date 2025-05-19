package com.example.styleloop.models

data class Product(
    val id: String = "",               // Unique product ID
    val name: String = "",             // Actual product name (e.g., "Jacket")
    val title: String = "",            // Display title (optional, can match name)
    val description: String = "",      // Detailed description
    val location: String = "",         // Where the item is located
    val ownerId: String = "",          // Firebase user UID
    val price: Double? = null,
    val imageUrl: String = ""          // Image URL (can be empty if not uploaded)
)
