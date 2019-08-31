package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FoodSuggestionsActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_suggestions);
        setTitle("Food Suggestions");

        button1 = (Button) findViewById(R.id.b1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity1();
            }
        });
        button2 = (Button) findViewById(R.id.b2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity2();
            }
        });

        button3 = (Button) findViewById(R.id.b3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity3();
            }
        });

        button4 = (Button) findViewById(R.id.b4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity4();
            }
        });
        button5 = (Button) findViewById(R.id.b5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity5();
            }
        });

    }

    public void openactivity1() {

        Intent intent = new Intent(this, MushroomActivity.class);
        startActivity(intent);


    }

    public void openactivity2() {

        Intent intent = new Intent(this, AlfalfaActivity.class);
        startActivity(intent);
    }
    public void openactivity3() {

        Intent intent = new Intent(this, FortifiedMilkActivity.class);
        startActivity(intent);
    }
    public void openactivity4() {

        Intent intent = new Intent(this, ButterActivity.class);
        startActivity(intent);
    }
    public void openactivity5() {

        Intent intent = new Intent(this, EggYolkActivity.class);
        startActivity(intent);
    }

}
