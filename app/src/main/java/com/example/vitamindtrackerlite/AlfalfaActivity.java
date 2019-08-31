package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AlfalfaActivity extends AppCompatActivity {

    Button button01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfalfa);
        setTitle("Alfalfa");

        button01=findViewById(R.id.b1);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity1();
            }
        });

    }
    public void openactivity1() {
        Intent intent = new Intent(this, Alfalfa01Activity.class);
        startActivity(intent);
    }
}
