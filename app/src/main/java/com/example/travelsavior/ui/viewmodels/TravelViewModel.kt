package com.example.travelsavior.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.travelsavior.model.TravelData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class TravelViewModel: ViewModel() {
    private var _uiState: MutableStateFlow<TravelData> = MutableStateFlow(TravelData());
    val uiState: StateFlow<TravelData> = _uiState.asStateFlow();

    fun setPrice (price: Double) {
        _uiState.value = _uiState.value.copy(price = price);
    }

    fun setDistance (distance: Double) {
        _uiState.value = _uiState.value.copy(distance = distance);
    }

    fun setAverageConsumption (averageConsumption: Double) {
        _uiState.value = _uiState.value.copy(averageConsumption = averageConsumption);
    }

    fun calculateTravel ()  {
        val result = _uiState.value.price * _uiState.value.distance / _uiState.value.averageConsumption;
        _uiState.value = _uiState.value.copy(travelPrice = result);
    }

    fun reset () {
        _uiState.value = TravelData();
    }
}