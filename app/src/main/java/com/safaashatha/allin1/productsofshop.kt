package com.safaashatha.allin1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_productsofshop.*
import kotlinx.android.synthetic.main.productsdetails.*

class productsofshop:  AppCompatActivity() {
    private lateinit var productsarrylist: ArrayList<product>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productsofshop)
        val b = intent.extras!!
        var prodname = b.getString("prodId")
        showprod(prodname.toString())
        database.child(prodname.toString()).get().addOnSuccessListener {
            shopsname.text=it.child("name").value.toString()
            shopsabout.text=it.child("about").value.toString()
            shopsaddress.text=shopsaddress.text.toString()+it.child("address").value.toString()
            shopscategory.text=shopscategory.text.toString()+it.child("category").value.toString()
            shopsphone.text=shopsphone.text.toString()+it.child("phone").value.toString()
        }
        val actionbar = supportActionBar
        actionbar!!.title = "Allin1"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        val search = menu!!.findItem(R.id.prodsearch)
        val editprod=menu!!.findItem(R.id.editprofile)
        search.isVisible=false
        editprod.isVisible=true
        val cat = menu!!.findItem(R.id.categories)
        cat.isVisible=false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()

                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                true
            }
            R.id.editprofile -> {
                startActivity(Intent(this,editprofileuser::class.java))
                true
            }
            R.id.homepage->{
                startActivity(Intent(this,MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun showprod(newtext:String) {
        var flag = 0
        productsarrylist = ArrayList()
        //noprod.text=""
        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops")
        database.get().addOnSuccessListener {
            for (x in it.children) {

                if (x.exists()) {
                    if(x.key.equals(newtext))
                        for (prod in x.child("products").children) {

                            val pr = product(
                                name = prod.child("name").value.toString(),
                                owner = prod.child("owner").value.toString(),
                                price = prod.child("price").value.toString(),
                                category = prod.child("category").value.toString(),
                                about = prod.child("about").value.toString()
                                //R.drawable.img
                            )
                            productsarrylist.add(pr)
                        }
                }
            }
            val listView: GridView = findViewById(R.id.products)
            listView.setAdapter(productadapter(this, productsarrylist))
        }
    }
}