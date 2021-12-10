package com.example.zachswonderemporium.data.network

import com.example.zachswonderemporium.data.model.LiveTime
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object FirebaseDataHelper {

    suspend fun retrieveLiveTimes(): ArrayList<LiveTime> {
        val db = Firebase.firestore

        val firebaseResult = db.collection("live_times").get().await()

        var liveTimes = arrayListOf<LiveTime>()

        for(document in firebaseResult) {
            liveTimes.add(document.toObject(LiveTime::class.java))
        }

        return liveTimes
    }

}