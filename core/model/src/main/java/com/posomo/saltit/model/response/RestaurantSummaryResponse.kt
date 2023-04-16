package com.posomo.saltit.model.response

import com.google.gson.annotations.SerializedName
import com.posomo.saltit.model.dto.RestaurantSummaryDto

data class RestaurantSummaryResponse(
	@SerializedName("restaurantSummaries")
	val restaurantSummaries: List<RestaurantSummaryDto>,
	@SerializedName("hasNext") val hasNext: Boolean,
	@SerializedName("page") val page: Int,
	@SerializedName("size") val size: Int,
)