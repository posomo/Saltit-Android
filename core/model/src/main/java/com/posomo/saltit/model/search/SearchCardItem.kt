package com.posomo.saltit.model.search

data class SearchCardItem(
    val id: Long,
    val order: Int,
    val name: String,
    val category: String,
    val rate: Float,
    val distance: Int,
    val menu: String,
    val price: Int,
    val lat: Double,
    val log: Double,
)
