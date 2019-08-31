package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    //database
    public static String Database_name="Vitamin_D.db";

    //table 01
    public static String Table_name_01="Profile";
    //col
    public static String Table_01_col_01="ID";
    public static String Table_01_col_02="Name";
    public static String Table_01_col_03="DOB";
    public static String Table_01_col_04="Height";
    public static String Table_01_col_05="Weight";
    public static String Table_01_col_06="Gender";
    public static String Table_01_col_07="Skin_Tone";

    //table 02
    public static String Table_name_02="Medical_and_Food_In_info";
    //col
    public static String Table_02_col_01="ID";
    public static String Table_02_col_02="Vitamin_D_Dosage";
    public static String Table_02_col_03="Test_Done";
    public static String Table_02_col_04="Recent_Medical_Report";
    public static String Table_02_col_05="Food_In";

    //table 03
    public static String Table_name_03="Skin_Tones";
    //col
    public static String Table_03_col_01="Type";
    public static String Table_03_col_02="Colour";

    //table 04
    public static String Table_name_04="Food_items";
    //col
    public static String Table_04_col_01="Sr_no";
    public static String Table_04_col_02="Food";
    public static String Table_04_col_03="Quantity";

    //table 05
    public static String Table_name_05="Medical_Report";
    //col
    public static String Table_05_col_01="Number";
    public static String Table_05_col_02="Date";
    public static String Table_05_col_03="Medical_Report";

    public Database(Context context) {
        super(context, Database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "+ Table_name_01 +" ( "
                        + Table_01_col_01 +" INTEGER PRIMARY KEY AUTOINCREMENT,"//ID
                        + Table_01_col_02 +" TEXT NOT NULL,"                    //Name
                        + Table_01_col_03 +" DATE NOT NULL,"                    //DOB
                        + Table_01_col_04 +" REAL NOT NULL,"                    //Height
                        + Table_01_col_05 +" REAL NOT NULL,"                    //Weight
                        + Table_01_col_06 +" TEXT NOT NULL,"                    //Gender
                        + Table_01_col_07 +" INTEGER NOT NULL);"                //Skin Tone
        );
        db.execSQL(
                "CREATE TABLE "+ Table_name_02 +" ( "
                        + Table_02_col_01 +" INTEGER PRIMARY KEY AUTOINCREMENT,"//ID
                        + Table_02_col_02 +" REAL NOT NULL,"                    //Dosage
                        + Table_02_col_03 +" TEXT NOT NULL,"                    //Test Done
                        + Table_02_col_04 +" REAL NOT NULL,"                    //Report
                        + Table_02_col_05 +" REAL NOT NULL);"                   //Food In
        );
        db.execSQL(
                "CREATE TABLE "+ Table_name_03 +" ( "
                        + Table_03_col_01 +" INTEGER NOT NULL,"                 //type
                        + Table_03_col_02 +" TEXT NOT NULL );"                  //colour
        );
        db.execSQL(
                "CREATE TABLE "+ Table_name_04 +" ( "
                        + Table_04_col_01 +" INTEGER NOT NULL,"
                        + Table_04_col_02 +" TEXT NOT NULL,"
                        + Table_04_col_03 +" REAL NOT NULL );"
        );
        db.execSQL(
                "CREATE TABLE "+ Table_name_05 +" ( "
                        + Table_05_col_01 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Table_05_col_02 +" DATE NOT NULL,"
                        + Table_05_col_03 +" REAL NOT NULL );"
        );
        fillSkinToneCol(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name_01);
        db.execSQL("DROP TABLE IF EXISTS "+Table_name_02);
        db.execSQL("DROP TABLE IF EXISTS "+Table_name_03);
        db.execSQL("DROP TABLE IF EXISTS "+Table_name_04);
        db.execSQL("DROP TABLE IF EXISTS "+Table_name_05);
        onCreate(db);
    }

    //insert into profile table
    public boolean insertProfile(String name,String dob,String height,String weight,String gender,
                                 int skinTone,String dosage,String testDone,String medicalReport,String foodIn){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(Table_01_col_07,skinTone);
        contentValues1.put(Table_01_col_03,dob);
        contentValues1.put(Table_01_col_06,gender);
        contentValues1.put(Table_01_col_04,height);
        contentValues1.put(Table_01_col_02,name);
        contentValues1.put(Table_01_col_05,weight);
        Long result1=db.insert(Table_name_01,null,contentValues1);

        ContentValues contentValues2=new ContentValues();
        contentValues2.put(Table_02_col_02,dosage);
        contentValues2.put(Table_02_col_03,testDone);
        contentValues2.put(Table_02_col_04,medicalReport);
        contentValues2.put(Table_02_col_05,foodIn);
        Long result2=db.insert(Table_name_02,null,contentValues2);

        if(result1==-1 || result2==-1)
            return false;
        else
            return true;

    }
    public boolean insertMedicalReport(String medical,String date){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues3=new ContentValues();
        contentValues3.put(Table_05_col_02,date);
        contentValues3.put(Table_05_col_03,medical);
        Long result=db.insert(Table_name_05,null,contentValues3);

        ContentValues contentValues4=new ContentValues();
        contentValues4.put(Table_02_col_04,medical);
        db.update(Table_name_02,contentValues4,Table_02_col_01="1",null);

        if(result==-1)
            return false;
        else
            return true;
    }
    public void fillSkinToneCol(SQLiteDatabase db){

        ContentValues values1=new ContentValues();
        values1.put(Table_03_col_01,1);
        values1.put(Table_03_col_02,"Very Fair");
        db.insert(Table_name_03,null,values1);
        ContentValues values2=new ContentValues();
        values2.put(Table_03_col_01,2);
        values2.put(Table_03_col_02,"Fair");
        db.insert(Table_name_03,null,values2);
        ContentValues values3=new ContentValues();
        values3.put(Table_03_col_01,3);
        values3.put(Table_03_col_02,"Light Brown");
        db.insert(Table_name_03,null,values3);
        ContentValues values4=new ContentValues();
        values4.put(Table_03_col_01,4);
        values4.put(Table_03_col_02,"Moderate Brown");
        db.insert(Table_name_03,null,values4);
        ContentValues values5=new ContentValues();
        values5.put(Table_03_col_01,5);
        values5.put(Table_03_col_02,"Dark Brown");
        db.insert(Table_name_03,null,values5);
        ContentValues values6=new ContentValues();
        values3.put(Table_03_col_01,6);
        values3.put(Table_03_col_02,"Deep Pigmented Dark Brown");
        db.insert(Table_name_03,null,values6);

    }
    public Cursor getProfileData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+Table_name_01,null);
        return res;
    }

    //get medical report
    public Cursor getMedicalReport(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+Table_05_col_02+", "+Table_05_col_03+" FROM "+Table_name_05+" ORDER BY "+Table_05_col_01+" DESC LIMIT 1",null);
        return cursor;
    }

    public Cursor getSkinToneData(int type){
        String sType=Integer.toString(type);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT "+Table_03_col_02+" FROM "+Table_name_03+ " WHERE "+Table_03_col_01+" == "+sType,null);
        return res;
    }

    public void putProfileData(String name,String dob,String height,String weight){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Table_01_col_02,name);
        contentValues.put(Table_01_col_03,dob);
        contentValues.put(Table_01_col_04,height);
        contentValues.put(Table_01_col_05,weight);
        db.update(Table_name_01,contentValues,Table_01_col_01="1",null);
    }
}
