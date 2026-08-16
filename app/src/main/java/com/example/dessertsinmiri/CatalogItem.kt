package com.example.dessertsinmiri

// Catalog items
data class CatalogItem(
    val id: Int,
    val title: String,
    val description: String,
    val category: List<String>,
    val imageResourceId: Int, // Main image for list view
    val imageResourceIds: List<Int>, // All images for detail view
    var isFavourite: Boolean = false // For task 4: Favourite status
)