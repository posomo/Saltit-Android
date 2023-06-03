package com.posomo.saltit.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posomo.saltit.domain.usecase.GetOnboardingFinishStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
	private val getOnboardingFinishStatusUseCase: GetOnboardingFinishStatusUseCase
): ViewModel() {

	private val _onboardingFinishStatus = MutableStateFlow<Boolean?>(null)
	val onboardingFinishStatus = _onboardingFinishStatus.asStateFlow()

	init {
		getOnboardingFinishStatus()
	}

	private fun getOnboardingFinishStatus() {
		viewModelScope.launch {
			getOnboardingFinishStatusUseCase().collectLatest { isFinished ->
				delay(500)
				_onboardingFinishStatus.value = isFinished
			}
		}
	}
}