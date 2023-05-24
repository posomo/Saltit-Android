package com.posomo.saltit.model.domain

data class RestaurantSummary(
	val restaurantId: Int,
	val titleImageUrl: String,
	val restaurantName: String,
	val rating: String,
	val mainMenuPrice: String,
	val mainMenuName: String,
	val categoryName: String,
	val menuSize: String,
	val longitude: Double,
	val latitude: Double,
	val distance: String
)