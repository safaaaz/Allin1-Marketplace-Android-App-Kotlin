package com.safaashatha.allin1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_editprofileuser.*
import kotlinx.android.synthetic.main.list_productsowner.view.*
import kotlinx.android.synthetic.main.productsdetails.view.*
import java.io.File

class productowadapter(private val context: Activity,private val arrayList: ArrayList<product>):ArrayAdapter<product>(context,
    R.layout.list_productsowner,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        print("\n -----------------------------------------shathaaaaa----------------------------------\n")
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.list_productsowner,parent,false);

        val imageview:ImageView=view.findViewById(R.id.productimage)
        val nameview:TextView=view.findViewById(R.id.productname)
        val priceview:TextView=view.findViewById(R.id.productprice)

        val filename = FirebaseAuth.getInstance().currentUser!!.uid.toString()
        val storref = FirebaseStorage.getInstance().reference.child("productsimages/$filename/"+arrayList[position].name.toString())
        val localfile = File.createTempFile("tempimage", "jpg")
        storref.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            imageview.setImageBitmap(bitmap)
            print("99999999999999999999999999999999999999999999raratsrs")

        }
        //imageview.setImageResource(arrayList[position].image!!)
        nameview.text=arrayList[position].name
        priceview.text=arrayList[position].price

        val product=arrayList[position]
        val inflator=context!!.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        //var productview=inflator.inflate(R.layout.productsowdetails,null)

        view.productimage.setOnClickListener(){
            var intent= Intent(context,productsowdetails::class.java)

            //intent.putExtra("image",product.image!!)
            intent.putExtra("name",product.name!!)
            intent.putExtra("price",product.price!!)
            print("\n\n\n -----------------------------------------ayaaannn----------------------------------\n\n\n"+arrayList[position].name)

            context!!.startActivity(intent)
        }

        return view
    }
}