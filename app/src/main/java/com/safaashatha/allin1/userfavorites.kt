package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.safaashatha.allin1.databinding.ActivityMainBinding

class userfavorites : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var productsarrylist:ArrayList<product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userfavorites)
        readfavoritesprod()
        val actionbar = supportActionBar
        actionbar!!.title = "Allin1"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true


    }

    fun readfavoritesprod() {
        productsarrylist = ArrayList()
        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("favorites").child(FirebaseAuth.getInstance().currentUser!!.uid)
        database.get().addOnSuccessListener {
            for (prod in it.children){
                        var rating=0
                        var numraters=0
                        if(prod.child("numraters").exists()){
                            numraters=Integer.valueOf( prod.child("numraters").value.toString())
                            rating=Integer.valueOf( prod.child("rating").value.toString())
                        }
                        val pr = product(
                            name=prod.child("name").value.toString(),
                            owner=prod.child("owner").value.toString(),
                            price=prod.child("price").value.toString(),
                            category = prod.child("category").value.toString(),
                            rating= Integer.valueOf( rating ),
                            numraters = Integer.valueOf( numraters ),
                            about = prod.child("about").value.toString()
                        )
                        productsarrylist.add(pr)

                    }
            val listView: GridView = findViewById(R.id.favproducts)
            listView.setAdapter(productadapter(this, productsarrylist))
                }

    }

}
