package com.posomo.saltit.data.datastore

import kotlinx.coroutines.flow.Flow

interface PriceDataStore {

	suspend fun getUserCurrentAvgLunchPrice(): Int

	suspend fun setUserCurrentAvgLunchPrice(price: Int): Flow<Boolean>

	suspend fun getUserIdealAvgLunchPriceInLocal(): Int

	suspend fun setUserIdealAvgLunchPrice(price: Int): Flow<Boolean>
}