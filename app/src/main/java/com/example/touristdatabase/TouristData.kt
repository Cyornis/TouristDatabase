package com.example.touristdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tourist_database")
data class TouristData(

    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name="tourist_name")
    var name:String,

   @ColumnInfo (name="tourist_location")
    var location:String,

    @ColumnInfo(name="tourist_email")
    var email:String="anjali@gmail.com",

):Serializable
