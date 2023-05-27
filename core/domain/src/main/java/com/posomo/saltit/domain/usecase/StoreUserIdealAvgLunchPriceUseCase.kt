package com.posomo.saltit.domain.usecase

import com.posomo.saltit.data.datastore.PriceDataStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreUserIdealAvgLunchPriceUseCase  @Inject constructor(
	private val priceDataStore: PriceDataStore,
) {

	operator fun invoke(price: Int) = flow {
		priceDataStore.setUserIdealAvgLunchPrice(price).collect { isSuccess ->
			emit(isSuccess)
		}
	}
}