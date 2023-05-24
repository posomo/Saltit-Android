package com.posomo.saltit.data.mapper

import com.posomo.saltit.model.domain.RestaurantSummary
import com.posomo.saltit.model.dto.RestaurantSummaryDto
import kotlin.math.roundToInt

object RestaurantSummariesMapper : ObjectMapper<List<RestaurantSummary>, List<RestaurantSummaryDto>> {

	override fun asDomain(data: List<RestaurantSummaryDto>): List<RestaurantSummary> {
		return data.map {
			RestaurantSummary(
				restaurantId = it.restaurantId,
				titleImageUrl = it.titleImageUrl,
				restaurantName = it.restaurantName,
				rating = String.format("%.1f", it.rating.toDouble() / 20),
				mainMenuName = it.mainMenuName,
				categoryName = it.categoryName,
				mainMenuPrice = "${it.mainMenuPrice}원",
				distance = "${(it.distance).roundToInt()}m",
				menuSize = "${it.menuSize}개",
				longitude = it.longitude,
				latitude = it.latitude,
			)
		}
	}

	override fun asData(domain: List<RestaurantSummary>): List<RestaurantSummaryDto> {
		return domain.map {
			RestaurantSummaryDto(
				restaurantId = it.restaurantId,
				titleImageUrl = it.titleImageUrl,
				restaurantName = it.restaurantName,
				rating = (it.rating.toDouble() * 20).toInt(),
				mainMenuName = it.mainMenuName,
				categoryName = it.categoryName,
				mainMenuPrice = it.mainMenuPrice.toInt(),
				distance = it.distance.substring(0, it.distance.length-1).toDouble(),
				menuSize = it.menuSize.toInt(),
				longitude = it.longitude,
				latitude = it.latitude
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