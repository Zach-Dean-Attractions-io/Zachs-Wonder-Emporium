package com.example.zachswonderemporium.ui.times

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.model.PointOfInterestLiveTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimesViewModel(val repository: Repository) : ViewModel() {

    private val _pointsOfInterestWithLiveTimes = MutableLiveData<List<PointOfInterestLiveTime>>()

    val pointsOfInterestWithLiveTimes: LiveData<List<PointOfInterestLiveTime>>
        get() = _pointsOfInterestWithLiveTimes

    private val _navigateToDetails = MutableLiveData<PointOfInterestLiveTime>()

    val navigateToDetails: LiveData<PointOfInterestLiveTime>
        get() = _navigateToDetails

    init {
        refreshTimes()
    }

    fun refreshTimes() {
        viewModelScope.launch(Dispatchers.IO) {
            val refreshedLiveTimes = repository.refreshLiveTimes()
            _pointsOfInterestWithLiveTimes.postValue(refreshedLiveTimes)
        }
    }

    fun navigateToDetails(poiWithLiveTime: PointOfInterestLiveTime) {
        _navigateToDetails.value = poiWithLiveTime
    }

    fun navigationToDetailsComplete() {
        _navigateToDetails.value = null
    }

}