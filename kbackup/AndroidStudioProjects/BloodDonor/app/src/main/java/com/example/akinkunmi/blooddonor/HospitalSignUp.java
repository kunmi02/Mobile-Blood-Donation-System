package com.example.akinkunmi.blooddonor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HospitalSignUp extends AppCompatActivity {
    EditText editTextuser_mail,editTextpassword,editTextConfirmPassword,editTextname,editTextblood_type,editTextlocation,editTextmobile_number;
    RequestHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_sign_up);

        editTextuser_mail=findViewById(R.id.hospital_email);
        editTextpassword=findViewById(R.id.hospital_password);
        editTextConfirmPassword=findViewById(R.id.hospital_PasswordConfirm);
        editTextname=findViewById(R.id.hospital_name);

        editTextlocation=findViewById(R.id.hospital_location);
        editTextmobile_number=findViewById(R.id.hospital_digit);
        Button HospitalRegister=findViewById(R.id.register_hospital);

        helper  = new RequestHelper(this);
    }
    public void addHospital(View view){
        String email=editTextuser_mail.getText().toString();
        String password=editTextpassword.getText().toString();
        String confirmPassword=editTextConfirmPassword.getText().toString();
        String name=editTextname.getText().toString();
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
            boolean isInserted = helper.insertDataHospital(email,password,name,location,mobile_number);
            if(isInserted == true)
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

                startActivity(new Intent(HospitalSignUp.this, UserLogin.class));
            }
        }

    } // end of adduser method
}
