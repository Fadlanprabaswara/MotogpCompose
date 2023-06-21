package com.example.motogpcompose.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motogpcompose.model.HeroRepository
import com.example.motogpcompose.model.OrderHero
import com.example.motogpcompose.screen.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailRewardViewModel(
    private val repository: HeroRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderHero>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderHero>>
        get() = _uiState

    fun getRewardById(heroId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderRewardById(heroId))
        }
    }

}