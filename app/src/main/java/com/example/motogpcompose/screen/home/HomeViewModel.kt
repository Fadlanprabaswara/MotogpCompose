package com.example.motogpcompose.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motogpcompose.model.HeroRepository
import com.example.motogpcompose.model.OrderHero
import com.example.motogpcompose.screen.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HeroRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderHero>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderHero>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllHero()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}