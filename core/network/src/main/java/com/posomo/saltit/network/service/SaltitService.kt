package com.posomo.saltit.network.service

import com.posomo.saltit.model.request.RestaurantSummaryRequest
import com.posomo.saltit.network.model.RestaurantSummaryResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SaltitService {
    @POST("home/restaurant-summary")
    suspend fun sendRestaurantSummaryInfoReq(
        @Body restaurantSummaryBody: RestaurantSummaryRequest
    ): ApiResponse<RestaurantSummaryResponse>
}