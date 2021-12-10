package com.example.zachswonderemporium.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.ui.explore.ExploreViewModel
import com.example.zachswonderemporium.ui.planner.addtoplan.AddToPlanViewModel
import com.example.zachswonderemporium.ui.planner.myplan.MyPlanViewModel
import com.example.zachswonderemporium.ui.times.TimesViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> ExploreViewModel(repository) as T
            modelClass.isAssignableFrom(MyPlanViewModel::class.java) -> MyPlanViewModel(repository) as T
            modelClass.isAssignableFrom(AddToPlanViewModel::class.java) -> AddToPlanViewModel(repository) as T
            modelClass.isAssignableFrom(TimesViewModel::class.java) -> TimesViewModel(repository) as T
            else -> throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
        }

    }


}