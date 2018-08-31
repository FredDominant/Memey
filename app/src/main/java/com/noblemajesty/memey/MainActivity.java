package com.noblemajesty.memey;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_IMAGE_GET = 1000;
  private static final int REQUEST_IMAGE_CAPTURE = 2000;
  private EditText editText;
  private ImageView image;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

  private void init() {
    editText = findViewById(R.id.edit_text);
    image = findViewById(R.id.imageView);
    Button selectGallery = findViewById(R.id.button_gallery);
    Button launchCamera = findViewById(R.id.button_camera);

    selectGallery.setOnClickListener(v -> selectImage());
    launchCamera.setOnClickListener(v -> openCamera());
}

  private void openCamera() {
    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
  }

  private void selectImage() {
    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
    galleryIntent.setType("image/*");
    startActivityForResult(galleryIntent, REQUEST_IMAGE_GET);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.v("Intent before super", data.toString());
    super.onActivityResult(requestCode, resultCode, data);
    Log.v("Intent after super", data.toString());
    switch (requestCode) {
      case REQUEST_IMAGE_GET:
        if (resultCode == RESULT_OK) {
          image.setImageURI(data.getData());
        }
      case REQUEST_IMAGE_CAPTURE:
        Uri cameraImageUri = data.getData();
        if (cameraImageUri != null) {
          Log.v("Camera Image", cameraImageUri.toString());
          image.setImageURI(cameraImageUri);
        } else {
          Log.v("Camera Image", "iss empty");
          Log.v("Empty Camera Image", data.toString());
        }

    }
  }
}
