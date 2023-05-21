package com.posomo.saltit.network.service

import com.posomo.saltit.model.request.RestaurantSummaryRequest
import com.posomo.saltit.model.response.RestaurantDetailResponse
import com.posomo.saltit.model.response.RestaurantSummaryResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SaltitService {
    @POST("/api/v1/home/restaurant-summary")
    suspend fun fetchRestaurantSummary(
        @Body restaurantSummaryBody: RestaurantSummaryRequest
    ): ApiResponse<RestaurantSummaryResponse>

    @GET("/api/v1/restaurant/detail/{restaurantId}")
    suspend fun fetchRestaurantDetail(
        @Path("restaurantId") restaurantId: Int
    ) : ApiResponse<RestaurantDetailResponse>
}