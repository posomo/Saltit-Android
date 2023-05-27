package com.posomo.saltit.login.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posomo.saltit.domain.usecase.StoreUserCurrentAvgLunchPriceUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnboardingFirstViewModel @Inject constructor(
	private val storeUserCurrentAvgLunchPriceUsecase: StoreUserCurrentAvgLunchPriceUsecase
): ViewModel() {

	private val _storeCurrentAvgLunchPriceSuccessFlow = MutableStateFlow(false)
	val storeCurrentAvgLunchPriceSuccessFlow = _storeCurrentAvgLunchPriceSuccessFlow.asStateFlow()

	fun storeCurrentAvgLunchPrice(price: Int) {
		viewModelScope.launch {
			storeUserCurrentAvgLunchPriceUsecase(price).collectLatest {
				withContext(Dispatchers.Main) {
					_storeCurrentAvgLunchPriceSuccessFlow.value = true
				}
			}
		}
	}
}