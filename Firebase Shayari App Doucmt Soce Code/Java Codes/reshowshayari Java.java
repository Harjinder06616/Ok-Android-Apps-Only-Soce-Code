package com.sharepunjabishayari;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class reshowshayari extends AppCompatActivity {
    TextView username;
    TextView textView;
    Button copybtn,sharebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reshowshayari);
        textView = findViewById(R.id.shayaritext);
        username = findViewById(R.id.UserNameShow);
        copybtn = findViewById(R.id.copy);
        sharebtn = findViewById(R.id.share);
    }
}