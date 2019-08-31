package com.example.kartikpatkar.vitamindtrackerlite;


import android.app.Activity;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static java.lang.Math.PI;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;


public class VitaminDCalculation extends Activity {

    java.util.Date currentTime;
    String time,expoTime;



    public double bodyMassIndex(double height,double weight){
        //in meter
        double heightMeter=height*0.01;
        //BMI
        double BMI=weight/(heightMeter*heightMeter);
        return BMI;
    }

    //current date
    public String CurrentDate(){
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());
        return currentDate;
    }

    //current time
    public  String currentTime(){
        Calendar cal=Calendar.getInstance(TimeZone.getDefault());
        currentTime= cal.getTime();
        DateFormat date =new SimpleDateFormat("HH:mm");
        date.setTimeZone(TimeZone.getDefault());
        String localTime=date.format(currentTime);
        return localTime;
    }

    public int calculateAge(){
        int age=0;


        return age;
    }

    //location
    public void getLocationMethod(){
        //done
    }

    //Day of the year
    public int dayOfYear(){
        Calendar calendar=Calendar.getInstance();
        int dayOfYear=calendar.get(Calendar.DAY_OF_YEAR);
        return dayOfYear;
    }

    //Sun Elevation angle
    public double sunElevation(String date,double latitude,double longitude,String time) throws ParseException {

        //N
        Calendar calendar=Calendar.getInstance();
        int dayOfYear=calendar.get(Calendar.DAY_OF_YEAR);//day of the year

        //hours
        String[] arrofTime=time.split(":",2);
        double time1=Double.parseDouble(arrofTime[0]);
        double time2=Double.parseDouble(arrofTime[1]);
        double hours=time1+time2/60;

        //fractinal year g=(360/365.25)*(N+hour/24)
        double g=(360/365.25)*(dayOfYear+(hours/24));

        //declination of the sun D=0.396372-22.91327*cos(g)+4.02543*sin(g)-0.387205*cos(2*g)+
        //                         0.051967*sin(2*g)-0.154527*cos(3*g)+0.084798*sin(3*g)
        double D=0.396372-22.91327*cos(toRadians(g))+4.02543*sin(toRadians(g))-0.387205*cos(toRadians(2*g))
                +0.051967*sin(toRadians(2*g))-0.154527*cos(toRadians(3*g))+0.084798*sin(toRadians(3*g));

        //time correction TC=0.004297+0.107029*cos(g)-1.837877*sin(g)-0.837378*cos(2*g)-2.340475*sin(2*g)
        double TC=0.004297+0.107029*cos(toRadians(g))-1.837877*sin(toRadians(g))-0.837378*cos(toRadians(2*g))
                -2.340475*sin(toRadians(2*g));

        //Solar Hour Angle SHA=(hours-12)*15+longitude+TC
        Double SHA=(hours-12)*15+longitude+TC;

        //sun zenith angle SZA=arccos(sin(latitude)*sin(D)+cos(latitude)*cos(D)*cos(SHA))
        Double SZA=toDegrees(acos(sin(toRadians(latitude))*sin(toRadians(D))+
                cos(toRadians(latitude))*cos(toRadians(D))*cos(toRadians(SHA))));

        //Sun Elevation Angle SEA=90-SZA
        double SEA=90-SZA;
        return SEA;
    }

    //age
    public int calculateAge(Calendar date) {

        Calendar dob = date;
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        }
        return age;
    }

    //uv chart base on skin color and uv index
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
    public String SkinExposureUVI(int exposure,int UVI){
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
                break;
            case 2:
                if (UVI == 1) expoTime = "30:00";
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
                break;
            case 3:
                if (UVI == 1) expoTime = "80:00";
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
                break;
            case 4:
                if (UVI == 1) expoTime = "100:00";
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
                break;

        }
        return expoTime;
    }
}




//15.2575596
//73.9783188




