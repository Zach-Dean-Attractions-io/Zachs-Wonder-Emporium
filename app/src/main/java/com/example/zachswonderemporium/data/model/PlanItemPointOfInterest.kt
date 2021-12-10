package com.example.zachswonderemporium.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class PlanItemPointOfInterest(
    @Embedded val planItem: PlanItem,
    @Relation (
        parentColumn = "poi_id",
        entityColumn = "poi_id"
    )
    val pointOfInterest: PointOfInterest
)
