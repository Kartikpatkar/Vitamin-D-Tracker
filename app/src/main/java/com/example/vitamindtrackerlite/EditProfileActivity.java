package com.example.kartikpatkar.vitamindtrackerlite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity {

    EditText name,dob,height,weight;
    Button doneButton;
    String nameTemp,dobTemp,heightTemp,weightTemp;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");

        nameTemp=getIntent().getExtras().getString("Name");
        dobTemp=getIntent().getExtras().getString("DOB");
        heightTemp=getIntent().getExtras().getString("Height");
        weightTemp=getIntent().getExtras().getString("Weight");

        name=findViewById(R.id.editTextName);
        dob=findViewById(R.id.editTextDOB);
        height=findViewById(R.id.editTextHeight);
        weight=findViewById(R.id.editTextWeight);

        name.setText(nameTemp);
        dob.setText(dobTemp);
        height.setText(heightTemp);
        weight.setText(weightTemp);

        db=new Database(getApplicationContext());

        doneButton=findViewById(R.id.buttonDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTemp=name.getText().toString();
                dobTemp=dob.getText().toString();
                heightTemp=height.getText().toString();
                weightTemp=weight.getText().toString();

                final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(EditProfileActivity.this);
                alertDialogBuilder.setTitle("Edit");
                alertDialogBuilder.setIcon(R.drawable.ic_add_alert_black_24dp);
                alertDialogBuilder.setMessage("Do you want to edit your profile?");
                alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.putProfileData(nameTemp,dobTemp,heightTemp,weightTemp);
                    }
                });

                alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialogBuilder.show();


            }
        });


    }
}
