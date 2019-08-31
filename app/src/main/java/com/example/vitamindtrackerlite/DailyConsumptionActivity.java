package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DailyConsumptionActivity extends AppCompatActivity {

    Button nextButton;
    EditText editFoodIn;
    String Name,DOB,Height,Weight,Gender,FoodIn;
    int SkinTone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_consumption_main);
        setTitle("Daily Consumptions");

        //values from SkinToneActivity
        Name=getIntent().getExtras().getString("Name");
        DOB=getIntent().getExtras().getString("DOB");
        Height=getIntent().getExtras().getString("Height");
        Weight=getIntent().getExtras().getString("Weight");
        Gender=getIntent().getExtras().getString("Gender");
        SkinTone=getIntent().getExtras().getInt("SkinTone");

        editFoodIn=findViewById(R.id.foodInField);
        nextButton=findViewById(R.id.nextButton2);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodIn=editFoodIn.getText().toString();

                //validate
                if(!isValidFoodIn(FoodIn)){
                    editFoodIn.setError("Required");
                }

                //go to next activity
                if(isValidFoodIn(FoodIn)) {
                    final String message = "Food In: " + FoodIn + "gm";
                    Toast.makeText(DailyConsumptionActivity.this, message, Toast.LENGTH_LONG).show();
                    gotoNextAct();
                }
            }
        });

    }
    public void gotoNextAct(){
        Intent intent=new Intent(this,MedicalInfoActivity.class);
        intent.putExtra("Name",Name);
        intent.putExtra("DOB",DOB);
        intent.putExtra("Height",Height);
        intent.putExtra("Weight",Weight);
        intent.putExtra("Gender",Gender);
        intent.putExtra("FoodIn",FoodIn);
        intent.putExtra("SkinTone",SkinTone);
        startActivity(intent);
        finish();
    }
    public boolean isValidFoodIn(String foodin){
        if(foodin!=null && foodin.length()==0){
            return false;
        }
        return true;
    }
}
