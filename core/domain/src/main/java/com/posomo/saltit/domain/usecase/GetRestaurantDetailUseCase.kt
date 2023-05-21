package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.repository.SaltitRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRestaurantDetailUseCase @Inject constructor(
    private val saltitRepository: SaltitRepository
) {

    fun getDetail (
        restaurantId: Int,
        onStart: () -> Unit = {},
        onComplete: () -> Unit = {},
        onError: (String?) -> Unit = {}
    ) = flow {
        saltitRepository.getRestaurantDetailData(restaurantId, onStart, onComplete, onError).collect {
            emit(it)
        }
    }
}