package com.example.kartikpatkar.vitamindtrackerlite;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    Button nextButton;
    EditText editName,editDob,editHeight,editWeight;
    RadioGroup editGender;
    RadioButton gender;
    Calendar myCalendar;
    String Name,Height,Weight,DOB,Gender;
    //Database mydb;
    private Boolean firstTime=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);
        setTitle("Personal Info");

        //first time
        if(!isFirstTime()){
            Intent intent=new Intent(this,mainIntroActivity.class);
            startActivity(intent);
        }

        //mydb =new Database(this);

        editName=findViewById(R.id.nameField);
        editHeight=findViewById(R.id.heightField);
        editWeight=findViewById(R.id.weightField);
        editDob=findViewById(R.id.dobField);
        editGender=findViewById(R.id.genderField);
        nextButton=findViewById(R.id.nextButton1);


        //date picker
        myCalendar=Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
                DOB="";
                DOB = editDob.getText().toString();
            }

        };
        editDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ProfileActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = editName.getText().toString();
                Height = editHeight.getText().toString();
                Weight = editWeight.getText().toString();

                //validation
                if (!isValidName(Name)) {
                    editName.setError("Required");
                }
                if (!isValidHeight(Height)) {
                    editHeight.setError("Required");
                }
                if (!isValidWeight(Weight)) {
                    editWeight.setError("Required");
                }
                if (!isValidDob(DOB)) {
                    editDob.setError("Required");
                }
                if (editGender.getCheckedRadioButtonId()==-1) {
                    Toast.makeText(ProfileActivity.this,"Select a Gender",Toast.LENGTH_SHORT).show();
                }else{
                    int genID = editGender.getCheckedRadioButtonId();
                    gender = findViewById(genID);
                    Gender = gender.getText().toString();
                }


                //go to FoodInactivity

                if (isValidName(Name) && (!(editGender.getCheckedRadioButtonId()==-1)) && isValidDob(DOB) && isValidWeight(Weight) && isValidHeight(Height)) {
                    final String message = "Name: " + Name + "\n" +
                            "Height: " + Height + "cm\n" +
                            "Weight: " + Weight + "kg\n" +
                            "DOB: " + DOB + "\n" +
                            "Gender: " + Gender;
                    Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_LONG).show();

                    gotoNextAct();
                }
            }
        });
    }
   public void gotoNextAct(){
        Intent intent=new Intent(this,SkinToneActivity.class);
        intent.putExtra("Name",Name);
        intent.putExtra("DOB",DOB);
        intent.putExtra("Height",Height);
        intent.putExtra("Weight",Weight);
        intent.putExtra("Gender",Gender);
        startActivity(intent);
        finish();
    }

    //calender
    public void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(myFormat,Locale.US);
        editDob.setText((sdf.format(myCalendar.getTime())));

    }

    //validate
    public boolean isValidName(String name){
        if(name!=null && name.length()==0){
            return false;
        }
        return true;
    }
    public boolean isValidHeight(String height){
        if(height!=null && height.length()==0){
            return false;
        }
        return true;
    }
    public boolean isValidWeight(String weight){
        if(weight!=null && weight.length()==0){
            return false;
        }
        return true;
    }
    public boolean isValidDob(String dob){
        if(dob==null){
            return false;
        }
        return true;
    }

    //first time
    private boolean isFirstTime(){
        if (firstTime==null){
            SharedPreferences mPreferences=this.getSharedPreferences("first time",Context.MODE_PRIVATE);
            firstTime=mPreferences.getBoolean("first time",true);
            if(firstTime){
                SharedPreferences.Editor editor=mPreferences.edit();
                editor.putBoolean("first time",false);
                editor.commit();
            }
            else
                firstTime=false;
        }
        return firstTime;
    }
}
