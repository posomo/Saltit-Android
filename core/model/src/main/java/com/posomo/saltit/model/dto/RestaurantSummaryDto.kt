package com.posomo.saltit.model.dto

import com.google.gson.annotations.SerializedName

data class RestaurantSummaryDto(
    @SerializedName("restaurantId") val restaurantId: Int,
    @SerializedName("titleImageUrl") val titleImageUrl: String,
    @SerializedName("restaurantName") val restaurantName: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("mainMenuPrice") val mainMenuPrice: Int,
    @SerializedName("mainMenuName") val mainMenuName: String,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("menuSize") val menuSize: Int,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("distance") val distance: Double
)