package com.example.practiceformadproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val medicineList: ArrayList<MedicineModel>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
//
    private val pictures:IntArray = intArrayOf(R.drawable.panadol,R.drawable.digene,R.drawable.losartan50mg,R.drawable.aspirin)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_provider_card_view,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMedicine = medicineList[position]

        holder.medName.text = currentMedicine.name
        holder.medprice.text = currentMedicine.pricePerUnit
        holder.image.setImageResource(pictures[position])
    }

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var medName: TextView
        var medprice: TextView

        init{
            image = itemView.findViewById(R.id.imageView)
            medName = itemView.findViewById(R.id.tvMedName)
            medprice = itemView.findViewById(R.id.tvMedPrice)
        }
    }
}