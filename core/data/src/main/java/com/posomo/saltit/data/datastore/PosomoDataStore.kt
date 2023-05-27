package com.posomo.saltit.data.datastore

import kotlinx.coroutines.flow.Flow

interface PosomoDataStore {

	suspend fun getUserCurrentAvgLunchPrice(): Int

	suspend fun setUserCurrentAvgLunchPrice(price: Int): Flow<Boolean>

	suspend fun getUserIdealAvgLunchPrice(): Int

	suspend fun setUserIdealAvgLunchPrice(price: Int): Flow<Boolean>

	suspend fun setOnboarding(finished: Boolean): Flow<Boolean>
}