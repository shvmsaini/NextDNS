package io.github.shvmsaini.nextdns.data.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//
//@Database(
//    entities = [],
//    version = 1,
//    exportSchema = false,
//)
//abstract class NextDnsDb : RoomDatabase() {
//
//    companion object {
//        @Volatile
//        private var INSTANCE: Any? = null
//
//        fun getInstance(context: Context) {
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//        }
//
//        private fun buildDatabase(appContext: Context) =
//            Room.databaseBuilder(appContext, NextDnsDb::class.java, "database")
//                .fallbackToDestructiveMigration()
//                .build()
//    }
//}