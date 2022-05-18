package com.safaashatha.allin1

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.commit_list.*
import kotlinx.android.synthetic.main.productsdetails.*
import java.io.File
import android.widget.RatingBar

import android.widget.RatingBar.OnRatingBarChangeListener
import com.google.firebase.database.DatabaseReference
import com.safaashatha.allin1.databinding.ActivityMainBinding
import android.widget.LinearLayout





class productsdetails : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsarrylist:ArrayList<product>
    lateinit var currentproduct:product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productsdetails)

        val b = intent.extras!!
        val prodname = b.getString("name")
        val prodprice = b.getString("price")
        val prodabout = b.getString("about")
        val prodowner = b.getString("owner")
        val prodrate = b.getInt("rating",0)
        val prodraters = b.getInt("numraters",0)
        val storref = FirebaseStorage.getInstance().reference.child("productsimages/"+prodowner+"/"+prodname)
        val localfile = File.createTempFile("tempimage", "jpg")
        storref.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            productsimg.setImageBitmap(bitmap)
        }
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "New Activity"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        productsname.text = prodname
        productsabout.text=prodabout
        productsprice.text = prodprice
        if(prodraters!!.toInt()==0){
            productsrate.setText("no rating")
        }
        else {
            rating(prodrate,prodraters)
        }
        var database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops").child(prodowner!!)
        database.get().addOnSuccessListener {
            shopname.text = "store name: " +it.child("name").value.toString()
            shopabout.text = it.child("about").value.toString()
            shopphone.text = "phone: "+ it.child("phone").value.toString()
            shopaddress.text = "address: "+ it.child("address").value.toString()
        }
        loadcommits()


        ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                Toast.makeText(this,"your rate "+ratingBar.rating.toInt()+" stars ",Toast.LENGTH_LONG).show()

                var database =
                    FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("shops").child(intent.getStringExtra("owner")!!)
                        .child("products").child(productsname.text.toString())
                database.child("rating").setValue(intent.getIntExtra("rating",0)+ratingBar.rating)
                database.child("numraters").setValue(intent.getIntExtra("numraters",0)+1)
                rating(intent.getIntExtra("rating",0)+ratingBar.rating.toInt(),intent.getIntExtra("numraters",0).toInt()+1)

            }
        like_button_cb.setOnClickListener {
            like_button_cb.setBackgroundResource(R.drawable.fav)
            val usercart = FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("favorites")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            usercart.child(currentproduct.name!!).setValue(currentproduct)
                .addOnSuccessListener {
                    Toast.makeText(this,"Success add to favorites", Toast.LENGTH_LONG).show()

            }


                }
        }
        more.setOnClickListener {
            val params: ViewGroup.LayoutParams =peoplecomments.layoutParams
            params.height = params.height+100
            params.width = params.width+100
            peoplecomments.layoutParams = params
        }
        }

    fun rating(prodrate:Int,prodraters:Int){
        productsrate.setText("rating: " + (prodrate/ prodraters).toString())
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
            R.id.favorites->{
                Toast.makeText(this,"you arrived to step 1",Toast.LENGTH_LONG).show()
                readfavoritesprod()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
        if(writecomm.text.toString()!="") {
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

            for(x in it.child("commits").children){
               var commexplain= x.child("explain").value
               var commuser=x.child("username").value
                commitsarraylist.add(commit(commexplain.toString(),commuser.toString()))
            }
            val listVieww: ListView = findViewById(R.id.peoplecomments!!)
            listVieww.setAdapter(commitadapter(this, commitsarraylist))
             }
        }

    fun gostore(view: View) {
        val b = intent.extras!!
        val shopid = b.getString("owner")
        var newtext=productsname.text.toString()
        val intent = Intent(this, productsofshop::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("prodId", shopid)
        startActivity(intent)
        finish()
    }

    fun readfavoritesprod() {
        startActivity(Intent(this,userfavorites::class.java))

    }
}