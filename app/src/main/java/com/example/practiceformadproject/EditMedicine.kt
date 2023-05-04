package com.example.practiceformadproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EditMedicine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_medicine)

        val secondAct = findViewById<Button>(R.id.btnUpdate)
        secondAct.setOnClickListener{
            val intent = Intent(this,ServiceProviderViewMedicine::class.java)
            startActivity(intent)
        }
    }
}