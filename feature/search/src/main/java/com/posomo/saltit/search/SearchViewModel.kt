package com.posomo.saltit.search

import androidx.lifecycle.ViewModel
import com.posomo.saltit.model.search.SearchCardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _aroundRestaurants = MutableStateFlow<List<SearchCardItem>>(emptyList())
    val aroundRestaurants = _aroundRestaurants.asStateFlow()

    fun getAroundRestaurants() {
        _aroundRestaurants.value = listOf(
            SearchCardItem(0, 1, "을지로입구역", "카테고리", 4.5f, 200, "을지로", 8000, 37.566035912327, 126.98235941562),
            SearchCardItem(1, 2, "청계광장", "카테고리", 4.5f, 200, "청계광장", 8000, 37.570013917406, 126.9780542555)
        )
    }
}