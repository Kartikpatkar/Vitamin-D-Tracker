package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MedicalInfoActivity extends AppCompatActivity {

    EditText editReport,editDosage;
    RadioButton noTest;
    Button nextButton;
    String Name,DOB,Height,Weight,Gender,FoodIn,dosage,noTestC,report,TestDone;
    int SkinTone;
    public Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_info_main);
        setTitle("Medical Info");

        //data from DailyConsumptionActivity
        Name=getIntent().getExtras().getString("Name");
        DOB=getIntent().getExtras().getString("DOB");
        Height=getIntent().getExtras().getString("Height");
        Weight=getIntent().getExtras().getString("Weight");
        Gender=getIntent().getExtras().getString("Gender");
        FoodIn=getIntent().getExtras().getString("FoodIn");
        SkinTone=getIntent().getExtras().getInt("SkinTone");

        mydb=new Database(this);

        editReport=findViewById(R.id.reportField);
        editDosage=findViewById(R.id.dosageField);
        noTest=findViewById(R.id.noTestCheck);

        nextButton=findViewById(R.id.nextButton3);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosage=editDosage.getText().toString();
                noTestC=noTest.getText().toString();

                //validate
                if(!isValidDosage(dosage)){
                    dosage="00";
                }
                if(noTest(noTestC)){
                    report="00";
                    TestDone="False";
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                    mydb.insertMedicalReport(report,currentDate);
                }
                else{
                    report=editReport.getText().toString();
                    TestDone="True";
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                    Boolean result=mydb.insertMedicalReport(report,currentDate);
                }

                //insert into database
                boolean isInserted =mydb.insertProfile(Name,DOB,Height,Weight,Gender,SkinTone,dosage,TestDone,report,FoodIn);
                if(isInserted==true)
                    Toast.makeText(MedicalInfoActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MedicalInfoActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();


                /*
                //print data
                String message="Report: "+report+"\n"+
                        "Dosage: "+dosage;

                Toast.makeText(MedicalInfoActivity.this,message,Toast.LENGTH_LONG).show();
                */

                gotoNext();
            }
        });

    }
    public void gotoNext(){
        Intent intent=new Intent(this,mainIntroActivity.class);
        startActivity(intent);
        finish();
    }

    //validate
    public boolean isValidDosage(String dosage){
        if(dosage!=null && dosage.length()==0){
            return false;
        }
        return true;
    }
    public boolean noTest(String no) {
        if (no != null && no.length() == 0) {
            return false;
        }
        return true;
    }
}
