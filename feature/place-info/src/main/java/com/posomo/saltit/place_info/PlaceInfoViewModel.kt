package com.posomo.saltit.place_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posomo.saltit.domain.usecase.GetRestaurantDetailUseCase
import com.posomo.saltit.model.domain.RestaurantDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceInfoViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase
) : ViewModel() {

    private val _restaurantDetail = MutableStateFlow<RestaurantDetail?>(null)
    val restaurantDetail = _restaurantDetail.asStateFlow()

    fun initDetailInfo(id: Int) {
        viewModelScope.launch {
            getRestaurantDetailUseCase.getDetail(id).collectLatest {
                _restaurantDetail.value = it
            }
        }
    }
}