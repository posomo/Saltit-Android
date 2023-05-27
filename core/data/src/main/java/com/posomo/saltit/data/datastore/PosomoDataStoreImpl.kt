package com.posomo.saltit.data.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PosomoDataStoreImpl @Inject constructor(
	private val dataStore: DataStore<Preferences>,
) : PosomoDataStore {

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
	}.catch {
		Log.d("Check@@@", "${it.message}")
		emit(false) }

	private val userIdealAvgLunchPriceFlow =
		dataStore.data.map { pref ->
			pref[intPreferencesKey(USER_IDEAL_AVG_LUNCH_PRICE)] ?: 0
		}

	override suspend fun getUserIdealAvgLunchPrice(): Int = userIdealAvgLunchPriceFlow.first()

	override suspend fun setUserIdealAvgLunchPrice(price: Int) = flow {
		dataStore.edit { pref ->
			pref[intPreferencesKey(USER_IDEAL_AVG_LUNCH_PRICE)] = price
			emit(true)
		}
	}.catch { emit(false) }


	private val userOnboardingFlow =
		dataStore.data.map { pref ->
			pref[booleanPreferencesKey(USER_ONBOARDING)] ?: true
		}
	override suspend fun setOnboarding(finished: Boolean) = flow {
		dataStore.edit { pref ->
			pref[booleanPreferencesKey(USER_ONBOARDING)] = finished
			emit(true)
		}
	}.catch { emit(false) }

}