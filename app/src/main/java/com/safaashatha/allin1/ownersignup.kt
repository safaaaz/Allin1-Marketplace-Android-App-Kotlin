package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.view.ViewGroup
import android.view.Gravity

import android.widget.LinearLayout


import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_store_add.*

class ownersignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ownersignup)


    }
    fun addstore(view: View) {

        view.findNavController().navigate(R.id.action_blankFragment3_to_storeAdd)
    }



    fun savestore(view: View){
        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(owner(name.text.toString()
            ,Address.text.toString(),Catagory.text.toString(),phone.text.toString()),"products")
        //name.setText("")
        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child("1").setValue(product(
            "book","for reading","30"))

        FirebaseDatabase.getInstance().reference.child("300").setValue("tt")
        view.findNavController().navigate(R.id.action_storeAdd_to_mystore)
    }

    fun addproduct(view: View) {
        //val button = findViewById<Button>(R.id.Add_product)

            // button?.setOnClickListener()
        //{

           // Toast.makeText(this@ownersignup, "shatha", Toast.LENGTH_LONG).show() }

        val layout1 = findViewById(R.id.layout) as LinearLayout
        val name = TextView(this)
        val editText = EditText(this)
        val button_add = Button(this)
        val catagory = TextView(this)
        val catagory1 = EditText(this)
        val price = TextView(this)
        val price1 = EditText(this)

        name.layoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        name.text = "Name"
        editText.setHint("Enter name product")
        editText.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        editText.setPadding(20, 20, 20, 20)

        catagory.layoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        catagory.text = "Catagory"

        catagory1.setHint("Enter Catagory")
        catagory1.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        catagory1.setPadding(20, 20, 20, 20)

        price.layoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        price.text = "Price"
        price1.setHint("Enter Price")
        price1.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        price1.setPadding(20, 20, 20, 20)


        button_add.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        button_add.text = "Add Product"

        layout1.addView(name)
        layout1.addView(editText)
        layout1.addView(catagory)
        layout1.addView(catagory1)
        layout1.addView(price)
        layout1.addView(price1)
        layout1.addView(button_add)
        //save_button.setOnClickListener{FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child("1").setValue(product(
          //  "book","for reading","30"))}
        button_add.setOnClickListener()
        {

        Toast.makeText(this@ownersignup, "The product is add", Toast.LENGTH_LONG).show()
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child("1").setValue(product(
                editText.text.toString(),price1.text.toString(),catagory1.text.toString()
            ))
        }


    }

    }

