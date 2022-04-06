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
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
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
        readData()
        val reference = FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("shops")



        logoutuser.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
    fun readData() {
        productsarrylist = ArrayList()
        val c=product("book","30","learning",R.drawable.img)
        productsarrylist.add(c)

        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops")
        database.get().addOnSuccessListener {
            for (x in it.children)

                if (x.exists()) {
                    for (prod in x.child("products").children) {
                        val pr = product(
                            prod.child("name").value.toString(),
                            prod.child("price").value.toString(),
                            prod.child("category").value.toString(),
                            R.drawable.img
                        )
                        productsarrylist.add(pr)
                        //println("-######################################################################shatha  "+ productsarrylist.size)

                    }
                }
            //println("---------------------------------------------------------------------safa  "+ productsarrylist.size)
            val listView: ListView = findViewById(R.id.products)
            listView.setAdapter(productadapter(this, productsarrylist))
        }
        //println("---------------------------------------------------------------------shatha  "+ productsarrylist.size)

        //products.adapter = productadapter(this, productsarrylist)


        /*var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto")
        val adapter = ArrayAdapter(
            this,
            R.layout.listview_item, array
        )
*/

       /* listView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {

                // value of item that is clicked
                val itemValue = listView.getItemAtPosition(position) as String

                // Toast the values
                Toast.makeText(
                    applicationContext,
                    "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG
                )
                    .show()
            }

        }*/

    }}