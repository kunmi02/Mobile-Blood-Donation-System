package com.example.akinkunmi.blooddonor;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        FloatingActionButton add_history = (FloatingActionButton) findViewById(R.id.add_history);
        add_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // CreateAlertDialogWithTextinput();
                Snackbar.make(view, "Add Donation History", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void CreateAlertDialogWithTextinput() {


        AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
        LayoutInflater inflater=getLayoutInflater();
        View dialogview=inflater.inflate(R.layout.request_dialogue,null);
        builder.setView(dialogview);

        Button save=findViewById(R.id.save_btn);
        Button cancel=findViewById(R.id.cancel_btn);
        final EditText date=findViewById(R.id.date);
        final EditText pint=findViewById(R.id.pint);

        final AlertDialog dialog=builder.create();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(), "Not Saved", Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();

    }
}
