package com.example.kartikpatkar.vitamindtrackerlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class EstimateActivity extends AppCompatActivity {

    VitaminDCalculation VDC;
    Database db;
    int skinTone,exposure,uvi;
    TextView timeLimit;
    String SEUVI, UVTSTC,time,expoTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);

        exposure=Integer.parseInt(getIntent().getExtras().getString("Skin Exposure"));
        uvi=Integer.parseInt(getIntent().getExtras().getString("UVI"));

        db=new Database(getApplicationContext());
        Cursor res=db.getProfileData();
        if(res.getCount()==0){
            Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG).show();
        }

        while (res.moveToNext()){
            skinTone=Integer.parseInt(res.getString(6));
        }


        SkinExposureUVI(exposure,uvi);//00:00 min

        UV_IndexSkinToneChart(skinTone,uvi);//0-0 min

        String[] arrI=expoTime.split(":",2);
        String[] arrJ=time.split("-",2);

        int min=Integer.parseInt(arrI[0])+Integer.parseInt(arrJ[0]);
        int max=Integer.parseInt(arrI[0])+Integer.parseInt(arrJ[1]);

        timeLimit=findViewById(R.id.textViewTime);
        timeLimit.setText(Integer.toString(min)+"-"+Integer.toString(max)+" min");

    }
    public String UV_IndexSkinToneChart(int SkinTone, int UVI) {

        switch (SkinTone) {
            case 1:
                if(UVI>=0 && UVI<=2)
                    time="0-0";
                else if (UVI >= 3 && UVI <= 5)
                    time="10-15";
                else if (UVI >= 6 && UVI <= 7)
                    time= "5-10";
                else if (UVI >= 8 && UVI <= 10)
                    time="2-5";
                else if (UVI >= 11)
                    time="1-2";
                break;
            case 2:
                if(UVI>=0 && UVI<=2)
                    time="0-0";
                else if (UVI >= 3 && UVI <= 5)
                    time="15-20";
                else if (UVI >= 6 && UVI <= 7)
                    time="10-15";
                else if (UVI >= 8 && UVI <= 10)
                    time="5-10";
                else if (UVI >= 11)
                    time="2-5";
                break;
            case 3:
                if(UVI>=0 && UVI<=2)
                    time="0-0";
                else if (UVI >= 3 && UVI <= 5)
                    time="20-30";
                else if (UVI >= 6 && UVI <= 7)
                    time="15-20";
                else if (UVI >= 8 && UVI <= 10)
                    time="10-15";
                else if (UVI >= 11)
                    time="5-10";
                break;
            case 4:
                if(UVI>=0 && UVI<=2)
                    time="0-0";
                else if (UVI >= 3 && UVI <= 5)
                    time="30-40";
                else if (UVI >= 6 && UVI <= 7)
                    time="20-30";
                else if (UVI >= 8 && UVI <= 10)
                    time="15-20";
                else if (UVI >= 11)
                    time="10-15";
                break;
            case 5:
                if(UVI>=0 && UVI<=2)
                    time="0-0";
                else if (UVI >= 3 && UVI <= 5)
                    time="40-60";
                else if (UVI >= 6 && UVI <= 7)
                    time="30-40";
                else if (UVI >= 8 && UVI <= 10)
                    time="20-30";
                else if (UVI >= 11)
                    time="15-20";
                break;
            case 6:
                if(UVI>=0 && UVI<=2)
                    time="0-0";
                else if (UVI >= 3 && UVI <= 5)
                    time="40-60";
                else if (UVI >= 6 && UVI <= 7)
                    time="30-40";
                else if (UVI >= 8 && UVI <= 10)
                    time="20-30";
                else if (UVI >= 11)
                    time="15-20";
                break;
            default:
                Toast.makeText(this, "Wrong Skin Type", Toast.LENGTH_LONG).show();
        }
        return time;
    }

    //Skin exposure and UVI
    public void SkinExposureUVI(int exposure,int UVI){
        switch (exposure) {
            case 1:
                if (UVI == 1) expoTime = "20:00";
                else if (UVI == 2) expoTime = "8:00";
                else if (UVI == 3) expoTime = "4:00";
                else if (UVI == 4) expoTime = "3:00";
                else if (UVI == 5) expoTime = "2:30";
                else if (UVI == 6) expoTime = "2:00";
                else if (UVI == 7) expoTime = "1:40";
                else if (UVI == 8) expoTime = "1:30";
                else if (UVI == 9) expoTime = "1:20";
                else if (UVI == 10) expoTime = "1:00";
                else if (UVI == 11) expoTime = "0:50";
                else if (UVI == 12) expoTime = "0:40";
                else if (UVI == 13) expoTime = "0:30";
                else if (UVI == 14) expoTime = "0:30";
                else if (UVI == 15) expoTime = "0:20";
                else if (UVI == 0) expoTime="0:0";
                break;
            case 2:
                if (UVI == 2) expoTime = "30:00";
                else if (UVI == 2) expoTime = "12:00";
                else if (UVI == 3) expoTime = "7:00";
                else if (UVI == 4) expoTime = "5:00";
                else if (UVI == 5) expoTime = "3:30";
                else if (UVI == 6) expoTime = "3:00";
                else if (UVI == 7) expoTime = "2:40";
                else if (UVI == 8) expoTime = "2:30";
                else if (UVI == 9) expoTime = "2:00";
                else if (UVI == 10) expoTime = "1:50";
                else if (UVI == 11) expoTime = "1:40";
                else if (UVI == 12) expoTime = "1:30";
                else if (UVI == 13) expoTime = "1:20";
                else if (UVI == 14) expoTime = "1:10";
                else if (UVI == 15) expoTime = "1:00";
                else if (UVI == 0) expoTime="0:0";
                break;
            case 3:
                if (UVI == 3) expoTime = "80:00";
                else if (UVI == 2) expoTime = "30:00";
                else if (UVI == 3) expoTime = "17:00";
                else if (UVI == 4) expoTime = "12:00";
                else if (UVI == 5) expoTime = "9:00";
                else if (UVI == 6) expoTime = "7:00";
                else if (UVI == 7) expoTime = "6:00";
                else if (UVI == 8) expoTime = "5:00";
                else if (UVI == 9) expoTime = "4:30";
                else if (UVI == 10) expoTime = "4:00";
                else if (UVI == 11) expoTime = "3:40";
                else if (UVI == 12) expoTime = "3:30";
                else if (UVI == 13) expoTime = "2:50";
                else if (UVI == 14) expoTime = "2:30";
                else if (UVI == 15) expoTime = "2:20";
                else if (UVI == 0) expoTime="0:0";
                break;
            case 4:
                if (UVI == 4) expoTime = "100:00";
                else if (UVI == 2) expoTime = "80:00";
                else if (UVI == 3) expoTime = "45:00";
                else if (UVI == 4) expoTime = "35:00";
                else if (UVI == 5) expoTime = "25:30";
                else if (UVI == 6) expoTime = "20:00";
                else if (UVI == 7) expoTime = "17:00";
                else if (UVI == 8) expoTime = "15:00";
                else if (UVI == 9) expoTime = "12:00";
                else if (UVI == 10) expoTime = "10:00";
                else if (UVI == 11) expoTime = "9:00";
                else if (UVI == 12) expoTime = "8:00";
                else if (UVI == 13) expoTime = "7:30";
                else if (UVI == 14) expoTime = "7:00";
                else if (UVI == 15) expoTime = "6:40";
                else if (UVI == 0) expoTime="0:0";
                break;

        }
    }
}

