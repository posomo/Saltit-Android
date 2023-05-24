package com.posomo.saltit.model.response

import com.google.gson.annotations.SerializedName
import com.posomo.saltit.model.dto.MainSideDto

data class RestaurantDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("diningcodeUrl") val diningcodeUrl: String,
    @SerializedName("totalMenuCount") val totalMenuCount: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("categories") val categories: List<String>,
    @SerializedName("main") val main: MainSideDto,
    @SerializedName("side") val side: MainSideDto,
    @SerializedName("titleImageUrl") val titleImageUrl: String
)