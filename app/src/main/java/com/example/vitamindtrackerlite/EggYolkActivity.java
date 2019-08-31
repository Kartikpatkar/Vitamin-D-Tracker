package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EggYolkActivity extends AppCompatActivity {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_yolk);
        setTitle("Egg yolk");

        button1 = (Button) findViewById(R.id.b2);

    }

}
