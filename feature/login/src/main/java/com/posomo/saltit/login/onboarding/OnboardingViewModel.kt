package com.posomo.saltit.login.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posomo.saltit.domain.usecase.StoreOnboardingUseCase
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
	private val storeUserIdealAvgLunchPriceUseCase: StoreUserIdealAvgLunchPriceUseCase,
	private val storeOnboardingUseCase: StoreOnboardingUseCase
): ViewModel() {

	private val _currentAvgLunchPrice = MutableStateFlow(0)
	val currentAvgLunchPrice = _currentAvgLunchPrice.asStateFlow()

	private val _idealAvgLunchPrice = MutableStateFlow(0)
	val idealAvgLunchPrice = _idealAvgLunchPrice.asStateFlow()

	private val _storeCurrentAvgLunchPriceSuccessFlow = MutableStateFlow(false)
	val storeCurrentAvgLunchPriceSuccessFlow = _storeCurrentAvgLunchPriceSuccessFlow.asStateFlow()

	private val _storeIdealAvgLunchPriceSuccessFlow = MutableStateFlow(false)
	val storeIdealAvgLunchPriceSuccessFlow = _storeIdealAvgLunchPriceSuccessFlow.asStateFlow()

	private val _storeOnboardingSuccessFlow = MutableStateFlow(false)
	val storeOnboardingSuccessFlow = _storeOnboardingSuccessFlow.asStateFlow()

	fun storeCurrentAvgLunchPrice(price: Int) {
		viewModelScope.launch {
			_currentAvgLunchPrice.value = price/1000

			storeUserCurrentAvgLunchPriceUsecase(price).collectLatest { isSuccess ->
				withContext(Dispatchers.Main) {
					Log.d("Check@@@", "isSuccess -> $isSuccess")
					_storeCurrentAvgLunchPriceSuccessFlow.value = isSuccess
				}
			}
		}
	}

	fun storeIdealAvgLunchPrice(price: Int) {
		viewModelScope.launch {
			_idealAvgLunchPrice.value = price/1000

			storeUserIdealAvgLunchPriceUseCase(price).collectLatest { isSuccess ->
				withContext(Dispatchers.Main) {
					_storeIdealAvgLunchPriceSuccessFlow.value = isSuccess
				}
			}
		}
	}

	fun finishOnboarding(finished: Boolean = true) {
		viewModelScope.launch {
			storeOnboardingUseCase(finished).collectLatest { isSuccess ->
				withContext(Dispatchers.Main) {
					_storeOnboardingSuccessFlow.value = isSuccess
				}
			}
		}
	}
}