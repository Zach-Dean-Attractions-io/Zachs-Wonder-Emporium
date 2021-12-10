package com.example.zachswonderemporium.data.local

import android.graphics.Point
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.data.model.PointOfInterestCategory
import com.example.zachswonderemporium.data.model.PointOfInterestLiveTime
import kotlinx.coroutines.flow.Flow

@Dao
interface PointOfInterestDao {

    @Query("select * from pointsOfInterest")
    fun getAll(): LiveData<List<PointOfInterest>>

    @Query("select * from pointsOfInterest where poi_id = :poi_id")
    fun getPointOfInterest(poi_id: String): PointOfInterest

    @Query("select * from pointsOfInterest where poi_category = :category")
    fun getAllInCategory(category: PointOfInterestCategory): LiveData<List<PointOfInterest>>

    @Insert
    fun insertPointsOfInterest(vararg pointsOfInterest: PointOfInterest)

    @Transaction
    @Query("select * from pointsOfInterest")
    suspend fun getAllWithLiveTimes(): List<PointOfInterestLiveTime>

    @Query("select pointsOfInterest.* from pointsOfInterest left join myPlan on pointsOfInterest.poi_id = myPlan.poi_id where myPlan.plan_item_id is null")
    suspend fun getAllPointsOfInterestNotInPlan(): List<PointOfInterest>

}