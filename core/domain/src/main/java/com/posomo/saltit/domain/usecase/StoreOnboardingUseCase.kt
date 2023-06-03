package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.datastore.SaltitDataStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreOnboardingUseCase @Inject constructor(
	private val saltitDataStore: SaltitDataStore,
) {

	operator fun invoke(finished: Boolean) = flow {
		saltitDataStore.setOnboardingFinishStatus(finished).collect { isSuccess ->
			emit(isSuccess)
		}
	}
}