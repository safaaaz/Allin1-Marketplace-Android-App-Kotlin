package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult

import android.webkit.WebView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import java.security.acl.Owner

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val database = FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app")
        val myRef = database.reference
        //myRef.setValue("shops")
        login_s.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

        }
        businessaccount.setOnClickListener {
            when {
                TextUtils.isEmpty(EmailSignup.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter email", Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(PasswordSignup.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter password", Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val email = EmailSignup.text.toString().trim { it <= ' ' }
                    val pass = PasswordSignup.text.toString().trim { it <= ' ' }

                    //create user

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    //var database=FirebaseDatabase.getInstance().reference
                                    //database.setValue("Owners")
                                    FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("Owners").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(owner(EmailSignup.text.toString()
                                        ,password_text_s.text.toString()))
                                    val firebaseuser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this, "you are registered successfully.", Toast.LENGTH_LONG
                                    ).show()


                                    val intent =(Intent(this, ownersignup::class.java))

                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseuser.uid)
                                    intent.putExtra("email_id", firebaseuser.email)

                                    startActivity(intent)
                                    //finish()
                                } else {
                                    Toast.makeText(
                                        this, task.exception!!.message.toString(), Toast.LENGTH_LONG
                                    ).show()

                                }
                            }
                        )
                }


            }


        }
        Register_button.setOnClickListener {
            when {
                TextUtils.isEmpty(EmailSignup.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter email", Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(PasswordSignup.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter password", Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val email = EmailSignup.text.toString().trim { it <= ' ' }
                    val pass = PasswordSignup.text.toString().trim { it <= ' ' }

                    //create user

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    var database=FirebaseDatabase.getInstance().reference
                                    database.setValue("Users")
                                    FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child("Users").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(owner(EmailSignup.text.toString()
                                        ,password_text_s.text.toString()))
                                    val firebaseuser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this, "you are registered successfully.", Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseuser.uid)
                                    intent.putExtra("email_id", firebaseuser.email)

                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this, task.exception!!.message.toString(), Toast.LENGTH_LONG
                                    ).show()

                                }
                            }
                        )
                }

            }

        }
    }
}