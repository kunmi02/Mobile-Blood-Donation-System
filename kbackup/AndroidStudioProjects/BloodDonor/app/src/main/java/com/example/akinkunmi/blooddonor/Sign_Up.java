package com.example.akinkunmi.blooddonor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Sign_Up extends AppCompatActivity{
    EditText editTextuser_mail,editTextpassword,editTextConfirmPassword,editTextname,editTextblood_type,editTextlocation,editTextmobile_number;
    RequestHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        editTextuser_mail=findViewById(R.id.email);
        editTextpassword=findViewById(R.id.password);
        editTextConfirmPassword=findViewById(R.id.PasswordConfirm);
        editTextname=findViewById(R.id.UserName);
        //final String User_blood_type;
        editTextlocation=findViewById(R.id.location);
        editTextmobile_number=findViewById(R.id.digit);
        Button Register=findViewById(R.id.register);

        helper  = new RequestHelper(this);


        // spinner details
        Spinner bloodtype = findViewById(R.id.blood_type);
         ArrayAdapter<CharSequence> blood_group = ArrayAdapter.createFromResource(this, R.array.bloodgroups, android.R.layout.simple_spinner_item);
        blood_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodtype.setAdapter(blood_group);


    }
    public void addUser(View view){
        String email=editTextuser_mail.getText().toString();
        String password=editTextpassword.getText().toString();
        String confirmPassword=editTextConfirmPassword.getText().toString();
        String name=editTextname.getText().toString();
        String bloodgroup="A+";
        String location=editTextlocation.getText().toString();
        String mobile_number=editTextmobile_number.getText().toString();


        // check if any of the fields are vaccant
        if(email.equals("")||password.equals("")||confirmPassword.equals("")||name.equals("")||location.equals("")||mobile_number.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Some Fields are Vacant", Toast.LENGTH_LONG).show();
            return;
        }
// check if both password matches
        else if(!password.equals(confirmPassword))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        else{
          long id = helper.insertDataMember(email,password,name,bloodgroup,location,mobile_number);
          if(id<=0)
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                editTextuser_mail.setText("");
                editTextpassword.setText("");
                editTextConfirmPassword.setText("");
                editTextname.setText("");
                editTextlocation.setText("");
                editTextmobile_number.setText("");
            } else
            {
                Message.message(getApplicationContext(),"Insertion Successful \n You can now Log in");
                editTextuser_mail.setText("");
                editTextpassword.setText("");
                editTextConfirmPassword.setText("");
                editTextname.setText("");
                editTextlocation.setText("");
                editTextmobile_number.setText("");

                startActivity(new Intent(Sign_Up.this, UserLogin.class));
            }
        }

    } // end of adduser method
}

