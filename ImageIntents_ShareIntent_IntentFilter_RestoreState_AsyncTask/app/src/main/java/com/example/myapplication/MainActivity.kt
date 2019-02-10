package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button;
import android.widget.ImageView
import android.view.View
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    private var btn: Button? = null;
    private var imgview: ImageView? = null;
    private val GALLERY = 1;
    private val CAMERA = 2;
    companion object {
        private var imageUri: Uri? =null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById<View>(R.id.btn) as Button
        imgview = findViewById<View>(R.id.iv) as ImageView
        btn!!.setOnClickListener { showPictureDialog() }
    }


    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select action")
        val pictureDialogItems = arrayOf("From gallery", "Take a photo")
        pictureDialog.setItems(pictureDialogItems) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA)

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (data != null) {
                imageUri = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
//                    val path = saveImage(bitmap)
                    Toast.makeText(this@MainActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                    imgview!!.setImageBitmap(bitmap)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@MainActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == CAMERA) {
                val thumbnail = data!!.extras!!.get("data") as Bitmap
                imgview!!.setImageBitmap(thumbnail)
//                saveImage(thumbnail)
                Toast.makeText(this@MainActivity, "Image Saved!", Toast.LENGTH_SHORT).show()

            }
        }

    }


//    fun saveImage(myBitmap: Bitmap): String {
//        val bytes = ByteArrayOutputStream()
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
//        val wallPaperDirectory = File(Environment.getExternalStorageDirectory().toString() + IMAGE_DIRECTORY)
//        Log.d("fee", wallPaperDirectory.toString())
//        if (!wallPaperDirectory.exists()) {
//
//            wallPaperDirectory.mkdir()
//        }
//        try {
//            Log.d("heel", wallPaperDirectory.toString())
//            val f = File(wallPaperDirectory, ((Calendar.getInstance().getTimeInMillis()).toString() + ".jpg"))
//            f.createNewFile()
//            val fo = FileOutputStream(f)
//            fo.write(bytes.toByteArray())
//            MediaScannerConnection.scanFile(
//                this,
//                arrayOf(f.getPath()),
//                arrayOf("image/jpeg"), null
//            )
//            fo.close()
//            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())
//
//            return f.getAbsolutePath()
//        } catch (e1: IOException) {
//            e1.printStackTrace()
//        }
//
//        return ""
//
//    }
//
//    companion object {
//        private val IMAGE_DIRECTORY = "/photos"
//    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater;
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.item1->{

                shareImage(imageUri)

                return true

            }
            R.id.item2->{

                return true

            }
            R.id.item3->{

                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }
    fun shareImage(imagePath: Uri?){

        val shareIntent=Intent(android.content.Intent.ACTION_SEND)
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imagePath)
        startActivity(Intent.createChooser(shareIntent,"Share via"))

    }

}
