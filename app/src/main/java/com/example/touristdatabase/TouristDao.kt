package com.example.touristdatabase

import androidx.room.*

@Dao
interface TouristDao {
    @Insert
    fun AddTouristData(touristData: TouristData)

    @Update
    fun UpdateTouristData(touristData: TouristData)

    @Query("SELECT * FROM tourist_database")
    fun getAllData():List<TouristData>

    @Delete
    fun deleteData(touristData: TouristData)
}