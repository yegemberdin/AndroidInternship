package com.example.myapplication

import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.IOException


class Main2Activity : AppCompatActivity() {

     var name: TextView?=null
     var surname: TextView?=null
    var image: ImageView?=null
    var position:Int?=null
    var backButton:Button?=null
    companion object {
        private var imageUri: Uri? =null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        name = findViewById<View>(R.id.nameDetail) as TextView
        surname = findViewById(R.id.surnameDetail) as TextView
        image = findViewById(R.id.imageDetail) as ImageView
        backButton=findViewById(R.id.backButton) as Button

        val intent = intent
        val nameIntent = intent.getStringExtra("name")
        val surnameIntent = intent.getStringExtra("surname")
        val imageIntent = intent.getIntExtra("image",1)
        position=intent.getIntExtra("position",1)
       image!!.setImageResource(imageIntent)
//        name!!.setText(nameIntent)
        surname!!.setText(surnameIntent)
//        image!!.setImageResource(imageIntent)
        backButton!!.setOnClickListener { edit(imageUri) }
        image!!.setOnClickListener{changeImage()}
//edits
    }

    fun changeImage(){
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, 1)

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1) {
            imageUri = data!!.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
//                    val path = saveImage(bitmap)
                image!!.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    fun edit(path: Uri?){
        val editIntent=Intent(this,MainActivity::class.java)
        editIntent.putExtra("new name", name!!.text)
        editIntent.putExtra("position",position)
        editIntent.putExtra("image",path )
        startActivity(editIntent)


    }
}
