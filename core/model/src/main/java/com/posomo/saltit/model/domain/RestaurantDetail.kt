package com.posomo.saltit.model.domain

import com.posomo.saltit.model.dto.MenuDto

data class RestaurantDetail(
    val id: Int,
    val diningcodeUrl: String,
    val totalMenuCount: String,
    val name: String,
    val rating: String,
    val phone: String,
    val address: String,
    val categories: String,
    val mainMenuCount: Int,
    val mainMenuList: List<MenuDto>,
    val sideMenuCount: Int,
    val sideMenuList: List<MenuDto>
)