package com.posomo.saltit.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Serializable
data class SaltitResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val comment: String = "default comment"
    )