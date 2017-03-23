package com.example.hp.ekumid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity {
    Button submit;
    EditText name,contact,altcontact,blood,age,email;
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        submit=(Button)findViewById(R.id.submit);

        name=(EditText)findViewById(R.id.name);
        contact=(EditText)findViewById(R.id.contact);
        altcontact=(EditText)findViewById(R.id.altcontact);
        blood=(EditText)findViewById(R.id.blood);
        email=(EditText)findViewById(R.id.email);
        age=(EditText)findViewById(R.id.age);

        click=(TextView)findViewById(R.id.click);




        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get Refferences of Views

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub



                String namevalue = name.getText().toString();
                String contactvalue = contact.getText().toString();
                String altcontactvalue = altcontact.getText().toString();
                String agevalue = age.getText().toString();
                String bloodvalue = blood.getText().toString();
                String emailvalue = email.getText().toString();

                // check if any of the fields are vaccant
                if(namevalue.equals("")||contactvalue.equals("")||agevalue.equals("")||bloodvalue.equals("")||emailvalue.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                if(contactvalue.length()!=10){
                    contact.setError("Enter valid mobile no. ");

                }
                if(altcontactvalue.length()!=10){
                    altcontact.setError("Enter valid mobile no.");
                }
                else
                {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(namevalue, contactvalue, altcontactvalue, agevalue, bloodvalue, emailvalue);
                    Toast.makeText(getApplicationContext(), " Successfully Registered ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }


}
