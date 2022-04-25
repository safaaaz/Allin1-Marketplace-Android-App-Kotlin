package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.productsdetails.*

class productsdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productsdetails)
        val b = intent.extras!!
        val prodname = b.getString("name")
        val prodprice = b.getString("price")
        val prodimg = b.getInt("image")
        productsname.text = prodname
        productsprice.text = prodprice
        productsimg.setImageResource(prodimg)
    }

   // FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
    //"shops"
    //).child(FirebaseAuth.getInstance().currentUser!!.uid + "/products")
    //.child(productsname.text.toString()).removeValue()

}