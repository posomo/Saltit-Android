package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.repository.SaltitRepository
import com.posomo.saltit.model.request.RestaurantSummaryRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRestaurantSummaryUseCase @Inject constructor(
	private val saltitRepository: SaltitRepository
) {
	operator fun invoke(
		request: RestaurantSummaryRequest,
		onStart: () -> Unit = {},
		onComplete: () -> Unit = {},
		onError: (String?) -> Unit = {}
	) = flow {
		saltitRepository.getRestaurantSummaryData(request, onStart, onComplete, onError).collect {
			emit(it)
		}
	}
}