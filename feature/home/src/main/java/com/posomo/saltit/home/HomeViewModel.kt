package com.posomo.saltit.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posomo.saltit.domain.usecase.GetRestaurantSummaryUseCase
import com.posomo.saltit.model.dto.RestaurantSummaryDto
import com.posomo.saltit.model.request.RestaurantSummaryRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getRestaurantSummaryUseCase: GetRestaurantSummaryUseCase
) : ViewModel() {

	private val resTest = MutableLiveData<List<RestaurantSummaryDto>>()
	val resTest2: LiveData<List<RestaurantSummaryDto>> = resTest

	init {
		viewModelScope.launch {
			getRestaurantSummaryUseCase(
				request = RestaurantSummaryRequest("한식", 1000, 8500, 1, 3, 127.0521f, 37.5033f),
				page = 0
            ).collectLatest {
				resTest.value = it
			}
		}
	}
}