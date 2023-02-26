package com.posomo.saltit.network.model.mapper

/**
 * A customized pokemon error response.
 *
 * @param code A network response code.
 * @param message A network error message.
 */
data class SaltitErrorResponse(
    val code: Int,
    val message: String?
)