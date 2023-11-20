package com.example.stayinfit;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NotificationActivity extends AppCompatActivity {
    private EditText etm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        etm=findViewById(R.id.editTextMessage);
    }
    public void notification(View view) {
        String text = etm.getText().toString();
        notificationHelper.showNotificationBtn(this,text);
    }

}