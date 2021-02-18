package com.example.fahadtestphase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity
{
    AdminTable admin;
    Button btnAdminRegistration1,btnRegBack1;
    EditText etRegUserName1,etRegPassword1,etRegRetyepw1,etRegMobNo1,etRegAddress1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etRegUserName1=(EditText) findViewById(R.id.etRegUserID);
        etRegPassword1=(EditText) findViewById(R.id.etRegPassword);

        etRegRetyepw1=(EditText) findViewById(R.id.etRegRetyePW);
        etRegMobNo1=(EditText) findViewById(R.id.etRegMobNo);
        etRegAddress1=(EditText) findViewById(R.id.etRegLocation);



        btnAdminRegistration1=(Button) findViewById(R.id.btnRegisterRP);

        btnRegBack1=(Button) findViewById(R.id.btnRegistrationCancel);

        admin=new AdminTable(this);

        btnRegBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //back to main
                Intent regPage=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(regPage);//go to registration activity

            }
        });
        btnAdminRegistration1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                DoRegistration();
            }
        });

    }//end on create


    //define function
    private void DoRegistration()
    {

        String username=etRegUserName1.getText().toString();

        String password= etRegPassword1.getText().toString();
        String retypePw=  etRegRetyepw1.getText().toString();
        String mobileNo=  etRegMobNo1.getText().toString();
        String address=    etRegAddress1.getText().toString();
        int mobileNo1=Integer.parseInt(mobileNo);
        if(username.equals("")&&password.equals("")&&retypePw.equals("")&&mobileNo.equals("")&&address.equals(""))
        {
            Toast.makeText(RegistrationActivity.this, "Error: Please enter correct data" , Toast.LENGTH_SHORT).show();

        }
        else
        {

            if (password.equals(retypePw))
            {
                boolean isInserted = admin.insertData(username, password, mobileNo1, address);

                if (isInserted == true)
                    Toast.makeText(RegistrationActivity.this, "User is registered", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RegistrationActivity.this, "Eror: data is not inserted", Toast.LENGTH_LONG).show();

            }
        }


    }
}
