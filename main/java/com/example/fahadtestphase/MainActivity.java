package com.example.fahadtestphase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    Button btnLogin1,btnRegisteration1;
    EditText etUserName1,etPassword1;

AdminTable admin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);

        etUserName1=(EditText) findViewById(R.id.etUserID);
        etPassword1=(EditText) findViewById(R.id.etPassword);
        btnLogin1=(Button) findViewById(R.id.btnLogin);

        btnRegisteration1=(Button) findViewById(R.id.btnRegistration);

        admin=new AdminTable(this);

        btnLogin1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                login();//calling the method


            }
        });
        btnRegisteration1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent regPage=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(regPage);


            }
        });


    }//oncreate


    //define method
    private void login()
    {

        String username= etUserName1.getText().toString();
        String password=  etPassword1.getText().toString();

        if(username.equals("")&&password.equals(""))
        {
            Toast.makeText(MainActivity.this, "Error: Please enter correct data" , Toast.LENGTH_SHORT).show();

        }
        else
        {

            if(  admin.checkUserExist(username,password) )
            {

                Intent intent = new Intent(getBaseContext(), AdminConsole.class);

                startActivity(intent);


            }
            else
            {
                Toast.makeText(MainActivity.this, "Error: userid or password is wrong " , Toast.LENGTH_SHORT).show();

            }

        }


    }
}








