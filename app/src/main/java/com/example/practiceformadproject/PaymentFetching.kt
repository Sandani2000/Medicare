package com.example.practiceformadproject


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.myapplication1.R
//import com.example.myapplication1.adapters.PayAdapter
//import com.example.myapplication1.models.PaymentModel

import com.google.firebase.database.*

class PaymentFetching : AppCompatActivity() {

    private lateinit var payRecyclerView: RecyclerView
    private lateinit var tvLoadingPaymentData:TextView
    private lateinit var payList: ArrayList<PaymentModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_fetching)

        payRecyclerView = findViewById(R.id.rvPayments)
        payRecyclerView.layoutManager  = LinearLayoutManager(this)
        payRecyclerView.setHasFixedSize(true)
        tvLoadingPaymentData = findViewById(R.id.tvLoadingPaymentData)


        payList = arrayListOf<PaymentModel>()
        getPaymentDetails()

    }
    private fun getPaymentDetails(){
        payRecyclerView.visibility = View.GONE
        tvLoadingPaymentData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Payments")

        dbRef.addValueEventListener(object :ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                payList.clear()
                if (snapshot.exists()) {
                    for (paySnap in snapshot.children){
                        val payData = paySnap.getValue(PaymentModel::class.java)
                        payList.add(payData!!)
                }
                val mAdapter = PayAdapter(payList)
                payRecyclerView.adapter = mAdapter

                mAdapter.setOnItemClickListener(object:PayAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@PaymentFetching,ViewPaymentDetails::class.java)

                        //putExtras
                        //intent.putExtra("payId",payList[position].payId)
                        intent.putExtra("cardNo",payList[position].cardNo)
                        intent.putExtra("holderName",payList[position].holderName)
                        intent.putExtra("expDate",payList[position].expDate)
                        startActivity(intent)
                    }

                })

                payRecyclerView.visibility = View.VISIBLE
                tvLoadingPaymentData.visibility = View.GONE
            }
        }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}

