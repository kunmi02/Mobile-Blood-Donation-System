package com.example.akinkunmi.blooddonor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Request extends AppCompatActivity {
    EditText editRequest_name,editRequestmail,editrequestCountry,editrequestcity,editRequestnumber;
    Button Request;
    RequestHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        myDb = new RequestHelper(this);
        editRequest_name=findViewById(R.id.request_name);
        editRequestmail=findViewById(R.id.request_email);

        editrequestCountry=findViewById(R.id.request_country);
        //final String User_blood_type;
        editrequestcity=findViewById(R.id.request_city);
        editRequestnumber=findViewById(R.id.request_digit);
        Request=findViewById(R.id.request_go_btn);

        Spinner request_bloodtype = findViewById(R.id.request_blood_type);
        ArrayAdapter<CharSequence> blood_group = ArrayAdapter.createFromResource(this, R.array.bloodgroups, android.R.layout.simple_spinner_item);
        blood_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        request_bloodtype.setAdapter(blood_group);

        createRequest();
    }

    public  void createRequest() {
        Request.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bloodgroup="AB+";
                        String Request_name=editRequest_name.getText().toString();
                        String Request_mail=editRequestmail.getText().toString();
                        String Request_country=editrequestCountry.getText().toString();
                        String Request_city=editrequestcity.getText().toString();
                        String Request_number=editRequestnumber.getText().toString();
                        // check if any of the fields are vaccant
                        if(Request_name.equals("")||Request_mail.equals("")||Request_country.equals("")||Request_city.equals("")||Request_number.equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Some Fields are Vacant", Toast.LENGTH_LONG).show();
                            return;
                        }

                        boolean isInserted = myDb.insertDataBloodRequest(Request_name,Request_mail,bloodgroup,Request_country,Request_city,Request_number);
                        if(isInserted == true) {
                            Toast.makeText(Request.this, "Request made Successfully \n You will be contacted by a donor soon", Toast.LENGTH_LONG).show();
                            editRequest_name.setText("");
                            editRequestmail.setText("");
                            editrequestCountry.setText("");
                            editrequestcity.setText("");
                            editRequestnumber.setText("");
                        }
                        else {
                            Toast.makeText(Request.this, "Request is not made \n Please try again", Toast.LENGTH_LONG).show();
                            editRequest_name.setText("");
                            editRequestmail.setText("");
                            editrequestCountry.setText("");
                            editrequestcity.setText("");
                            editRequestnumber.setText("");
                        }
                    }
                }
        );
    }
}
