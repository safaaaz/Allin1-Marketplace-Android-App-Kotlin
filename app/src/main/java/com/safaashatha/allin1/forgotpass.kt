package com.safaashatha.allin1

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forgotpass.*

class forgotpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        resetemail.setOnClickListener {
            FirebaseAuth.getInstance().sendPasswordResetEmail(writeemail.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (task.result==null) {
                        Toast.makeText(this,"not correct email",Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,"password send to your email",Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this,"failed",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}