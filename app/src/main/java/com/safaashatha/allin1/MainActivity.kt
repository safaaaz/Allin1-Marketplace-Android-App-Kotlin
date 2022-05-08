package com.safaashatha.allin1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import android.graphics.ColorSpace.get
import android.nfc.tech.NfcA.get
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_store_add.*
import kotlinx.android.synthetic.main.productsdetails.*
import kotlinx.android.synthetic.main.showcart.*
import kotlinx.android.synthetic.main.showcart.view.*
import android.widget.TextView





class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsarrylist:ArrayList<product>
    var userid:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userid=intent.getStringExtra("user_id")
        val emailid=intent.getStringExtra("email_id")

        binding=ActivityMainBinding.inflate(layoutInflater)
        readData()
        val reference = FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("shops")




    }
    fun readData() {
        productsarrylist = ArrayList()
        //val c=product("book","30","learning",R.drawable.img)
        //productsarrylist.add(c)

        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops")
        database.get().addOnSuccessListener {
            for (x in it.children)

                if (x.exists()) {
                    for (prod in x.child("products").children) {
                        val pr = product(
                            name=prod.child("name").value.toString(),
                            owner=prod.child("owner").value.toString(),
                            price=prod.child("price").value.toString(),
                            category = prod.child("category").value.toString(),

                        )
                        productsarrylist.add(pr)

                    }
                }
            val listView: GridView = findViewById(R.id.products)
            listView.setAdapter(productadapter(this, productsarrylist))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        val search = menu!!.findItem(R.id.prodsearch)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //products.adapter.filter.filter("hhh")
                searchforprod(newText!!)
                return true
            }
        })
        return true
    }
//for shatha
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.books -> {
                Toast.makeText(this,"you click books category",Toast.LENGTH_LONG).show()
                showproductsbycategory("books")
                true
            }
            R.id.clothes -> {
                Toast.makeText(this,"you click clothes category",Toast.LENGTH_LONG).show()
                showproductsbycategory("clothes")
                true
            }
            R.id.nails -> {
                Toast.makeText(this,"you click nails category",Toast.LENGTH_LONG).show()
                showproductsbycategory("nails")
                true
            }
            R.id.home -> {
                Toast.makeText(this,"you click home category",Toast.LENGTH_LONG).show()
                showproductsbycategory("home")
                true
            }
            R.id.cart -> {
                showcart()
                true
            }
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()

                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                true
            }
            R.id.editprofile -> {
                val intent=Intent(this,editprofileuser::class.java)
                intent.putExtra("user_id", FirebaseAuth.getInstance()!!.uid)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun showcart(){
        //nocart.text=" "
        setContentView(R.layout.showcart)
        val cartarraylist=ArrayList<product>()
        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("cart")
        println("-######################################################################userid  "+ FirebaseAuth.getInstance()!!.uid)

        database.child(FirebaseAuth.getInstance()!!.uid!!).get().addOnSuccessListener {
            for (x in it.children)

                if (x.exists()) {
                            val pr = product(
                                x.child("name").value.toString(),
                                x.child("price").value.toString(),
                                x.child("category").value.toString(),
                            )
                            cartarraylist.add(pr)
                            println("-######################################################################shatha  "+ cartarraylist.size)
                        }


        if(cartarraylist.size==0){
            nocart.text="there is no products in this category"
        }

        //println("---------------------------------------------------------------------safa  "+ productsarrylist.size)
        val listVieww: ListView = findViewById(R.id.cartlist!!)
        listVieww.setAdapter(productadapter(this, cartarraylist))
        }}


    fun showproductsbycategory(cat:String){
        prodcattext.text=cat
        productsarrylist = ArrayList()
        noprod.text=""
        //val c=product("book","30","learning",R.drawable.img)
        //productsarrylist.add(c)

        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops")
        database.get().addOnSuccessListener {
            for (x in it.children)

                if (x.exists()) {
                    for (prod in x.child("products").children) {
                        if(prod.child("category").value.toString()==cat) {
                            val pr = product(
                                prod.child("name").value.toString(),
                                prod.child("price").value.toString(),
                                prod.child("category").value.toString(),
                            )
                            productsarrylist.add(pr)
                            println("-######################################################################shatha  "+ productsarrylist.size)
                        }
                    }
                    if(productsarrylist.size==0){
                        noprod.text="there is no products in this category"
                    }
                }
            //println("---------------------------------------------------------------------safa  "+ productsarrylist.size)
            val listView: GridView = findViewById(R.id.products)
            listView.setAdapter(productadapter(this, productsarrylist))
        }
    }

    fun searchforprod(newtext:String){
        prodcattext.text=newtext
        productsarrylist = ArrayList()
        noprod.text=""
        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops")
        database.get().addOnSuccessListener {
            for (x in it.children)

                if (x.exists()) {
                    for (prod in x.child("products").children) {
                        if(newtext in prod.child("name").value.toString()) {
                            val pr = product(
                                prod.child("name").value.toString(),
                                prod.child("price").value.toString(),
                                prod.child("category").value.toString(),
                            )
                            productsarrylist.add(pr)
                            println("-######################################################################shatha  "+ productsarrylist.size)
                        }
                    }
                    if(productsarrylist.size==0){
                        noprod.text="there is no products in this category"
                    }
                }
            //println("---------------------------------------------------------------------safa  "+ productsarrylist.size)
            val listView: GridView = findViewById(R.id.products)
            listView.setAdapter(productadapter(this, productsarrylist))

        }
    }

}