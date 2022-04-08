package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.productsdetails.*

class productsdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productsdetails)
        val b=intent.extras!!
        val prodname=b.getString("name")
        val prodprice=b.getString("price")
        val prodimg=b.getInt("image")
        productsname.text=prodname
        productsprice.text=prodprice
        productsimg.setImageResource(prodimg)
    }
}