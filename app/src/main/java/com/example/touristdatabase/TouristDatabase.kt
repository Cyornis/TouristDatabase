package com.example.touristdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TouristData::class], version = 1)
abstract class TouristDatabase : RoomDatabase() {
    abstract fun touristDao(): TouristDao

    companion object {
        private var instance: TouristDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): TouristDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext,
                    TouristDatabase::class.java, "tourist_database")
                    .fallbackToDestructiveMigration()
                    //.addCallback(roomCallback)
                    .build()

            return instance!!

        }
    }
}