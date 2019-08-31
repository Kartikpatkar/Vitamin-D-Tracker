package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ButterActivity extends AppCompatActivity {
    private Button button1;
    public Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter);
        setTitle("butter");

        button1 = (Button) findViewById(R.id.b1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity1();
            }
        });

        button2=findViewById(R.id.b2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity2();
            }
        });


    }

    public void openactivity1(){

        Intent intent = new Intent(this,Butter01Activity.class);
        startActivity(intent);


    }
    public void openactivity2(){

        Intent intent = new Intent(this,Butter02Activity.class);
        startActivity(intent);


    }
}
