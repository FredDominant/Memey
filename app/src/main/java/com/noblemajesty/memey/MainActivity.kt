package com.noblemajesty.memey

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private var editText: EditText? = null
    private var image: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        editText = findViewById(R.id.edit_text)
        image = findViewById(R.id.imageView)
        val selectGallery = findViewById<Button>(R.id.button_gallery)
        val launchCamera = findViewById<Button>(R.id.button_camera)

        selectGallery.setOnClickListener { v -> selectImage() }
        launchCamera.setOnClickListener { v -> openCamera() }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
    }

    private fun selectImage() {
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, REQUEST_IMAGE_GET)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Log.v("Intent before super", data.toString())
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("Intent after super", data.toString())
        when (requestCode) {
            REQUEST_IMAGE_GET -> {
                if (resultCode == Activity.RESULT_OK) {
                    image!!.setImageURI(data.data)
                }
                val cameraImageUri = data.data
                if (cameraImageUri != null) {
                    Log.v("Camera Image", cameraImageUri.toString())
                    image!!.setImageURI(cameraImageUri)
                } else {
                    Log.v("Camera Image", "iss empty")
                    Log.v("Empty Camera Image", data.toString())
                }
            }
            REQUEST_IMAGE_CAPTURE -> {
                val cameraImageUri = data.data
                if (cameraImageUri != null) {
                    Log.v("Camera Image", cameraImageUri.toString())
                    image!!.setImageURI(cameraImageUri)
                } else {
                    Log.v("Camera Image", "iss empty")
                    Log.v("Empty Camera Image", data.toString())
                }
            }
        }
    }

    companion object {

        private val REQUEST_IMAGE_GET = 1000
        private val REQUEST_IMAGE_CAPTURE = 2000
    }
}
