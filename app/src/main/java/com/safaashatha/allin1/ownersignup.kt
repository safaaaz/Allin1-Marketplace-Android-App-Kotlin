package com.safaashatha.allin1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.safaashatha.allin1.databinding.FragmentMystoreBinding
import kotlinx.android.synthetic.main.activity_editprofileuser.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_addprod.*
import kotlinx.android.synthetic.main.fragment_addprod.name1
import kotlinx.android.synthetic.main.fragment_editprof.*
import kotlinx.android.synthetic.main.fragment_mystore.*
import kotlinx.android.synthetic.main.fragment_store_add.*
import kotlinx.android.synthetic.main.fragment_store_add.Catagory
import kotlinx.android.synthetic.main.fragment_store_add.name
import kotlinx.android.synthetic.main.fragment_store_add.phone
import kotlinx.android.synthetic.main.list_products.view.*
import java.util.*
import kotlin.collections.ArrayList

class ownersignup : AppCompatActivity() {
    private lateinit var productsarrylistt:ArrayList<product>
    private lateinit var database: DatabaseReference
    private lateinit var binding: FragmentMystoreBinding
    lateinit var file_uri:Uri
    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ownersignup)
        val userid=intent.getStringExtra("user_id")
        val tempp=intent.getStringExtra("temp")
        binding = FragmentMystoreBinding.inflate(layoutInflater)
        if(tempp.equals("yes")) {
            readData()
            val view = findViewById<View>(R.id.Add_store)
            view.findNavController().navigate(R.id.action_blankFragment3_to_mystore)
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

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                if (resultCode == Activity.RESULT_OK && requestCode == 100){
                println("2222222222222222222222222222222222222222222"+ data?.data!!.hashCode())
                val fileUri=data?.data!!
                putim.setImageURI(fileUri)
                    val filename=FirebaseAuth.getInstance().currentUser!!.uid.toString()
                    val storref= FirebaseStorage.getInstance().getReference("productsimages/$filename/${name1.text.toString()}")
                    storref.putFile(fileUri).
                    addOnSuccessListener {
                        Toast.makeText(this,"success",Toast.LENGTH_LONG)
                    }.addOnFailureListener{
                        Toast.makeText(this,"failed",Toast.LENGTH_LONG)

                    }
                }}

    fun pickpic(view: View){
        openGalleryForImage()
    }

    fun logoutowner(view: View) {
                    FirebaseAuth.getInstance().signOut()

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }



    fun savestore(view: View) {
                    //val layout2 = findViewById(R.id.layout) as LinearLayout
                    FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                        "shops"
                    ).child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(
                        owner(
                            name.text.toString(),
                            Address.text.toString(),
                            Catagory.text.toString(),
                            phone.text.toString()
                        ), "products"
                    )
                    //name.setText("")
                   /* FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                        "shops"
                    ).child(FirebaseAuth.getInstance().currentUser!!.uid + "/products").child("1")
                        .setValue(
                            product(
                                "book", "for reading", "30"
                            )
                        )

                    FirebaseDatabase.getInstance().reference.child("300").setValue("tt")*/

                    view.findNavController().navigate(R.id.action_storeAdd_to_mystore)
                }

    fun addproduct(view: View) {
                    view.findNavController().navigate(R.id.action_mystore_to_addprod)
                }

    fun addprod(view: View) {
                    FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                        "shops"
                    ).child(FirebaseAuth.getInstance().currentUser!!.uid + "/products")
                        .child(name1.text.toString()).setValue(
                        product(
                            name1.text.toString(),FirebaseAuth.getInstance().currentUser!!.uid,about1.text.toString(), price1.text.toString(),  Catagory1.text.toString()
                        )
                    )
                    Toast.makeText(this@ownersignup, "The product is add", Toast.LENGTH_LONG).show()
                    startActivity(this.intent)
    }

    fun Cancel(view: View) {
                    view.findNavController().navigate(R.id.action_addprod_to_mystore)
}

    fun editprofile(view: View) {
        view.findNavController().navigate(R.id.action_mystore_to_editprof2)
    }

    fun editpeoff(view: View){
        if( !(TextUtils.isEmpty(name1.text.toString().trim { it <= ' ' })))
          {
              FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                  "shops"
              ).child(FirebaseAuth.getInstance().currentUser!!.uid).child("name").setValue(name1.text.toString())

          }

        if( !(TextUtils.isEmpty(addresss.text.toString().trim { it <= ' ' })))
        {
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                "shops"
            ).child(FirebaseAuth.getInstance().currentUser!!.uid).child("address").setValue(addresss.text.toString())

        }

        if( !(TextUtils.isEmpty(phone.text.toString().trim { it <= ' ' })))
        {
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                "shops"
            ).child(FirebaseAuth.getInstance().currentUser!!.uid).child("phone").setValue(phone.text.toString())

        }


        Toast.makeText(this@ownersignup, "The Profile is updatted ", Toast.LENGTH_LONG).show()
        startActivity(intent)


    }

    fun readData() {
        productsarrylistt = ArrayList()
        val userid=intent.getStringExtra("user_id")
        val emailid=intent.getStringExtra("email_id")


                    val databasee =
                        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                            .getReference("shops")
                    databasee.get().addOnSuccessListener {

                        for (x in it.children)

                            if (x.exists()) {
                                print("\n------------------------------------------------------lakahhhhhh-----------------\n"+userid.toString())
                                if (x.key == userid) {
                                    print("\n------------------------------------------------------lakah-----------------\n")
                                    for (prod in x.child("products").children) {
                                        val pr = product(
                                            prod.child("name").value.toString(),
                                            prod.child("price").value.toString(),
                                            prod.child("category").value.toString(),
                                        )
                                        productsarrylistt.add(pr)
                                    }

                                }
                            }

                        val listView: GridView = findViewById(R.id.productss)
                        print("\n---------------------------------------ssaass----------------\n"+productsarrylistt.size)
                        listView.setAdapter(productowadapter(this, productsarrylistt))
                    }


                }

    fun buy(view: View) {
        view.findNavController().navigate(R.id.action_mystore_to_editprof)
    }

    fun addstore(view: View) {

        view.findNavController().navigate(R.id.action_blankFragment3_to_storeAdd)
    }

}




