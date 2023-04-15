package com.posomo.saltit.network.di

import com.posomo.saltit.network.Dispatcher
import com.posomo.saltit.network.SaltitAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

	@Provides
	@Dispatcher(SaltitAppDispatchers.IO)
	fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}