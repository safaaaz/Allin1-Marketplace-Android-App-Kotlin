package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import com.google.firebase.database.FirebaseDatabase

class showcustomer : AppCompatActivity() {
    //val userid = intent.getStringExtra("user_id")
    private lateinit var customersarrylistt: ArrayList<user>
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcustomer)
        readData()
        val actionbar = supportActionBar
        actionbar!!.title = "Allin1"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

    fun readData() {
        customersarrylistt = ArrayList()
        val userid = intent.getStringExtra("user_id")


        val databasee =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops")
        databasee.get().addOnSuccessListener {

            for (x in it.children)

                if (x.exists()) {
                    //print("\n------------------------------------------------------lakahhhhhh-----------------\n"+userid.toString())
                    if (x.key == userid) {
                        //print("\n------------------------------------------------------lakah-----------------\n")
                        for (user1 in x.child("customers").children) {
                            val us = user(
                                Firstname = user1.child("firstname").value.toString(),
                                Address = user1.child("address").value.toString(),
                                phone=user1.child("phone").value.toString(),
                            )
                            customersarrylistt.add(us)
                        }

                    }
                }

            val listView: ListView = findViewById(R.id.customers)
            print("\n---------------------------------------mmmmmmmmmm----------------\n" + customersarrylistt.size)
            listView.setAdapter(useradapter(this, customersarrylistt))
        }
    }

}