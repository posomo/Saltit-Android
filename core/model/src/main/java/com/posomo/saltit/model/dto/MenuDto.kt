package com.posomo.saltit.model.dto

import com.google.gson.annotations.SerializedName

data class MenuDto(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("name") val name: String
)
