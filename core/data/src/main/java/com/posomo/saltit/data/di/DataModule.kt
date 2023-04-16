package com.posomo.saltit.data.di

import com.posomo.saltit.data.repository.SaltitRepository
import com.posomo.saltit.data.repository.SaltitRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

	@Binds
	fun bindsSaltitRepository(
		saltitRepositoryImpl: SaltitRepositoryImpl
	): SaltitRepository
}