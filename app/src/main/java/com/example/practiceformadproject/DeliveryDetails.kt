package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import com.example.myapplication1.R
//import com.example.myapplication1.models.ReceiverModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeliveryDetails : AppCompatActivity() {

    private lateinit var etName:EditText
    private lateinit var etContactNo:EditText
    private lateinit var etStreetName:EditText
    private lateinit var etCityName:EditText
    private lateinit var etEmail:EditText
    private lateinit var btnSaveData:Button

    private lateinit var dbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)

        etName = findViewById(R.id.inputname)
        etContactNo = findViewById(R.id.phone)
        etStreetName = findViewById(R.id.houseno)
        etCityName = findViewById(R.id.editTextTextPersonName7)
        etEmail = findViewById(R.id.editTextTextPersonName8)
        btnSaveData = findViewById(R.id.button)

        dbRef = FirebaseDatabase.getInstance().getReference("Delivery Details")

        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnSaveData.setOnClickListener {
            saveDeliveryDetails()
        }


    }
    private fun saveDeliveryDetails(){
        //getting values
        val recName = etName.text.toString()
        val recContactNo = etContactNo.text.toString()
        val recStreetName = etStreetName.text.toString()
        val recCityName = etCityName.text.toString()
        val recEmail = etEmail.text.toString()

        if(recName.isEmpty()){
            etName.error = "Please Enter Name"
        }
        if(recContactNo.isEmpty()){
            etContactNo.error = "Please Enter Contact No"
        }
        if(recStreetName.isEmpty()){
            etStreetName.error = "Please Enter Street Name"
        }
        if(recCityName.isEmpty()){
            etCityName.error = "Please Enter City Name"
        }
        if(recEmail.isEmpty()){
            etEmail.error = "Please Enter Email"
        }

        val recId = dbRef.push().key!!

        val receiver = ReceiverModel(recId, recContactNo , recStreetName, recStreetName, recCityName, recEmail)

        dbRef.child(recId).setValue(receiver)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()

                etName.text.clear()
                etContactNo.text.clear()
                etStreetName.text.clear()
                etCityName.text.clear()
                etContactNo.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()

            }
         val secondAct = findViewById<Button>(R.id.button)
        secondAct.setOnClickListener{
            val intent = Intent(this, FirstPage::class.java)
            startActivity(intent)
        }
    }
}