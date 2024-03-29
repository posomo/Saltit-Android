package com.posomo.saltit.data.repository

import androidx.annotation.WorkerThread
import com.posomo.saltit.data.datastore.SaltitDataStore
import com.posomo.saltit.data.mapper.asDomain
import com.posomo.saltit.model.*
import com.posomo.saltit.model.request.RestaurantSummaryRequest
import com.posomo.saltit.network.Dispatcher
import com.posomo.saltit.network.SaltitAppDispatchers
import com.posomo.saltit.network.model.mapper.ErrorResponseMapper
import com.posomo.saltit.network.service.SaltitService
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SaltitRepositoryImpl @Inject constructor(
    private val saltitService: SaltitService,
    private val saltitDataStore: SaltitDataStore,
    @Dispatcher(SaltitAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : SaltitRepository {

    @WorkerThread
    override fun getRestaurantSummaryData(
        request: RestaurantSummaryRequest,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = saltitService.fetchRestaurantSummary(request)
        response.suspendOnSuccess {
            emit(data.restaurantSummaries.asDomain())
        }.onError {
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }.onException { onException { onError(message) } }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    override fun getRestaurantDetailData(
        restaurantId: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = saltitService.fetchRestaurantDetail(restaurantId)
        response.suspendOnSuccess {
            emit(data.asDomain())
        }.onError {
            map(ErrorResponseMapper) { onError("[Error Code: $code]: $message") }
        }.onException { onException { onError(message) } }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override fun getOnboardingFinishStatus() = flow {
        emit(saltitDataStore.getOnboardingFinsihStatus())
    }
}

