package com.example.stayinfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class cameragalleryActivity extends AppCompatActivity {
    private ImageView image;

    private Button gallery;
    private  Button camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameragallery);
        initObjects();
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opencamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(opencamera, 100);


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data){
        super.onActivityResult(requestCode, resultCode , data);
        if(resultCode == RESULT_OK  && data != null)
        {
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);
        }

    }
    public void initObjects(){
         image = findViewById(R.id.ImageViewImage);
         gallery = findViewById(R.id.buttonGallery);
         camera = findViewById(R.id.buttonCamera);
    }
}