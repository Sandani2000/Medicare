package com.example.practiceformadproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.example.myapplication1.R
//import com.example.myapplication1.models.PaymentModel



class PayAdapter(private val payList: ArrayList<PaymentModel>):
    RecyclerView.Adapter<PayAdapter.ViewHolder> (){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_payment_list_item, parent, false)
        return ViewHolder(itemView,mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPay = payList[position] //give current account
        holder.tvPaymentTitle.text = currentPay.cardNo //this part confusion
    }



    override fun getItemCount(): Int {
        return payList.size
    }

    class ViewHolder (itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvPaymentTitle : TextView = itemView.findViewById(R.id.tvPaymentTitle)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}
