package com.safaashatha.allin1

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class productadapter(private val context: Activity,private val arrayList: ArrayList<product>):ArrayAdapter<product>(context,
R.layout.list_products,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.list_products,parent,false);

        val imageview:ImageView=view.findViewById(R.id.productimage)
        val nameview:TextView=view.findViewById(R.id.productname)
        val priceview:TextView=view.findViewById(R.id.productprice)

        imageview.setImageResource(arrayList[position].image!!)
        nameview.text=arrayList[position].name
        priceview.text=arrayList[position].price

        return view
    }
}