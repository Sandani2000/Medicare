package com.example.practiceformadproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapterClass(private val dataList: ArrayList<CartDataClass>) :
    RecyclerView.Adapter<CartAdapterClass.ViewHolderClass>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medicine_layout, parent, false)
        return ViewHolderClass(itemView)
    }


    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvImage1.setImageResource(currentItem.dataImage1)
        holder.rvImage2.setImageResource(currentItem.dataImage2)

        holder.rvTitle.text = currentItem.dataTitle.toString()
        holder.rvTitle1.text = currentItem.dataCompany.toString()
        holder.rvTitle2.text = currentItem.dataPrice.toString()
        holder.rvTitle3.text = currentItem.dataUnits.toString()
        holder.rvTitle3.text = currentItem.dataQuantity.toString()
    }


    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.img1)
        val rvImage1: ImageView = itemView.findViewById(R.id.deleteImg)
        val rvImage2: ImageView = itemView.findViewById(R.id.editImg)

        val rvTitle: TextView = itemView.findViewById(R.id.Title)
        val rvTitle1: TextView = itemView.findViewById(R.id.Price)
        val rvTitle2: TextView = itemView.findViewById(R.id.Units)
        val rvTitle3: TextView = itemView.findViewById(R.id.Quantity)
    }
}

