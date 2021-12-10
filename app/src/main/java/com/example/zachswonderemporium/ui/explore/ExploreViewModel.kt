package com.example.zachswonderemporium.ui.explore

import androidx.lifecycle.*
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.data.model.PointOfInterestCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExploreViewModel(val repository: Repository) : ViewModel() {

    val attractionCategory = MutableLiveData<PointOfInterestCategory>(PointOfInterestCategory.ATTRACTIONS)

    fun retrievePointsOfInterestByCategory(category: PointOfInterestCategory) {
        attractionCategory.value = category
    }

    val pointsOfInterestLive: LiveData<List<PointOfInterest>> = Transformations.switchMap(attractionCategory) { category ->
        repository.getPointsOfInterestByCategory(category)
    }


}

