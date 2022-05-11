package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.productsdetails.*

class payment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        //val view = findViewById<EditText>(R.id.cardNumberEditText)

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
        val cardNumber = cardNumberEditText.text
        val cardCVCEditTextt = cardCVCEditText.text
        val card = isValid(cardNumber.toString())
        val cv = isValidc(cardCVCEditTextt.toString())
        var userid =intent.getStringExtra("user_id")

        if (cv == true && card == true) {
            Toast.makeText(this, "its Done", Toast.LENGTH_LONG).show()

            FirebaseDatabase.getInstance().getReference("cart").child(userid.toString()).removeValue()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user_id",userid)
            startActivity(intent)
        }

    }

}