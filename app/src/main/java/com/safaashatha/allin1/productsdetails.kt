package com.safaashatha.allin1

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.commit_list.*
import kotlinx.android.synthetic.main.productsdetails.*
import java.io.File

class productsdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productsdetails)
        val b = intent.extras!!
        val prodname = b.getString("name")
        val prodprice = b.getString("price")
        val prodabout = b.getString("about")
        val prodowner = b.getString("owner")
        val storref = FirebaseStorage.getInstance().reference.child("productsimages/"+prodowner+"/"+prodname)
        val localfile = File.createTempFile("tempimage", "jpg")
        storref.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            productsimg.setImageBitmap(bitmap)
        }

        productsname.text = prodname
        productsabout.text=prodabout
        productsprice.text = prodprice
        var database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops").child(prodowner!!)
        database.get().addOnSuccessListener {
            shopname.text = it.child("name").value.toString()
            shopabout.text = it.child("about").value.toString()
            shopphone.text = it.child("phone").value.toString()
            shopaddress.text = it.child("address").value.toString()
        }
        loadcommits()
        }



    fun addtocart(view: View) {
        /* add product to the cart of the user */
        val usercart = FirebaseDatabase.getInstance().getReference("cart")
            .child(intent.getStringExtra("user_id").toString())
        usercart.child("1").setValue(product(productsname.text.toString(),productsprice.text.toString(),"vv"))
            .addOnSuccessListener {
                Toast.makeText(this,"Success add to cart", Toast.LENGTH_LONG).show()

            }
    }

    fun save_commit(view: View){
        addcommit()
        loadcommits()

    }

    fun addcommit(){
        if(writecomm.text.toString()!=null) {
            var database =
                FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .getReference("shops").child(intent.getStringExtra("owner")!!)
                    .child("products").child(productsname.text.toString()).child("commits")
                    .child(writecomm.text.toString()).setValue(commit(writecomm.text.toString(),
                        FirebaseAuth.getInstance().currentUser!!.email.toString()))
            writecomm.setText("")
            Toast.makeText(this,"your comment has been successfully save",Toast.LENGTH_LONG).show()
        }
    }
    fun loadcommits() {
        var commitsarraylist: ArrayList<commit> = ArrayList()
        //commitsarraylist.add(commit("first commit","safaa"))
        println("ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp")

        var database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops").child(intent.getStringExtra("owner")!!)
                .child("products").child(productsname.text.toString())
        database.get().addOnSuccessListener {
            if(!it.child("commits").exists()){
                FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .getReference("shops").child(intent.getStringExtra("owner")!!)
                    .child("products").child(productsname.text.toString()).child("commits").child("e").setValue(commit())
            }
            print("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+it.child("commits"))

            for(x in it.child("commits").children){
               var commexplain= x.child("explain").value
               var commuser=x.child("username").value
                commitsarraylist.add(commit(commuser.toString(),commexplain.toString()))
                print("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+commitsarraylist.size)

            }
            val listVieww: ListView = findViewById(R.id.peoplecomments!!)
            listVieww.setAdapter(commitadapter(this, commitsarraylist))
             }
        }
    fun gostore(view: View)
    {
        val b = intent.extras!!
        val prodid = b.getString("owner")
        var newtext=productsname.text.toString()
        val intent = Intent(this, productsofshop::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("prodId", prodid)
        startActivity(intent)
        finish()

    }

}