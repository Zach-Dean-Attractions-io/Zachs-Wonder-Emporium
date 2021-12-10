package com.example.zachswonderemporium.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PointOfInterestLiveTime (
    @Embedded val pointOfInterest: PointOfInterest,
    @Relation (
        parentColumn = "poi_id",
        entityColumn = "poi_id"
    )
    val liveTime: LiveTime?
) : Parcelable