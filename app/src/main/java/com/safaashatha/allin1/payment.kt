package com.safaashatha.allin1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_editprofileuser.*
import kotlinx.android.synthetic.main.activity_editprofileuser.phone
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.fragment_addprod.*
import kotlinx.android.synthetic.main.fragment_store_add.*
import kotlinx.android.synthetic.main.productsdetails.*
import org.json.JSONObject

class payment : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var database2: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val b = intent.extras!!
        val userid= b.getString("user_id")
        val namee = b.getString("firstname")
        val lname=b.getString("lastname")
        val email=b.getString("email")
        val phone=b.getString("phone")
        val address=b.getString("address")


    }
    fun addcust() {
        val b = intent.extras!!
        val namee = b.getString("firstname")
        val lname = b.getString("lastname")
        val email = b.getString("email")
        val phone = b.getString("phone")
        val userid = b.getString("user_id")
        val address = b.getString("address")
        //val owner = b.getString("owner")

        setContentView(R.layout.showcart)
        val cartarraylist = ArrayList<product>()
        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("cart")
        // println("-######################################################################userid  "+ FirebaseAuth.getInstance()!!.uid)

        database.child(FirebaseAuth.getInstance()!!.uid!!).get().addOnSuccessListener {
            for (x in it.children)

                if (x.exists()) {
                    val pr = product(
                        x.child("name").value.toString(),
                        x.child("price").value.toString(),
                        x.child("category").value.toString(),
                        x.child("owner").value.toString()
                    )
                    var owner = x.child("owner").value.toString()
                    database2 =
                        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                            .getReference("shops")
                    database2.child( owner+ "/customers").child(namee.toString()).setValue(
                        user(
                            Firstname = namee.toString(),
                            Lastname = lname.toString(),
                            id = userid.toString()
                        )
                    )

                    //cartarraylist.add(pr)
//                            println("-######################################################################shatha  "+ cartarraylist.size)
                }




        }
    }
        fun isValid(cardNumber: String): Boolean {

            if (cardNumber.length != 16) {
                Toast.makeText(this, "The number card must be 16 digits!", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        fun isValidc(cvv: String): Boolean {

            if (cvv.length != 3) {
                Toast.makeText(this, "The CVV must be 3 digits!", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        fun pay(view: View) {

            if (month.getText().toString().trim().length < 2)
                Toast.makeText(this, "you must be write legal month", Toast.LENGTH_LONG).show();
            else {
                addcust()

                val b = intent.extras!!
                val userid = b.getString("user_id")
                FirebaseDatabase.getInstance().getReference("cart").child(userid.toString())
                    .removeValue()
                Toast.makeText(this, "its Done", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user_id", userid)
                startActivity(intent)
            }
        }

        //startPayment()




}