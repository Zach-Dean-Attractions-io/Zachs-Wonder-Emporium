package com.example.zachswonderemporium.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myPlan")
data class PlanItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "plan_item_id") val id: Long? = null,
    @ColumnInfo(name = "poi_id") var poiId: String?
) {

    companion object {

        fun getTestingPlanItems(): Array<PlanItem> {
            return arrayOf(
                PlanItem(poiId = "1"),
                PlanItem(poiId = "3"),
                PlanItem(poiId = "5"),
                PlanItem(poiId = "7"),
                PlanItem(poiId = "9"),
            )
        }

    }

}
