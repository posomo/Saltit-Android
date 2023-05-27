package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.datastore.PosomoDataStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreOnboardingUseCase @Inject constructor(
	private val posomoDataStore: PosomoDataStore,
) {

	operator fun invoke(finished: Boolean) = flow {
		posomoDataStore.setOnboarding(finished).collect { isSuccess ->
			emit(isSuccess)
		}
	}
}