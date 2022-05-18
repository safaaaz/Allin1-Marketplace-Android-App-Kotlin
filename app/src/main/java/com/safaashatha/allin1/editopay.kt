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
        firstnameinput.setText(intent.getStringExtra("name"))
        //print("\nllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll\n "+intent.getStringExtra("name").toString()+"\npppppppppppppppp\n")
        lastnameinput.setText(intent.getStringExtra("lastname"))
        emailinput.setText(intent.getStringExtra("email"))
        addressinput.setText(intent.getStringExtra("aaddress"))
        phoneinput.setText(intent.getStringExtra("phone"))



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
                    val userr=FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                        "Users"
                    ).child(FirebaseAuth.getInstance().currentUser!!.uid)
                    userr.child("Firstname")
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
                                phone=phoneinput.text.toString()
                            )
                        )
                    Toast.makeText(
                        this,
                        "Your information has been saved successfully ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            val intent = Intent(this, MainActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
            //intent.putExtra("temp", "yes")

            //intent.putExtra("email_id", email)

            startActivity(intent)
            finish()
        }
    }


}

