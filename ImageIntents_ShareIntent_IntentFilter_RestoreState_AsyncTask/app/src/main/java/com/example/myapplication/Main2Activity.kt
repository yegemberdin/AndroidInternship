package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import java.io.IOException



class Main2Activity : AppCompatActivity() {
    private var imgView: ImageView?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        imgView=findViewById<View>(R.id.shared_image) as ImageView
        onSharedIntent();
    }
    fun onSharedIntent(){
        val receivedIntent=intent;
        val receivedIntentAction=receivedIntent.action;
        val receivedIntentType=receivedIntent.type;
        if(receivedIntentAction.equals(Intent.ACTION_SEND)){
            Log.d("sendacion","ok");

            if(receivedIntentType.startsWith("image/")){
                Log.d("startsimage","ok");
                val receiveUri: Uri=receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                if(receiveUri!=null){
                    try {
                        Log.d("receiveurl","ok");

                        val bitmap=MediaStore.Images.Media.getBitmap(this.contentResolver,receiveUri);
                        imgView!!.setImageBitmap(bitmap)
                        Log.d("setimg","ok");

                    }
                    catch (e: IOException) {
                        e.printStackTrace();
                    }
                }


                }

            }
        else if(receivedIntentAction.equals(Intent.ACTION_MAIN)){
            Toast.makeText(this@Main2Activity,"Nothing has shared", Toast.LENGTH_SHORT).show()

        }


    }
}
