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
import com.google.firebase.database.ktx.getValue
import com.safaashatha.allin1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_blank.*

import android.R.string.no
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
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

        //productsarrylist.add(product("apple","20","food",R.drawable.googleg_standard_color_18))
        readData()
        val reference = FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("shops")
/*
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                productsarrylist=ArrayList()
                for (postSnapshot in dataSnapshot!!.children) {
                    productsarrylist.remove(c)
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
                println("The read failed: " + databaseError.code)
            }
        })*/
        //val x=reference.get()
        //productsarrylist=ArrayList()
        /*
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("firebaseeeeeeeee", "onDataChange ${snapshot.value.toString()}" )
                productsarrylist.add(product("apple1","20","food",R.drawable.googleg_standard_color_18))

                for (postSnapshot in snapshot!!.children) {
                    productsarrylist.remove(c)
                    val post = postSnapshot.child("products")
                    productsarrylist.add(product("apple2","20","food",R.drawable.googleg_standard_color_18))

                    for (productsnapshot in post.children){
                        val product=productsnapshot.getValue<product>()
                        productsarrylist.add(product!!)
                        productsarrylist.add(product("apple3","20","food",R.drawable.googleg_standard_color_18))
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebasefffff", "onCancelled ${error.message}" )

            }
        })
        reference.get().addOnCompleteListener { task ->
            productsarrylist.add(product("apple1","20","food",R.drawable.googleg_standard_color_18))
            if (task.isSuccessful) {
                val snapshot = task.result
                productsarrylist.add(product("apple1","20","food",R.drawable.googleg_standard_color_18))

                for (postSnapshot in snapshot!!.children) {
                    productsarrylist.remove(c)
                    val post = postSnapshot.child("products")
                    productsarrylist.add(product("apple2","20","food",R.drawable.googleg_standard_color_18))

                    for (productsnapshot in post.children){
                        val product=productsnapshot.getValue<product>()
                        productsarrylist.add(product!!)
                        productsarrylist.add(product("apple3","20","food",R.drawable.googleg_standard_color_18))
                    }


                }
            } else {
                Log.d("TAGGGGG", task.exception!!.message!!) //Don't ignore potential errors!
            }
        }
        // ...*/
            //.addOnSuccessListener {
          //  Log.i("firebase", "Got value ${it.value}")}
        //reference.addValueEventListener(productListener)
        //binding.products.isClickable=true
        //binding.products.setOnItemClickListener{parent, view, position, id->
        //    val name
        //}

        logoutuser.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }



    }
    fun readData() {
        productsarrylist= ArrayList()
        val c=product("book","30","learning",R.drawable.img)
        productsarrylist.add(c)
        print("6666666666666666666666666666666666666666666666666666666666")
        database = FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("shops")

        database.get().addOnSuccessListener {
        for (x in it.children)

            if (x.exists()) {
                for(prod in x.child("products").children){
                    //val product=prod.getValue<product>()
                    println("-------------------------------------------------------------------------------------")
                    println(prod.value)
                    println("-------------------------------------------------------------------------------------")

                    productsarrylist.add(product(prod.child("name").value.toString(),prod.child("price").value.toString(),prod.child("category").value.toString(),R.drawable.googleg_standard_color_18))


                }
                //val firstname = x.child("products").value


            }
    }
        for (i in productsarrylist){
            println("-------------------------------------------------------------------------------------")
            print(i)
            println("-------------------------------------------------------------------------------------")


        }

        products.adapter= productadapter(this,productsarrylist)

    }

}