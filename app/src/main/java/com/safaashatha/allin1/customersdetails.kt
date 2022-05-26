package com.safaashatha.allin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_customersdetails.*

class customersdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customersdetails)
        val b = intent.extras!!
        val usname = b.getString("name")
        val usphone = b.getString("phone")
        val usaddress = b.getInt("address")

        username.setText(usname)
        userphone.setText(usphone)
        useraddress.setText(usaddress)


        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "allin1"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}


