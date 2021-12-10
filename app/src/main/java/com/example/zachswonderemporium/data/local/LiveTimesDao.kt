package com.example.zachswonderemporium.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.zachswonderemporium.data.model.LiveTime

@Dao
interface LiveTimesDao {

    @Insert
    fun insertLiveTimes(vararg liveTimes: LiveTime)

    @Query("delete from liveTimes")
    suspend fun deleteAllTimes()
}