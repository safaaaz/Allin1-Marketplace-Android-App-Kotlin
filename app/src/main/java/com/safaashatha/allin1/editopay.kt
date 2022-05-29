package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_editprofileuser.*
import kotlinx.android.synthetic.main.activity_editprofileuser.password
import kotlinx.android.synthetic.main.activity_login.*

class editopay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editopay)
        val b = intent.extras!!
        val name = b.getString("name")
        print("\n mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm\n"+name.toString()+"\nmmmmmmmmmmmmmmmmmm\n")
        val lastname = b.getString("lastname")
        val address = b.getString("aaddress")
        val email = b.getString("email")
        val phone = b.getString("phone")
        if(name!=null) {
            firstnameinput.setText(name)
        }
        if(lastname!="null") {
            lastnameinput.setText(lastname)
        }
        if(email!="null") {
            emailinput.setText(email)
        }
        if(address!="null") {
            addressinput.setText(address)
        }
        if(phone!=null) {
            phoneinput.setText(phone)
        }


        saveprofile.setOnClickListener {

            when {
                TextUtils.isEmpty(firstnameinput.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter first name", Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(lastnameinput.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter last name", Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(emailinput.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter email", Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(addressinput.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter address", Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(phoneinput.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter phone", Toast.LENGTH_LONG
                    ).show()
                }
                else -> {


                    val editt =
                        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                            "Users"
                        ).child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(
                            user(
                                FirebaseAuth.getInstance().currentUser!!.uid,
                                Firstname = firstnameinput.text.toString(),
                                Lastname=lastnameinput.text.toString(),
                                Email=emailinput.text.toString(),
                                Address=addressinput.text.toString(),
                                phone=phoneinput.text.toString(),

                            )
                        )

                    Toast.makeText(
                        this,
                        "Your information has been saved successfully ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            val b = intent.extras!!
            val owner = b.getString("owner")
            val intent = Intent(this, payment::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
            intent.putExtra("firstname", firstnameinput.text.toString())
            intent.putExtra("lastname", lastnameinput.text.toString())
            intent.putExtra("email",emailinput.text.toString())
            intent.putExtra("address",addressinput.text.toString() )
            intent.putExtra("phone",phoneinput.text.toString() )
            intent.putExtra("owner", owner)





            //intent.putExtra("temp", "yes")

            //intent.putExtra("email_id", email)

            startActivity(intent)
            finish()
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



}

