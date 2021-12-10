package com.example.zachswonderemporium.ui.planner.addtoplan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.model.PlanItem
import com.example.zachswonderemporium.data.model.PointOfInterest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddToPlanViewModel(val repository: Repository) : ViewModel() {

    private val _pointsOfInterestNotInPlan = MutableLiveData<List<PointOfInterest>>()

    val pointsOfInterestNotInPlan: LiveData<List<PointOfInterest>>
        get() = _pointsOfInterestNotInPlan

    private val _saveOperationComplete = MutableLiveData<Boolean>(false)

    val saveOperationComplete: LiveData<Boolean>
        get() = _saveOperationComplete

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPointsOfInterestNotInPlan()
            _pointsOfInterestNotInPlan.postValue(result)
        }
    }

    fun addItemsToPlan(selectedItems: List<PointOfInterest>) {
        viewModelScope.launch(Dispatchers.IO) {
            val planItems = selectedItems.map { pointOfInterest ->
                PlanItem(poiId = pointOfInterest.id)
            }.toTypedArray()
            repository.addPointsOfInterestToPlan(planItems)
            _saveOperationComplete.postValue(true)
        }
    }

}