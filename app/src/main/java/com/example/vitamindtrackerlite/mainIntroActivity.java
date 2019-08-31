package com.example.kartikpatkar.vitamindtrackerlite;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class mainIntroActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LocationListener, SensorEventListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    java.util.Date currentTime;
    Database mydb;
    Handler handler;
    double localLatitude,localLongitude,report;
    String locationText,time,date,mReport,dateR;
    LocationManager locationManager;
    VitaminDCalculation VDC;
    TextView textSEA,textLocation,DProgress;
    Button estimateButton;
    TextView textUVI;
    FloatingActionMenu menu;
    FloatingActionButton FABMedical,FABFood;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            textUVI.setText("" + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @SuppressLint({"WrongViewCast", "ServiceCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maip_page);
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer =findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //intro layout

        //location
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},101);
        }
        getLocation();

        textLocation=findViewById(R.id.textViewLocation);
        textLocation.setText(locationText);

        //date
        Calendar calendar=Calendar.getInstance();
        date=DateFormat.getDateInstance().format(calendar.getTime());

        //time
        Calendar cal=Calendar.getInstance(TimeZone.getDefault());
        currentTime= cal.getTime();
        DateFormat date =new SimpleDateFormat("HH:mm");
        date.setTimeZone(TimeZone.getDefault());
        time=date.format(currentTime);

        //sun elevation
        //N
        Calendar calendart=Calendar.getInstance();
        int dayOfYear=calendart.get(Calendar.DAY_OF_YEAR);//day of the year

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
        Double SHA=(hours-12)*15+localLatitude+TC;

        //sun zenith angle SZA=arccos(sin(latitude)*sin(D)+cos(latitude)*cos(D)*cos(SHA))
        Double SZA=toDegrees(acos(sin(toRadians(localLatitude))*sin(toRadians(D))+
                cos(toRadians(localLatitude))*cos(toRadians(D))*cos(toRadians(SHA))));

        //Sun Elevation Angle SEA=90-SZA
        double SEA=90-SZA;
        textSEA=findViewById(R.id.textViewSEA);
        textSEA.setText(String.format("%.2f",SEA)+" degress");

        //Light sensing value textView
        textUVI=findViewById(R.id.textUVI);

        //Light sensor selection
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        estimateButton=findViewById(R.id.buttonEstimate);
        estimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uvi=textUVI.getText().toString();
                Intent intent=new Intent(mainIntroActivity.this,SkinExposureActivity.class);
                intent.putExtra("UVI",uvi);
                startActivity(intent);
            }
        });

        //Floating button menu
        menu=findViewById(R.id.menu);
        FABMedical=findViewById(R.id.menu_item_01);
        FABMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMedicalReport();

            }
        });

        FABFood=findViewById(R.id.menu_item_02);
        FABFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //D level
        DProgress=findViewById(R.id.dLevel);
        mydb=new Database(getApplicationContext());
        handler=new Handler();

        final Runnable r=new Runnable() {
            @Override
            public void run() {
                Cursor res= mydb.getMedicalReport();
                if(res.getCount()==0){
                    Toast.makeText(mainIntroActivity.this,"No Data Found",Toast.LENGTH_LONG).show();
                }
                while (res.moveToNext()){
                    dateR=res.getString(0);
                    mReport=res.getString(1);
                }
                if(mReport!=null) {
                    //meter
                    int D = Integer.parseInt(mReport);
                    DProgress.setText(mReport);
                    if (D < 30)
                        DProgress.setTextColor(Color.RED);
                    else if (D >= 30 && D <= 60)
                        DProgress.setTextColor(Color.GREEN);
                    else
                        DProgress.setTextColor(Color.RED);
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(r,1000);
    }

    @Override
    public void onBackPressed() {
        drawerLayout =findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maip_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent=new Intent(this,ProfileDetailsActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_foodSuggestions) {
            Intent intent=new Intent(this,FoodSuggestionsActivity.class);
            startActivity(intent);


        }else if (id == R.id.nav_contact) {


        } else if (id == R.id.nav_support) {


        }  else if (id == R.id.nav_acknowledgement) {

        }

        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Floating Action menu
    public void addMedicalReport(){
        //Intent intent=new Intent(this,NewMedicalReportActivity.class);
        //startActivity(intent);
        final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("New Medical Report");
        alertDialogBuilder.setIcon(R.drawable.ic_assignment_black_24dp);
        final EditText inputMedicalReport=new EditText(this);
        inputMedicalReport.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputMedicalReport.setHint("nl/gm");
        alertDialogBuilder.setView(inputMedicalReport);

        alertDialogBuilder.setPositiveButton("INSERT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if((inputMedicalReport.getText().toString()!=null && ((inputMedicalReport.getText().toString()).length()==0))
                report=Double.valueOf(inputMedicalReport.getText().toString());
                Toast.makeText(mainIntroActivity.this, Double.toString(report),Toast.LENGTH_LONG).show();
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
                mydb.insertMedicalReport(Double.toString(report), currentDate);
            }
        });

        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.show();
    }

    //location
    public  void getLocation() {
        try{
            locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5,
                     this);
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        localLatitude=location.getLatitude();
        localLongitude=location.getLongitude();
        //locationText.setText("Latitude: "+location.getLatitude()+"\nLongitude: "+location.getLongitude());
        try{
            Geocoder geocoder=new Geocoder(this,Locale.getDefault());
            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if (addresses.size() > 0) {
                textLocation.setText(addresses.get(0).getAdminArea()+"-"+addresses.get(0).getCountryName());
            }
            //locationText.setText(addresses.get(0).getAddressLine(0));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    public void onProviderEnabled(String provider) {

    }
    public void onProviderDisabled(String provider) {
        Toast.makeText(this,"Please Enable GPS and Internet",Toast.LENGTH_LONG).show();
    }
}
