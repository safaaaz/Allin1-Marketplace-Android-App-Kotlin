package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ListView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if(FirebaseAuth.getInstance().currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
        }
        //var database= FirebaseDatabase.getInstance().reference
        //database.setValue("shops")

        //database.setValue("susu")
        joinus.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))

        }
        login_button.setOnClickListener {
            when {
                TextUtils.isEmpty(username.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter username", Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this, "please enter password", Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val email = username.text.toString().trim { it <= ' ' }
                    val pass = password.text.toString().trim { it <= ' ' }

                    //create user
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    val firebaseuser: FirebaseUser = task.result!!.user!!

                                    val databasee =
                                        FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                                            .getReference("shops")
                                    databasee.get().addOnSuccessListener {
                                        var itsowner=0
                                        for (x in it.children)

                                            if (x.exists()) {
                                                if(x.key==firebaseuser.uid){
                                                    itsowner=1
                                                }
                                                print("sssssssssssssssssssssssssss------> "+itsowner)
                                                print("sssssssssssssssssssssssssss x.key------> "+x.key)
                                                print("sssssssssssssssssssssssssss uid------> "+firebaseuser.uid)

                                            }
                                        if(itsowner==1){
                                            println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr------> ")

                                            val intent = Intent(this, ownersignup::class.java)
                                            intent.flags =
                                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            intent.putExtra("user_id", FirebaseAuth.getInstance()!!.uid)
                                            intent.putExtra("email_id", email)
                                            intent.putExtra("temp", "yes")

                                            startActivity(intent)
                                            finish()
                                        }
                                        println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr------> "+itsowner)

                                    }

                                    Toast.makeText(
                                        this, "you are logged successfully.", Toast.LENGTH_SHORT
                                    ).show()

                                    intent.putExtra("user_id", FirebaseAuth.getInstance()!!.uid)
                                    intent.putExtra("email_id", email)

                                    startActivity(intent)
                                    finish()
                                 }else {
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