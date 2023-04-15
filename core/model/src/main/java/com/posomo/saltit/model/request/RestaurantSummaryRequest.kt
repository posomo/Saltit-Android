package com.posomo.saltit.model.request

import com.google.gson.annotations.SerializedName

data class RestaurantSummaryRequest(
    @SerializedName("foodTypeName")
    val foodTypeName: String,
    @SerializedName("maxDistance")
    val maxDistance: Int,
    @SerializedName("maxPrice")
    val maxPrice: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("userLongitude")
    val userLongitude: Float,
    @SerializedName("userLatitude")
    val userLatitude: Float
)