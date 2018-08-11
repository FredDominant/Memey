package com.noblemajesty.memey;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_IMAGE_GET = 1000;
  private EditText editText;
  private ImageView image;
  private Button selectGallery, launchCamera;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }

  private void init() {
    editText = findViewById(R.id.edit_text);
    image = findViewById(R.id.imageView);
    selectGallery = findViewById(R.id.button_gallery);
    launchCamera = findViewById(R.id.button_camera);

    selectGallery.setOnClickListener(v -> selectImage());
}

  private void selectImage() {
    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
    galleryIntent.setType("image/*");
    startActivityForResult(galleryIntent, REQUEST_IMAGE_GET);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {

      case REQUEST_IMAGE_GET:
        if (resultCode == RESULT_OK) {
          image.setImageURI(data.getData());
        }
    }
  }
}
