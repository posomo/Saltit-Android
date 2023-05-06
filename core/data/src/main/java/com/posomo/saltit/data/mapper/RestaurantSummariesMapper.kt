package com.posomo.saltit.data.mapper

import com.posomo.saltit.model.domain.RestaurantSummary
import com.posomo.saltit.model.dto.RestaurantSummaryDto

object RestaurantSummariesMapper : ObjectMapper<List<RestaurantSummary>, List<RestaurantSummaryDto>> {

	override fun asDomain(data: List<RestaurantSummaryDto>): List<RestaurantSummary> {
		return data.map {
			RestaurantSummary(
				titleImageUrl = it.titleImageUrl,
				restaurantName = it.restaurantName,
				rating = it.rating,
				mainMenuName = it.mainMenuName,
				categoryName = it.categoryName,
				mainMenuPrice = it.mainMenuPrice,
				distance = "${it.distance}m"
			)
		}
	}

	override fun asData(domain: List<RestaurantSummary>): List<RestaurantSummaryDto> {
		return domain.map {
			RestaurantSummaryDto(
				titleImageUrl = it.titleImageUrl,
				restaurantName = it.restaurantName,
				rating = it.rating,
				mainMenuName = it.mainMenuName,
				categoryName = it.categoryName,
				mainMenuPrice = it.mainMenuPrice,
				distance = it.distance.substring(0, it.distance.length-1).toDouble()
			)
		}
	}
}

fun List<RestaurantSummaryDto>.asDomain(): List<RestaurantSummary> {
	return RestaurantSummariesMapper.asDomain(this)
}

fun List<RestaurantSummary>.asData(): List<RestaurantSummaryDto> {
	return RestaurantSummariesMapper.asData(this)
}