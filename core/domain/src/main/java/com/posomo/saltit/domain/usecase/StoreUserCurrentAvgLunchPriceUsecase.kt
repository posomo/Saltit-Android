package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.datastore.PriceDataStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreUserCurrentAvgLunchPriceUsecase @Inject constructor(
	private val priceDataStore: PriceDataStore,
) {

	operator fun invoke(price: Int) = flow {
		priceDataStore.setUserCurrentAvgLunchPrice(price).collect { isSuccess ->
			emit(isSuccess)
		}
	}
}