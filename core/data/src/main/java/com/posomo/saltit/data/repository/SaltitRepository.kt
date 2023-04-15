package com.posomo.saltit.data.repository

import androidx.annotation.WorkerThread
import com.posomo.saltit.model.dto.RestaurantSummaryDto
import com.posomo.saltit.model.request.RestaurantSummaryRequest
import kotlinx.coroutines.flow.Flow

interface SaltitRepository {
    @WorkerThread
    fun getRestaurantSummaryData(
        request: RestaurantSummaryRequest,
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<RestaurantSummaryDto>>
}