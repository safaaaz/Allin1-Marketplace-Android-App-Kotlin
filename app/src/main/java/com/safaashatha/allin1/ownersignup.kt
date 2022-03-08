package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
            ,about.text.toString(),Address.text.toString(),Catagory.text.toString(),phone.text.toString()),"products")
        //name.setText("")
        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(FirebaseAuth.getInstance().currentUser!!.uid+"/products").child("1").setValue(product(
            "book","for reading","30","learning"))

        FirebaseDatabase.getInstance().reference.child("300").setValue("tt")
    }
}