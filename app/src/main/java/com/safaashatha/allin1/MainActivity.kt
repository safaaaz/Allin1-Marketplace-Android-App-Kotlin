package com.safaashatha.allin1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.safaashatha.allin1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_blank.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsarrylist:ArrayList<product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userid=intent.getStringExtra("user_id")
        val emailid=intent.getStringExtra("email_id")

        binding=ActivityMainBinding.inflate(layoutInflater)
        //user_id.text="user id :: $userid"
        //email_id.text="email id:: $emailid"
        productsarrylist= ArrayList()
        productsarrylist.add(product("book","30","learning",R.drawable.img))
        productsarrylist.add(product("apple","20","food",R.drawable.googleg_standard_color_18))

        val reference = FirebaseDatabase.getInstance().getReference().child("shops")
        //val x=reference.get().addOnSuccessListener {
          //  Log.i("firebase", "Got value ${it.value}")}

        val productListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val post = dataSnapshot.child("products")
                    for (productsnapshot in post.children){
                        val product=productsnapshot.getValue<product>()
                        productsarrylist.add(product!!)
                    }


                }
                val post = dataSnapshot.getValue<owner>()
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        reference.addValueEventListener(productListener)
        //binding.products.isClickable=true
        products.adapter= productadapter(this,productsarrylist)
        //binding.products.setOnItemClickListener{parent, view, position, id->
        //    val name
        //}

        logoutuser.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }



    }
}