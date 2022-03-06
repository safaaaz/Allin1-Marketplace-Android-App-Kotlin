package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userid=intent.getStringExtra("user_id")
        val emailid=intent.getStringExtra("email_id")

        user_id.text="user id :: $userid"
        email_id.text="email id:: $emailid"

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }



    }
}