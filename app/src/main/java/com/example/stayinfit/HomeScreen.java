package com.example.stayinfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {
    private Button btnNotiii;
    private EditText etSource;
    private  EditText etDestination;
    private Button btnGetDestination;
    private Button btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initObjects();
        btnGetDestination.setOnClickListener(view -> {
            String userLocation = etSource.getText().toString();
            String userDestination = etDestination.getText().toString();
            if(userLocation.equals("") || userDestination.equals("")){
                Toast.makeText(this,"please enter your location & destination",Toast.LENGTH_SHORT).show();
            }else{
                getDirection(userLocation,userDestination);
            }
        });
        btnNotiii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(i);
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cameragalleryActivity.class);
                startActivity(i);
            }
        });
    }
    private void getDirection(String from, String to){
        try {
            Uri uri = Uri.parse("http://www.google.com/maps/dir/" + from + "/" + to);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch(ActivityNotFoundException exception){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    public void initObjects(){
        etSource = findViewById(R.id.editTextsource);
        etDestination = findViewById(R.id.editTextdestination);
        btnGetDestination = findViewById(R.id.buttonMaps);
        btnNotiii = findViewById(R.id.buttonnotiii);
        btnPhoto = findViewById(R.id.buttonphoto);
    }
}