package com.example.akinkunmi.blooddonor;

import android.app.AlertDialog;
import  android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Donorlistview extends AppCompatActivity {

    Button button;
    Context context;
    CardView cardview;
    LayoutParams layoutparams;
    TextView textview;
    TextView bloodview;
    TextView location;
    RelativeLayout relativeLayout;
    LinearLayout myown;
    ScrollView parent;

    RequestHelper myDb;
    String digit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorlistview);
        myDb = new RequestHelper(this);
        String id;
        /**TextView name_name = findViewById(R.id.name_name);
        TextView view_blood = findViewById(R.id.bd_type);
       TextView city = findViewById(R.id.city);
       TextView country = findViewById(R.id.country);

        ImageView make_call = findViewById(R.id.call);
        ImageView send_text = findViewById(R.id.message);

        make_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Donorlistview.this, "Call "+ digit, Toast.LENGTH_LONG).show();
            }
        });
        send_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Donorlistview.this, "message "+ digit, Toast.LENGTH_LONG).show();
            }
        }); **/

        context = getApplicationContext();

       // parent = findViewById(R.id.checkview);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);

        scrollView.addView(linearLayout);


       // View result=findViewById(R.id.checkview);
        Cursor res = myDb.getAllDonorRequest();
        if (res.getCount() == 0) {
            // show message
            //showMessage("Error", "Nothing found");
            return;
        }

       while (res.moveToNext()) {

            digit=res.getString(6);
           textview = new TextView(this);
           bloodview=new TextView(this);
           location=new TextView(this);
           LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
           params6.gravity = Gravity.CENTER;
           params6.setMargins(0, 30, 0, 30);
           bloodview.setText(res.getString(3));
           textview.setText(res.getString(1));
           location.setText(res.getString(5));
           textview.setTextSize(30);
           textview.setAllCaps(true);
           bloodview.setTextSize(20);
           location.setTextSize(20);
           textview.setTextColor(Color.RED);
           bloodview.setTextColor(Color.RED);
           location.setTextColor(Color.RED);
           textview.setPadding(15, 15, 0, 0);
           bloodview.setPadding(15, 0, 0, 0);
           location.setPadding(0, 0, 0, 15);
           textview.setGravity(Gravity.CENTER_HORIZONTAL);
           bloodview.setGravity(Gravity.CENTER_HORIZONTAL);
           location.setGravity(Gravity.CENTER_HORIZONTAL);
           ImageView imageView = new ImageView(this);
           imageView.setImageResource(R.drawable.ic_phone_black_24dp);
           imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Toast.makeText(getApplicationContext(), "Call" + digit, Toast.LENGTH_LONG).show();
                   Intent callintent = new Intent(Intent.ACTION_CALL);
                   callintent.setData(Uri.parse(" Call: " + digit));
                   if(ContextCompat.checkSelfPermission(Donorlistview.this,Manifest.permission.CALL_PHONE) !=
                           PackageManager.PERMISSION_GRANTED){
                       return;
                   }
                   startActivity(callintent);

               }
           });

           ImageView msgView = new ImageView(this);
           msgView.setImageResource(R.drawable.ic_message);
           msgView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   //Toast.makeText(getApplicationContext(), "Send Message to" + digit, Toast.LENGTH_LONG).show();
                 sendmysms();


                   }
           });

           linearLayout.addView(textview);
           linearLayout.addView(bloodview);
           linearLayout.addView(location);
           linearLayout.addView(imageView);
           linearLayout.addView(msgView);



       }


        LinearLayout linearLayout1 = findViewById(R.id.checkview);
        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView);
        }


    }



    public void sendmysms() {

        String phone = digit.trim();
        String message = "Hello, I will like to make blood donation to you";
        //Toast.makeText(getApplicationContext(), "Send Message to" + digit, Toast.LENGTH_LONG).show();
        //Check if the phoneNumber is empty
       if (phone.isEmpty() && phone.length()<11) {
            Toast.makeText(getApplicationContext(), " Phone Number is not valid", Toast.LENGTH_LONG).show();
        } else {

            SmsManager sms = SmsManager.getDefault();
            // if message length is too long messages are divided
            List<String> messages = sms.divideMessage(message);
            for (String msg : messages) {

                PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
                PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);
                sms.sendTextMessage(phone, null, msg, sentIntent, deliveredIntent);

            }
        }
    }



}
