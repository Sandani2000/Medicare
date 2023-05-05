package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ServiceProviderViewMedicine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_provider_view_medicine)

        val secondAct = findViewById<Button>(R.id.btnDelete)
        secondAct.setOnClickListener {
            val intent = Intent(this, ViewAllMedicines::class.java)
            startActivity(intent)
        }

        val thirdAct = findViewById<Button>(R.id.btnEdit)
        thirdAct.setOnClickListener {
            val intent = Intent(this, EditMedicine::class.java)
            startActivity(intent)
        }
    }
}