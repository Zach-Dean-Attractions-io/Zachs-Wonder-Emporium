package com.example.zachswonderemporium.data

import com.example.zachswonderemporium.data.local.LiveTimesDao
import com.example.zachswonderemporium.data.local.MyPlanDao
import com.example.zachswonderemporium.data.local.PointOfInterestDao
import com.example.zachswonderemporium.data.model.LiveTime
import com.example.zachswonderemporium.data.model.PlanItem
import com.example.zachswonderemporium.data.model.PointOfInterestCategory
import com.example.zachswonderemporium.data.model.PointOfInterestLiveTime
import com.example.zachswonderemporium.data.network.FirebaseDataHelper.retrieveLiveTimes
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class Repository(private val pointOfInterestDao: PointOfInterestDao, private val myPlanDao: MyPlanDao, private val liveTimesDao: LiveTimesDao) {

    // Points Of Interest By Category

    fun getPointsOfInterestByCategory(category: PointOfInterestCategory) = pointOfInterestDao.getAllInCategory(category)

    // My Plan
    suspend fun getItemsInPlan() = myPlanDao.getAllWithPOIDetails()

    // Points Of Interest Not In Plan
    suspend fun getPointsOfInterestNotInPlan() = pointOfInterestDao.getAllPointsOfInterestNotInPlan()

    // Add Points Of Interest To Plan
    suspend fun addPointsOfInterestToPlan(itemsToAdd: Array<PlanItem>) = myPlanDao.insertPlanItems(*itemsToAdd)

    // Delete Point Of Interest From Plan
    suspend fun deleteItemFromPlan(planItem: PlanItem) = myPlanDao.deleteItemFromPlan(planItem)

    // Points Of Interest With Times
    suspend fun getAllWithLiveTimes() = pointOfInterestDao.getAllWithLiveTimes()

    // Refresh Live Times
    suspend fun refreshLiveTimes(): List<PointOfInterestLiveTime> {

        liveTimesDao.deleteAllTimes()

        val liveTimes = retrieveLiveTimes()

        liveTimesDao.insertLiveTimes(*liveTimes.toTypedArray())

        return pointOfInterestDao.getAllWithLiveTimes()

    }

}