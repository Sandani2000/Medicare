package com.example.practiceformadproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CartMainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<CartDataClass>
    private lateinit var searchView: SearchView
    private lateinit var searchList:ArrayList<CartDataClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cartactivity_main)

        val imageList = arrayOf(R.drawable.img)
        val image1 = arrayOf(R.drawable.baseline_edit_24)
        val image2 = arrayOf(R.drawable.baseline_delete_forever_24)
        val dataTitle = arrayOf("Paracetamol")
        val dataCompany = arrayOf("hhhhhh")
        val dataPrice = arrayOf(10)
        val dataUnits = arrayOf("one")
        val dataQuantity = arrayOf(1)





        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<CartDataClass>()
        searchList = arrayListOf<CartDataClass>()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText?.toLowerCase(Locale.getDefault())
                if (!searchText.isNullOrEmpty()) {
                    dataList.forEach {
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })


        for ((index, _) in imageList.withIndex()) {
            val dataClass = CartDataClass(
                imageList[index],
                image1[index],
                image2[index],
                dataTitle[index],
                dataCompany[index],
                dataPrice[index],
                dataUnits[index],
                dataQuantity[index]
            )
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = CartAdapterClass(searchList)
        recyclerView.adapter = CartAdapterClass(dataList)
    }
}
