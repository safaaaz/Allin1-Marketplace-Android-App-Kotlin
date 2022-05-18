package com.safaashatha.allin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.productsdetails.*

class payment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
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
                val intent=Intent(this,editprofileuser::class.java)
                intent.putExtra("user_id", FirebaseAuth.getInstance()!!.uid)
                startActivity(intent)
                true
            }
            R.id.homepage->{
                startActivity(Intent(this,MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
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