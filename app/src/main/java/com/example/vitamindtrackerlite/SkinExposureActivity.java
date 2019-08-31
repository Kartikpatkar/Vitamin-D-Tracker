package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class SkinExposureActivity extends AppCompatActivity {
    SeekBar seekBar;
    Button nextButton;
    int skinexro,uvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skin_exposure_main);
        seekBar=findViewById(R.id.skinToneSeekBar);
        nextButton=findViewById(R.id.button);

        uvi=(int)Double.parseDouble(getIntent().getExtras().getString("UVI"));

        //seek bar for skin tone
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                skinexro=seekBar.getProgress()+1;
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SkinExposureActivity.this,"BODY EXPOSURE: "+skinexro,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SkinExposureActivity.this,EstimateActivity.class);
                intent.putExtra("Skin Exposure",Integer.toString(skinexro));
                intent.putExtra("UVI",Integer.toString(uvi));
                startActivity(intent);

            }
        });

    }
}
