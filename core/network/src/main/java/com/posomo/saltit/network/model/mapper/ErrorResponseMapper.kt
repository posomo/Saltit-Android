package com.posomo.saltit.network.model.mapper

import com.posomo.saltit.network.model.SaltitErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper : ApiErrorModelMapper<SaltitErrorResponse> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): SaltitErrorResponse {
        return SaltitErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}