package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

    fun addtocart(view: View) {
        val usercart = FirebaseDatabase.getInstance().getReference("cart")
            .child(intent.getStringExtra("user_id").toString())
        usercart.child("1").setValue(product(productsname.text.toString(),productsprice.text.toString(),"vv"))
            .addOnSuccessListener {
                Toast.makeText(this,"Success add to cart", Toast.LENGTH_LONG).show()

            }
    }



}