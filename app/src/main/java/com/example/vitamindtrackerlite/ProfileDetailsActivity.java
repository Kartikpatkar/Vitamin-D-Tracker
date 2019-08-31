package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class ProfileDetailsActivity extends AppCompatActivity {

    FloatingActionButton flootEditProfile;
    TextView nameText,DOBText,heightText,weightText,GenderText,skinToneText;
    Database db;
    String name,dob,height,weight,gender,skinTone,skinToneColor;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        setTitle("Profile Details");

        nameText=findViewById(R.id.textViewName);
        DOBText=findViewById(R.id.textViewDOB);
        heightText=findViewById(R.id.textViewHeight);
        weightText=findViewById(R.id.textViewWeight);
        GenderText=findViewById(R.id.textViewGender);
        skinToneText=findViewById(R.id.textViewSkinTone);
        flootEditProfile=findViewById(R.id.floatingEditProfileButton);

        db=new Database(getApplicationContext());
        Cursor res=db.getProfileData();
        if(res.getCount()==0){
            Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG).show();
        }
        while (res.moveToNext()){
            name=res.getString(1);
            dob=res.getString(2);
            height=res.getString(3);
            weight=res.getString(4);
            gender=res.getString(5);
            skinTone=res.getString(6);
        }
        nameText.setText(name);
        DOBText.setText(dob);
        heightText.setText(height);
        weightText.setText(weight);
        GenderText.setText(gender);

        res=db.getSkinToneData(Integer.parseInt(skinTone));
        if(res.getCount()==0){
            Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG).show();
        }
        while(res.moveToNext()){
            skinToneColor=res.getString(0);
        }
        skinToneText.setText(skinToneColor);

        flootEditProfile=findViewById(R.id.floatingEditProfileButton);
        flootEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileDetailsActivity.this,EditProfileActivity.class);
                intent.putExtra("Name",name);
                intent.putExtra("DOB",dob);
                intent.putExtra("Height",height);
                intent.putExtra("Weight",weight);
                startActivity(intent);
            }
        });
    }
}
