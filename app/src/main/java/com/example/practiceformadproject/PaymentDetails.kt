package com.example.practiceformadproject

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class PaymentDetails : AppCompatActivity() {

    private lateinit var etCardNumber: EditText
    private lateinit var etCardHolderName: EditText
    private lateinit var etMonthYear: EditText
    private lateinit var btnSave: Button

    private lateinit var dbRef: DatabaseReference
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        etCardNumber = findViewById(R.id.cardNo)
        etCardHolderName = findViewById(R.id.holderName)
        etMonthYear = findViewById(R.id.expDate)
        btnSave = findViewById(R.id.button6)

        dbRef = FirebaseDatabase.getInstance().getReference("Payments")
        calendar = Calendar.getInstance()

        etMonthYear.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, day)

                    val dateFormat = SimpleDateFormat("MM/yyyy", Locale.getDefault())
                    etMonthYear.setText(dateFormat.format(selectedDate.time))
                },
                year,
                month,
                day
            )

            datePicker.show()
        }

        btnSave.setOnClickListener {
            savePaymentDetails()
        }
    }

    private fun savePaymentDetails() {
        val cardNo = etCardNumber.text.toString()
        val holderName = etCardHolderName.text.toString()
        val expDate = etMonthYear.text.toString()

        if (cardNo.isEmpty()) {
            etCardNumber.error = "Please enter Card Number"
        }
        if (holderName.isEmpty()) {
            etCardHolderName.error = "Please enter Card Holder Name"
        }
        if (expDate.isEmpty()) {
            etMonthYear.error = "Please enter Expire date"
        }

        val payId = dbRef.push().key!!
        val payment = PaymentModel(payId, cardNo, holderName, expDate)

        dbRef.child(payId).setValue(payment)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etCardNumber.text.clear()
                etCardHolderName.text.clear()
                etMonthYear.text.clear()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
