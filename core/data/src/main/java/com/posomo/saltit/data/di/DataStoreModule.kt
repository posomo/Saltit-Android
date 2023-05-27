package com.posomo.saltit.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.posomo.saltit.data.datastore.PriceDataStore
import com.posomo.saltit.data.datastore.PriceDataStoreImpl
import com.posomo.saltit.data.datastore.USER_LUNCH_PRICE_DATASTORE
import com.posomo.saltit.network.Dispatcher
import com.posomo.saltit.network.SaltitAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

	private const val DATASTORE_NAME = USER_LUNCH_PRICE_DATASTORE

	private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)

	@Singleton
	@Provides
	fun providePriceDataStore(@ApplicationContext context: Context, @Dispatcher(SaltitAppDispatchers.IO) ioDispatcher: CoroutineDispatcher): PriceDataStore {
		return PriceDataStoreImpl(context.dataStore, ioDispatcher)
	}
}