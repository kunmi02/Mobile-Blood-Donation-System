package com.example.akinkunmi.blooddonor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class HomeScreen extends AppCompatActivity {

    AlertDialog alertDialog1;
    CharSequence[] options = {" Individual", " Blood Bank (Hospital) "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Button login = findViewById(R.id.login_btn);
        Button signUp = findViewById(R.id.SignUp_btn);
        TextView search_txt = findViewById(R.id.Search_txt);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, UserLogin.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(HomeScreen.this, Sign_Up.class));
                CreateAlertDialogWithRadioButtonGroup();
            }
        });

        search_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, Search.class));
            }
        });

    }

    public void CreateAlertDialogWithRadioButtonGroup() {


        AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);

        builder.setTitle("Register as");

        builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:

                        startActivity(new Intent(HomeScreen.this, Sign_Up.class));
                        break;
                    case 1:

                        startActivity(new Intent(HomeScreen.this, HospitalSignUp.class));
                        break;

                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();
    }
}
