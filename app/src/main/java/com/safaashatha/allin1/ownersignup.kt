package com.safaashatha.allin1

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_main.logoutuser
import kotlinx.android.synthetic.main.fragment_addprod.*
import kotlinx.android.synthetic.main.fragment_mystore.*
import kotlinx.android.synthetic.main.fragment_store_add.*
import kotlinx.android.synthetic.main.fragment_store_add.Catagory
import kotlinx.android.synthetic.main.fragment_store_add.name

class ownersignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ownersignup)
        val userid=intent.getStringExtra("user_id")
        if(userid!=null){
            val view=findViewById<View>(R.id.Add_store)
            view.findNavController().navigate(R.id.action_blankFragment3_to_mystore)

        }

    }
    fun logoutowner(view: View){
        FirebaseAuth.getInstance().signOut()

        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
    fun addstore(view: View) {

        view.findNavController().navigate(R.id.action_blankFragment3_to_storeAdd)
    }



    fun savestore(view: View){
        //val layout2 = findViewById(R.id.layout) as LinearLayout
        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("shops").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(owner(name.text.toString()
            ,Address.text.toString(),Catagory.text.toString(),phone.text.toString()),"products")
        //name.setText("")
        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("shops").child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child("1").setValue(product(
            "book","for reading","30"))

        FirebaseDatabase.getInstance().reference.child("300").setValue("tt")

        view.findNavController().navigate(R.id.action_storeAdd_to_mystore)
    }

    fun addproduct(view: View) {
        view.findNavController().navigate(R.id.action_mystore_to_addprod)

        //val button = findViewById<Button>(R.id.Add_product)

            // button?.setOnClickListener()
        //{

           // Toast.makeText(this@ownersignup, "shatha", Toast.LENGTH_LONG).show() }

        //val layout1 = findViewById(R.id.layout) as LinearLayout
        //val name = TextView(this)


        //name.layoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //name.text = "Name"

        //layout1.addView(name)

        //save_button.setOnClickListener{FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child("1").setValue(product(
          //  "book","for reading","30"))}
        //button_add.setOnClickListener()
        //{

        //Toast.makeText(this@ownersignup, "The product is add", Toast.LENGTH_LONG).show()
          //  FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child(editText.text.toString()).setValue(product(
              //  editText.text.toString(),price1.text.toString(),catagory1.text.toString()
            //))
        //}


    }
    fun addprod(view: View){


        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("shops").child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child(name1.text.toString()).setValue(product(
             name1.text.toString(),price1.text.toString(),Catagory1.text.toString()))
        Toast.makeText(this@ownersignup, "The product is add", Toast.LENGTH_LONG).show()
        view.findNavController().navigate(R.id.action_addprod_to_mystore)


    }
    fun Cancel(view: View){


        view.findNavController().navigate(R.id.action_addprod_to_mystore)


    }


}

