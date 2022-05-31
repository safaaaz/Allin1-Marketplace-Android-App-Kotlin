package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_addprod.*
import kotlinx.android.synthetic.main.productsowdetails.*
import kotlin.properties.Delegates

class productsowdetails : AppCompatActivity() {
    lateinit var prodname:String
    lateinit var prodprice:String
    lateinit var prodabout:String
    lateinit var prodcategory:String
    var prodimg by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //print("\n -----------------------------------------ayyyaaa----------------------------------\n")

        setContentView(R.layout.productsowdetails)
        val b = intent.extras!!
        prodname = b.getString("name")!!
        prodprice = b.getString("price")!!
        prodabout = b.getString("about")!!
        prodcategory=b.getString("category")!!
        prodimg = b.getInt("image")!!
            //productsname.text= prodname
            //productsprice.text = prodprice
        productsnamee.setText(prodname)
        productspricee.setText(prodprice)
        productssabout.setText(prodabout)
        productscategory.setText(prodcategory)

        productsimg.setImageResource(prodimg)
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "allin1"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun delete_prod(view: View) {
        val b = intent.extras!!
        val prodname = b.getString("name")
        val prodprice = b.getString("price")
        var pr = product(
            prodname.toString(), prodprice.toString(), "10"
        )

            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                "shops"
            ).child(FirebaseAuth.getInstance().currentUser!!.uid + "/products")
                .child(pr.name.toString()).removeValue()


        Toast.makeText(applicationContext, "Successfully deleted product", Toast.LENGTH_SHORT)
            .show()
        //view.findNavController().navigate(R.id.action_addprod_to_mystore)
        //startActivity(Intent(this,R.id.action_addprod_to_mystore::class.java))
        //finish()
        val intent = Intent(this, ownersignup::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("user_id", FirebaseAuth.getInstance()!!.uid)
        intent.putExtra("temp", "yes")

        startActivity(intent)
        finish()


    }



    fun edit_pro(view: View) {
        val b = intent.extras!!
        val prodname = b.getString("name")
       // val prodprice = b.getString("price")
        //var pr = product(
           // prodname.toString(), prodprice.toString(), "10"
        //)
        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
            "shops"
        ).child(FirebaseAuth.getInstance().currentUser!!.uid + "/products")
            .child(prodname.toString()).setValue(
                product(
                    name=productsnamee.text.toString(),
                    price = productspricee.text.toString(),
                    owner = FirebaseAuth.getInstance().currentUser!!.uid,
                    about = productssabout.text.toString(),
                    category =productscategory.text.toString()
                )
            )
        Toast.makeText(applicationContext, "Successfully edit product", Toast.LENGTH_SHORT)
            .show()
        val intent = Intent(this, ownersignup::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("user_id", FirebaseAuth.getInstance()!!.uid)
        intent.putExtra("temp", "yes")

        startActivity(intent)
        finish()




    }
}