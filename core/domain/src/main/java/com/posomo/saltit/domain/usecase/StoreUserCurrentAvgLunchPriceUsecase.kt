package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.datastore.SaltitDataStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreUserCurrentAvgLunchPriceUsecase @Inject constructor(
	private val saltitDataStore: SaltitDataStore,
) {

	operator fun invoke(price: Int) = flow {
		saltitDataStore.setUserCurrentAvgLunchPrice(price).collect { isSuccess ->
			emit(isSuccess)
		}
	}
}