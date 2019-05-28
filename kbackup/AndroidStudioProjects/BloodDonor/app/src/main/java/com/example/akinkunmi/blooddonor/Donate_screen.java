package com.example.akinkunmi.blooddonor;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Donate_screen extends AppCompatActivity {
    RequestHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_screen);
        myDb = new RequestHelper(this);
        Button Donate=findViewById(R.id.get_btn);

        Donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  getAllRequest();
               startActivity(new Intent(Donate_screen.this, Donorlistview.class));

            }
        });


    }
    public void getAllRequest(){
        Cursor res = myDb.getAllDonorRequest();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Email :"+ res.getString(2)+"\n");
            buffer.append("Blood_group :"+ res.getString(3)+"\n");
            buffer.append("Country :"+ res.getString(4)+"\n");
            buffer.append("City :"+ res.getString(5)+"\n");
            buffer.append("State :"+ res.getString(3)+"\n\n");
        }

        // Show all data
        showMessage("Data",buffer.toString());
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
