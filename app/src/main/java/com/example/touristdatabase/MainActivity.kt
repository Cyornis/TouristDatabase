package com.example.touristdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , ItemClickListener {

    private lateinit var datalist: ArrayList<TouristData>
    private lateinit var adapter: Adapter
    lateinit var progressCircular:ProgressBar
    var pageNumber = 0
    private lateinit var touristDb : TouristDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        touristDb = TouristDatabase.getInstance(this)
        progressCircular = findViewById(R.id.progress_circular)
        datalist = ArrayList<TouristData>()

GlobalScope.launch {
    getDataList()
}
        adapter = Adapter(datalist, this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        recyclerView.adapter = adapter
        //   adapter.notifyDataSetChanged()

        val add = findViewById<FloatingActionButton>(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, AddTourist::class.java)
            startActivity(intent)
            // getDataList()   ///list updated
        }

        val nextButton = findViewById<FloatingActionButton>(R.id.next_button)
        nextButton.setOnClickListener{
            getDataList()
        }
    }

    override fun onResume() {
        super.onResume()
        pageNumber = 1
        datalist.clear()
        GlobalScope.launch {
            getDataList()
        }
        adapter.notifyDataSetChanged()
    }

    fun getDataList() {
        progressCircular.visibility = ProgressBar.VISIBLE
        pageNumber = pageNumber+1   ///switching to next page
        //datalist = touristDb.touristDao().getAllData()
        GlobalScope.launch {
            datalist.addAll(touristDb.touristDao().getAllData())
        }
    }

    override fun onItemClickListener(position: Int) {
     val d = datalist[position]
    val intent = Intent(this, UpdateTourist::class.java)
   intent.putExtra("data", d)
    startActivity(intent)
    }

    override fun onDeleteClickListener(id: Int) {
        var td : TouristData? = null
        for(touristData in datalist){
            if(touristData.id == id){
                td = touristData
                datalist.remove(touristData)
                break
            }
        }
      if (td!=null)  {
          GlobalScope.launch {
              touristDb.touristDao().deleteData(td)
          }
       }
        adapter.notifyDataSetChanged()
    }
}


