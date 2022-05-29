package com.safaashatha.allin1

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.FirebaseDatabase

class shopsadapter (private val context: Activity, private val arrayList: ArrayList<owner>):
    ArrayAdapter<owner>(context,
    R.layout.list_shops,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_shops, parent, false);

        val nameview:TextView=view.findViewById(R.id.storename)
        val aboutview:TextView=view.findViewById(R.id.storeabout)

        nameview.text=arrayList[position].name
        aboutview.text=arrayList[position].about

        nameview.setOnClickListener {
            Toast.makeText(context,"on setonclick",Toast.LENGTH_LONG).show()

            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops").get().addOnSuccessListener {
                for (x in it.children){
                    //Toast.makeText(context,"on for",Toast.LENGTH_LONG).show()
                    if (x.exists()) {
                        //Toast.makeText(context,"on if x exist",Toast.LENGTH_LONG).show()

                        if(x.child("name").value==arrayList[position].name){
                            Toast.makeText(context,"on if child == name",Toast.LENGTH_LONG).show()

                            val intent = Intent(context, productsofshop::class.java)
                            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("prodId", x.key)
                            context!!.startActivity(intent)

                    }}
            }
        }
    }
        return view

    }

}