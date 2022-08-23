package com.example.touristdatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTourist : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var id: EditText
    private lateinit var location: EditText
    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_tourist)

        name = findViewById<EditText>(R.id.name_et)
        id = findViewById<EditText>(R.id.tourist_id_et)
        location = findViewById<EditText>(R.id.tourist_location_et)
        email = findViewById<EditText>(R.id.tourist_email_et)
        val add = findViewById<Button>(R.id.add)
        add.setOnClickListener {
            addData()
        }
    }

    fun addData() {
        var touristDatabase = TouristDatabase.getInstance(this)
        val touristData = TouristData(
            id.text.toString().toInt(),
            name.text.toString(),
            location.text.toString(),
            email.text.toString()
        )

        GlobalScope.launch {
            touristDatabase.touristDao().AddTouristData(touristData)

        }
    }
}
