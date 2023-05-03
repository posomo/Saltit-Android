package com.posomo.saltit.model.dto

import com.google.gson.annotations.SerializedName

data class RestaurantSummaryDto(
    @SerializedName("titleImageUrl") val titleImageUrl: String,
    @SerializedName("restaurantName") val restaurantName: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("mainMenuPrice") val mainMenuPrice: Int,
    @SerializedName("mainMenuName") val mainMenuName: String,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("distance") val distance: Double
)