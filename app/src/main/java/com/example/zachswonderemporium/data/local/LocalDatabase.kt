package com.example.zachswonderemporium.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.zachswonderemporium.data.model.LiveTime
import com.example.zachswonderemporium.data.model.PlanItem
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.data.model.utils.InitialPointsOfInterest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = [PointOfInterest::class, PlanItem::class, LiveTime::class], version = 1)
abstract class ZachsWonderEmporiumDatabase : RoomDatabase() {

    abstract fun pointOfInterestDao(): PointOfInterestDao
    abstract fun myPlanDao(): MyPlanDao
    abstract fun liveTimesDao(): LiveTimesDao

    companion object {

        @Volatile
        private var DB_INSTANCE: ZachsWonderEmporiumDatabase? = null

        fun getInstance(context: Context): ZachsWonderEmporiumDatabase {
            synchronized(this) {
                var instance = DB_INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, ZachsWonderEmporiumDatabase::class.java, "zachs_wonder_emporium_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(object: RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                                // Pre-Populate Points Of Interest
                                Executors.newSingleThreadExecutor().execute {
                                    instance?.let {
                                        it.pointOfInterestDao().insertPointsOfInterest(*InitialPointsOfInterest.get())
                                    }
                                }
                            }
                        })
                        .build()

                }

                return instance
            }
        }
    }

}