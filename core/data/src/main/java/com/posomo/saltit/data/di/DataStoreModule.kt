package com.posomo.saltit.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.posomo.saltit.data.datastore.SaltitDataStore
import com.posomo.saltit.data.datastore.SaltitDataStoreImpl
import com.posomo.saltit.data.datastore.USER_LUNCH_PRICE_DATASTORE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

	private const val DATASTORE_NAME = USER_LUNCH_PRICE_DATASTORE

	private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)

	@Singleton
	@Provides
	fun providePriceDataStore(@ApplicationContext context: Context): SaltitDataStore {
		return SaltitDataStoreImpl(context.dataStore)
	}
}