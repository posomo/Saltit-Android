package com.posomo.saltit.data.repository

import androidx.annotation.WorkerThread
import com.posomo.saltit.model.domain.RestaurantDetail
import com.posomo.saltit.model.domain.RestaurantSummary
import com.posomo.saltit.model.request.RestaurantSummaryRequest
import kotlinx.coroutines.flow.Flow

interface SaltitRepository {
    @WorkerThread
    fun getRestaurantSummaryData(
        request: RestaurantSummaryRequest,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<RestaurantSummary>>

    @WorkerThread
    fun getRestaurantDetailData(
        restaurantId: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) : Flow<RestaurantDetail>
}