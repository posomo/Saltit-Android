package com.posomo.saltit.model.domain

data class RestaurantSummary(
	val titleImageUrl: String,
	val restaurantName: String,
	val rating: Float,
	val mainMenuPrice: Int,
	val mainMenuName: String,
	val categoryName: String,
	val distance: String
)