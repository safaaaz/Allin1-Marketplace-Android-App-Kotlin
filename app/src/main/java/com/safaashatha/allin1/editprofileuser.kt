package com.safaashatha.allin1

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_editprofileuser.*
import kotlinx.android.synthetic.main.fragment_addprod.*
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class editprofileuser : AppCompatActivity() {

    lateinit var uriimage : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uriimage= Uri.EMPTY
        setContentView(R.layout.activity_editprofileuser)
        changeimage.setOnClickListener{
            openGalleryForImage()
        }
        saveprofile.setOnClickListener {
            val firstname= FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
                "Users"
            ).child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user(FirebaseAuth.getInstance().currentUser!!.uid,
                firstnameinput.text.toString(),
                lastnameinput.text.toString(),
                emailinput.text.toString(),
                passwordinput.text.toString(),
                birthdayinput.text.toString(),
                addressinput.text.toString(),
                phoneinput.text.toString()
            ))
            Toast.makeText(this,"Your information has been saved successfully ",Toast.LENGTH_LONG).show()
        }

        val userid=intent.getStringExtra("user_id")
        val firstname= FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app").reference.child(
            "Users"
        ).child(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {

            val k = it.getValue(user::class.java)
            val filename = FirebaseAuth.getInstance().currentUser!!.uid.toString()
            val storref = FirebaseStorage.getInstance().reference.child("profileimages/$filename")
            val localfile = File.createTempFile("tempimage", "jpg")
            storref.getFile(localfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)

                if(bitmap==null){
                    print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                }else{
                    image.setImageBitmap(bitmap)
                }

            }
            if(k!=null) {
                firstnameinput.setText(k!!.Firstname)
                lastnameinput.setText(k!!.Lastname)
                emailinput.setText(k!!.Email)
                passwordinput.setText(k!!.Password.toString())
                birthdayinput.setText(k!!.Birthday.toString())
                addressinput.setText(k!!.Address)
                phoneinput.setText(k!!.phone)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        val search = menu!!.findItem(R.id.prodsearch)
        val editprod=menu!!.findItem(R.id.editprofile)
        search.isVisible=false
        editprod.isVisible=false
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
                startActivity(Intent(this,editprofileuser::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100){
            println("2222222222222222222222222222222222222222222"+ data?.data!!.hashCode())
            uriimage=data?.data!!
            val progressgialog=ProgressDialog(this)
            progressgialog.setMessage("uploading file...")
            progressgialog.setCancelable(false)
            progressgialog.show()
            val filename=FirebaseAuth.getInstance().currentUser!!.uid.toString()
            val storref=FirebaseStorage.getInstance().getReference("profileimages/$filename")
            storref.putFile(uriimage).
            addOnSuccessListener {
                Toast.makeText(this,"success",Toast.LENGTH_LONG)
                if(progressgialog.isShowing) progressgialog.dismiss()
            }.addOnFailureListener{
                if(progressgialog.isShowing) progressgialog.dismiss()
                Toast.makeText(this,"failed",Toast.LENGTH_LONG)

            }
            if(progressgialog.isShowing) progressgialog.dismiss()
        }

    }
}