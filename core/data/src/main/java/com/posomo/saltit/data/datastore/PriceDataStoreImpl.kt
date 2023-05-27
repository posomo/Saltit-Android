package com.posomo.saltit.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.posomo.saltit.network.Dispatcher
import com.posomo.saltit.network.SaltitAppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PriceDataStoreImpl @Inject constructor(
	private val dataStore: DataStore<Preferences>,
	@Dispatcher(SaltitAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : PriceDataStore {

	private val userCurrentAvgLunchPriceFlow =
		dataStore.data.map { pref ->
			pref[intPreferencesKey(USER_CURRENT_AVG_LUNCH_PRICE)] ?: 0
		}

	override suspend fun getUserCurrentAvgLunchPrice(): Int = userCurrentAvgLunchPriceFlow.first()

	override suspend fun setUserCurrentAvgLunchPrice(price: Int) = flow {
		dataStore.edit { pref ->
			pref[intPreferencesKey(USER_CURRENT_AVG_LUNCH_PRICE)] = price
			emit(true)
		}
	}.catch { emit(false) }.flowOn(ioDispatcher)

	private val userIdealAvgLunchPriceFlow =
		dataStore.data.map { pref ->
			pref[intPreferencesKey(USER_IDEAL_AVG_LUNCH_PRICE)] ?: 0
		}

	override suspend fun getUserIdealAvgLunchPriceInLocal(): Int = userIdealAvgLunchPriceFlow.first()

	override suspend fun setUserIdealAvgLunchPrice(price: Int) = flow {
		dataStore.edit { pref ->
			pref[intPreferencesKey(USER_IDEAL_AVG_LUNCH_PRICE)] = price
			emit(true)
		}
	}.catch { emit(false) }.flowOn(ioDispatcher)

}