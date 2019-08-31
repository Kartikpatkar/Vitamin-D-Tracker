package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class SkinToneActivity extends AppCompatActivity {

    SeekBar seekBar;
    Button nextButton;
    String Name,Height,Weight,DOB,Gender;
    int st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_tone);
        setTitle("Skin Tone");

        //values from ProfileActivity
        Name=getIntent().getExtras().getString("Name");
        DOB=getIntent().getExtras().getString("DOB");
        Height=getIntent().getExtras().getString("Height");
        Weight=getIntent().getExtras().getString("Weight");
        Gender=getIntent().getExtras().getString("Gender");

        //components
        seekBar=findViewById(R.id.skinToneSeekBar);
        nextButton=findViewById(R.id.nextButtonSkinTone);

        //seek bar for skin tone
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                st=seekBar.getProgress()+1;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SkinToneActivity.this,"Skin Tone Type: "+st,Toast.LENGTH_LONG).show();
                gotoNextAct();

            }
        });

    }
    public void gotoNextAct(){
        Intent intent=new Intent(this,DailyConsumptionActivity.class);
        intent.putExtra("Name",Name);
        intent.putExtra("DOB",DOB);
        intent.putExtra("Height",Height);
        intent.putExtra("Weight",Weight);
        intent.putExtra("Gender",Gender);
        intent.putExtra("SkinTone",st);
        startActivity(intent);
        finish();
    }
}
