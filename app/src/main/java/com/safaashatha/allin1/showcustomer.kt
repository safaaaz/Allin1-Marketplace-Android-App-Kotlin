package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.google.firebase.database.FirebaseDatabase

class showcustomer : AppCompatActivity() {
    //val userid = intent.getStringExtra("user_id")
    private lateinit var customersarrylistt: ArrayList<user>
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcustomer)
        readData()
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
                                user1.child("Firstname").value.toString(),
                                user1.child("Address").value.toString(),
                                user1.child("phone").value.toString(),
                            )
                            customersarrylistt.add(us)
                        }

                    }
                }

            val listView: GridView = findViewById(R.id.customers)
            print("\n---------------------------------------ssaass----------------\n" + customersarrylistt.size)
            listView.setAdapter(useradapter(this, customersarrylistt))
        }
    }

}