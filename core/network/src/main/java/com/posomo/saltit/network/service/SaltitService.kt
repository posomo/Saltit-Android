package com.posomo.saltit.network.service

import com.posomo.saltit.model.request.RestaurantSummaryRequest
import com.posomo.saltit.model.response.RestaurantSummaryResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SaltitService {
    @POST("api/v1/home/restaurant-summary")
    suspend fun sendRestaurantSummaryInfoReq(
        @Body restaurantSummaryBody: RestaurantSummaryRequest
    ): ApiResponse<RestaurantSummaryResponse>
}