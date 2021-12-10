package com.example.zachswonderemporium.data.local

import androidx.room.*
import com.example.zachswonderemporium.data.model.PlanItem
import com.example.zachswonderemporium.data.model.PlanItemPointOfInterest

@Dao
interface MyPlanDao {

    @Query("select * from myPlan")
    fun getAll(): List<PlanItem>

    @Transaction
    @Query("select * from myPlan")
    suspend fun getAllWithPOIDetails(): List<PlanItemPointOfInterest>

    @Insert
    suspend fun insertPlanItems(vararg planItems: PlanItem)

    @Delete
    suspend fun deleteItemFromPlan(planItem: PlanItem)

}