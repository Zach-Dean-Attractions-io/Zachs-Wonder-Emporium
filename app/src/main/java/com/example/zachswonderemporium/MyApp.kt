package com.example.zachswonderemporium

import android.app.Application
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.local.LiveTimesDao
import com.example.zachswonderemporium.data.local.MyPlanDao
import com.example.zachswonderemporium.data.local.PointOfInterestDao
import com.example.zachswonderemporium.data.local.ZachsWonderEmporiumDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // Koin
        val myModule = module {
            single {
                Repository(
                    get() as PointOfInterestDao,
                    get() as MyPlanDao,
                    get() as LiveTimesDao
                )
            }
            single {
                ZachsWonderEmporiumDatabase.getInstance(this@MyApp).pointOfInterestDao()
            }
            single {
                ZachsWonderEmporiumDatabase.getInstance(this@MyApp).myPlanDao()
            }
            single {
                ZachsWonderEmporiumDatabase.getInstance(this@MyApp).liveTimesDao()
            }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }

}