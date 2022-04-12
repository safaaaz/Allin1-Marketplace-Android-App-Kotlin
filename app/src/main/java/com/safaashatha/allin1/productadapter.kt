package com.safaashatha.allin1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.list_products.view.*
import kotlinx.android.synthetic.main.productsdetails.view.*

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

            val product=arrayList[position]
            val inflator=context!!.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var productview=inflator.inflate(R.layout.productsdetails,null)

        view.productimage.setOnClickListener(){
                var intent= Intent(context,productsdetails::class.java)
                intent.putExtra("image",product.image!!)
                intent.putExtra("name",product.name!!)
                intent.putExtra("price",product.price!!)
                print("[[[[[[[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]][[[[[[[[[[[[[[[[[[[[[[[\n\n"+position.toString())
                intent.putExtra("user_id", FirebaseAuth.getInstance()!!.uid)
                context!!.startActivity(intent)

            }

        return view
    }
}