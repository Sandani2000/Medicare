package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ViewMedicines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_medicines)

        val secondAct = findViewById<Button>(R.id.btnAddToCart)
        secondAct.setOnClickListener{
            val intent = Intent(this,ServiceProviderViewMedicine::class.java)
            startActivity(intent)
        }
    }
}