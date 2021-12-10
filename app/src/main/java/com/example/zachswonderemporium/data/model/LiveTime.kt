package com.example.zachswonderemporium.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "liveTimes")
data class LiveTime (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "live_time_id") val id: Long? = null,
    @ColumnInfo(name = "poi_id") var poiId: String = "",
    @ColumnInfo(name = "live_time_message") var message: String = ""
) : Parcelable {

    companion object {

        fun getTestingLiveTimes(): Array<LiveTime> {
            return arrayOf(
                LiveTime(poiId = "1", message =  "2 minutes"),
                LiveTime(poiId = "2", message =  "3 minutes"),
                LiveTime(poiId = "3", message =  "4 minutes"),
                LiveTime(poiId = "4", message =  "5 minutes"),
                LiveTime(poiId = "5", message =  "7 minutes"),

                LiveTime(poiId = "11", message =  "11:00"),
                LiveTime(poiId = "12", message =  "12:00"),

                LiveTime(poiId = "16", message =  "Opens at 12:00"),
            )
        }

    }
}