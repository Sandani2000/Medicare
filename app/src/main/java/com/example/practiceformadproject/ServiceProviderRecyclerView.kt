package com.example.practiceformadproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ServiceProviderRecyclerView : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var medicineList: ArrayList <MedicineModel>
    private lateinit var firebase: DatabaseReference


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_provider_recycler_view)

        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        medicineList = arrayListOf<MedicineModel>()

        getMedicines()

//        adapter = RecyclerAdapter()
//        recyclerView.adapter = adapter
    }

    private fun getMedicines() {
        recyclerView.visibility = View.GONE

        firebase = FirebaseDatabase.getInstance().getReference("medicines")

        firebase.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                medicineList.clear()
                if(snapshot.exists()){
                    for(medicineSnap in snapshot.children) {
                        val medicineData = medicineSnap.getValue(MedicineModel::class.java)
                        medicineList.add(medicineData!!)
                    }
                    val mAdapter = RecyclerAdapter(medicineList)
                    recyclerView.adapter = mAdapter

                    recyclerView.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}