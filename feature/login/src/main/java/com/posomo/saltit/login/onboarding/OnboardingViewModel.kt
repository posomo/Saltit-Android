package com.posomo.saltit.login.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posomo.saltit.domain.usecase.StoreUserCurrentAvgLunchPriceUsecase
import com.posomo.saltit.domain.usecase.StoreUserIdealAvgLunchPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val storeUserCurrentAvgLunchPriceUsecase: StoreUserCurrentAvgLunchPriceUsecase,
	private val storeUserIdealAvgLunchPriceUseCase: StoreUserIdealAvgLunchPriceUseCase
): ViewModel() {

	private val _currentAvgPunchPrice = MutableStateFlow(0)
	val currentAvgLunchPrice = _currentAvgPunchPrice.asStateFlow()

	private val _storeCurrentAvgLunchPriceSuccessFlow = MutableStateFlow(false)
	val storeCurrentAvgLunchPriceSuccessFlow = _storeCurrentAvgLunchPriceSuccessFlow.asStateFlow()

	private val _storeIdealAvgLunchPriceSuccessFlow = MutableStateFlow(false)
	val storeIdealAvgLunchPriceSuccessFlow = _storeIdealAvgLunchPriceSuccessFlow.asStateFlow()
	fun storeCurrentAvgLunchPrice(price: Int) {
		viewModelScope.launch {
			_currentAvgPunchPrice.value = price/1000

			storeUserCurrentAvgLunchPriceUsecase(price).collectLatest {
				withContext(Dispatchers.Main) {
					_storeCurrentAvgLunchPriceSuccessFlow.value = true
				}
			}
		}
	}

	fun storeIdealAvgLunchPrice(price: Int) {
		viewModelScope.launch {
			storeUserIdealAvgLunchPriceUseCase(price).collectLatest {
				withContext(Dispatchers.Main) {
					_storeIdealAvgLunchPriceSuccessFlow.value = true
				}
			}
		}
	}
}