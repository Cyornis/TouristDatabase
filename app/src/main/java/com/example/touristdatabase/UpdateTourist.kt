package com.example.touristdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateTourist : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var id:EditText
    private lateinit var location:EditText
    private lateinit var touristDb: TouristDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_tourist)
        touristDb = TouristDatabase.getInstance(this)

        name = findViewById<EditText>(R.id.name_et)
        id = findViewById<EditText>(R.id.tourist_id)
        location = findViewById<EditText>(R.id.tourist_location_et)
        val edit = findViewById<Button>(R.id.edit)
        val update = findViewById<Button>(R.id.update)

        val dataComimgFromMainActivity  = intent.getSerializableExtra("data") as TouristData    // data is present in updateTourist class
        name.setText(dataComimgFromMainActivity.name)
        id.setText(dataComimgFromMainActivity.id.toString())
        location.setText(dataComimgFromMainActivity.location)

        name.isEnabled = false
        id.isEnabled = false
        location.isEnabled = false

        edit.setOnClickListener {
            name.isEnabled = true
            id.isEnabled = true
            location.isEnabled = true
            //Toast.makeText(this,"Ready to Edit",Toast.LENGTH_SHORT).show()
        }

        update.setOnClickListener {
            updateData(dataComimgFromMainActivity)
        }
    }

    fun updateData(touristData: TouristData) {
      //  val touristData = TouristData(id.text.toString(),name.text.toString(),location.text.toString() )
        touristData.id = id.text.toString().toInt()
        touristData.name = name.text.toString()
        touristData.location = location.text.toString()

        GlobalScope.launch {
            touristDb.touristDao().UpdateTouristData(touristData)
        }
    }
}



