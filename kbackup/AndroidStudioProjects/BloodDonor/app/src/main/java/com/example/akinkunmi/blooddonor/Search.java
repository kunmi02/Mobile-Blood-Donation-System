package com.example.akinkunmi.blooddonor;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    RequestHelper myDb;
    String item;
    String locality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        myDb = new RequestHelper(this);
        Spinner bloodtype = findViewById(R.id.blood_type);
        ArrayAdapter<CharSequence> blood_group = ArrayAdapter.createFromResource(this, R.array.bloodgroups, android.R.layout.simple_spinner_item);
        blood_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodtype.setAdapter(blood_group);

        bloodtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        TextView search_location=findViewById(R.id.location_search);
        locality=search_location.getText().toString();
        Button search=findViewById(R.id.search_go_btn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //getlist(locality,item);

                Cursor res = myDb.getrequestList(locality,item);
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
                    Toast.makeText(Search.this, "No data that matches", Toast.LENGTH_LONG).show();
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
                showMessage("Data",buffer.toString());


            }
        });


}


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}