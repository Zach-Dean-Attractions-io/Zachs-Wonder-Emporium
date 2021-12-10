package com.example.zachswonderemporium.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pointsOfInterest")
data class PointOfInterest(
    @PrimaryKey @ColumnInfo(name = "poi_id") val id: String,
    @ColumnInfo(name = "poi_name") var name: String,
    @ColumnInfo(name = "poi_summary") var summary: String,
    @ColumnInfo(name = "poi_latitude") var latitude: Double,
    @ColumnInfo(name = "poi_longitude") var longitude: Double,
    @ColumnInfo(name = "poi_image_url") var imageURL: String,
    @ColumnInfo(name = "poi_icon_url") var iconURL: String,
    @ColumnInfo(name = "poi_category") var category: String
) :Parcelable
