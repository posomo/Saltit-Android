package com.posomo.saltit.data.datastore

import kotlinx.coroutines.flow.Flow

interface SaltitDataStore {

	suspend fun getUserCurrentAvgLunchPrice(): Int

	suspend fun setUserCurrentAvgLunchPrice(price: Int): Flow<Boolean>

	suspend fun getUserIdealAvgLunchPrice(): Int

	suspend fun setUserIdealAvgLunchPrice(price: Int): Flow<Boolean>

	suspend fun setOnboardingFinishStatus(finished: Boolean): Flow<Boolean>

	suspend fun getOnboardingFinsihStatus(): Boolean
}