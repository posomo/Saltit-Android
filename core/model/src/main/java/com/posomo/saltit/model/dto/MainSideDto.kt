package com.posomo.saltit.model.dto

import com.google.gson.annotations.SerializedName

data class MainSideDto(
    @SerializedName("count") val count: Int,
    @SerializedName("menus") val menus: List<MenuDto>
)
