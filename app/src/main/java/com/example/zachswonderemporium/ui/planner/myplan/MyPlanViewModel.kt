package com.example.zachswonderemporium.ui.planner.myplan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.model.PlanItem
import com.example.zachswonderemporium.data.model.PlanItemPointOfInterest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPlanViewModel(val repository: Repository) : ViewModel() {

    // My Plan Items
    private val _myPlanItems = MutableLiveData<List<PlanItemPointOfInterest>>()

    val myPlanIems : LiveData<List<PlanItemPointOfInterest>>
        get() = _myPlanItems

    // Navigation
    private val _navigateToAddItems = MutableLiveData<Boolean>(false)

    val navigateToAddItems: LiveData<Boolean>
        get() = _navigateToAddItems

    fun setNavigateToAddItems(value: Boolean) {
        _navigateToAddItems.value = value
    }

    fun onNavigationToAddItemsComplete() {
        _navigateToAddItems.value = false
    }

    init {
        refreshPlanItems()
    }

    fun refreshPlanItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val myPlanItems = repository.getItemsInPlan()
            _myPlanItems.postValue(myPlanItems)
        }
    }

    fun deletePlanItem(planItem: PlanItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItemFromPlan(planItem)
            refreshPlanItems()
        }
    }

}