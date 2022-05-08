package com.safaashatha.allin1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.productsdetails.*
import java.io.File

class productsdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productsdetails)

        val b = intent.extras!!
        val prodname = b.getString("name")
        val prodprice = b.getString("price")
        val storref = FirebaseStorage.getInstance().reference.child("productsimages/"+b.getString("owner")+"/"+prodname)
        print("5555555555555555555555555555555555555555555555555555555\n"+b.getString("owner"))
        val localfile = File.createTempFile("tempimage", "jpg")
        storref.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            productsimg.setImageBitmap(bitmap)
            print("99999999999999999999999999999999999999999999raratsrs")
        }

        productsname.text = prodname
        productsprice.text = prodprice

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