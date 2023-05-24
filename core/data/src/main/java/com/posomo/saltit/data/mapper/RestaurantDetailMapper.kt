package com.posomo.saltit.data.mapper

import com.posomo.saltit.model.domain.RestaurantDetail
import com.posomo.saltit.model.dto.MainSideDto
import com.posomo.saltit.model.response.RestaurantDetailResponse

object RestaurantDetailMapper: ObjectMapper<RestaurantDetail, RestaurantDetailResponse> {
    override fun asDomain(data: RestaurantDetailResponse): RestaurantDetail {
        return RestaurantDetail(
            id = data.id,
            diningcodeUrl = data.diningcodeUrl,
            totalMenuCount = "${data.totalMenuCount}",
            name = data.name,
            rating = String.format("%.1f", data.rating.toDouble() / 20),
            phone = data.phone,
            address = data.address,
            categories = data.categories.joinToString(separator = ", "),
            mainMenuCount = data.main.count,
            mainMenuList = data.main.menus,
            sideMenuCount = data.side.count,
            sideMenuList = data.side.menus,
            titleImageUrl = data.titleImageUrl
        )
    }

    override fun asData(domain: RestaurantDetail): RestaurantDetailResponse {
        return RestaurantDetailResponse(
            id = domain.id,
            diningcodeUrl = domain.diningcodeUrl,
            totalMenuCount = domain.totalMenuCount.toInt(),
            name = domain.name,
            rating = (domain.rating.toDouble() * 20).toInt(),
            phone = domain.phone,
            address = domain.address,
            categories = domain.categories.split(", "),
            main = MainSideDto(count = domain.mainMenuCount, menus = domain.mainMenuList),
            side = MainSideDto(count = domain.sideMenuCount, menus = domain.sideMenuList),
            titleImageUrl = domain.titleImageUrl
        )
    }
}

fun RestaurantDetailResponse.asDomain() : RestaurantDetail {
    return RestaurantDetailMapper.asDomain(this)
}

fun RestaurantDetail.asData() : RestaurantDetailResponse {
    return RestaurantDetailMapper.asData(this)
}