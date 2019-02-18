package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.R
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.Toast
import java.text.FieldPosition


class MainActivity : AppCompatActivity(),RecyclerListener {


    val items: ArrayList<Data> = ArrayList()
    val hashMap:HashMap<Int,Uri?> = HashMap()
//    var pos:Int?=null

    private val myImageList = intArrayOf(R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus,R.drawable.btn_plus)
//    R.drawable.benz, R.drawable.bike, R.drawable.car, R.drawable.carrera, R.drawable.ferrari, R.drawable.harly, R.drawable.lamborghini, R.drawable.silver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.myapplication.R.layout.activity_main)
        addItems()
        val newText = intent.getStringExtra("new name")

        val pos = intent.getIntExtra("position",1)
        val receiveUri: Uri? =intent!!.getParcelableExtra("image");

//        hashMap.put(pos,newText)
        hashMap.put(pos,receiveUri)




        list.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter(items,hashMap, this)
        list.adapter = adapter
        adapter.setListener(this)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //permission granted
    }


    fun addItems() {
        for (i in 0..5) {
            var item= Data()
            item.setNames("naz")
            item.setSurnames("yegemberdi")
            item.setImage_drawables(myImageList[i])
            items.add(item)
        }

    }
    override fun openSecondActivity(data: Data?, itemPosition:Int) {
        val intent = Intent(this, Main2Activity::class.java)
        intent.putExtra("name", data!!.getNames())
        intent.putExtra("surname", data!!.getSurnames())

        intent.putExtra("image", data!!.getImage_drawables());
        intent.putExtra("position",itemPosition)
//        Log.d("position:", itemPosition.toStrin)
        startActivity(intent)


    }
}
//edits