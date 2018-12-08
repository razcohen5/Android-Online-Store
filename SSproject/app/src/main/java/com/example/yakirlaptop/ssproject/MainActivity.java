package com.example.yakirlaptop.ssproject;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseOpenHelper dbhelper;
    EditText username;
    EditText password;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.ETname);
        password = findViewById(R.id.ETpass);
        tv = findViewById(R.id.textView);
        dbhelper = new DatabaseOpenHelper(this);

        setTitle("Welcome");

    }

    public void enterBuyer(View view){
        String newUser = username.getText().toString();
        String newPass = password.getText().toString();
        if( (newUser.length() != 0 && newPass.length() != 0)){
           if(dbhelper.login(newUser,newPass)){
               Intent intent  = new Intent(getApplicationContext(), BuyerActivity.class);
               startActivity(intent);
           }
           else{

               Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show();
           }
        }
        else {
            Toast.makeText(this, "You must put something in the text field", Toast.LENGTH_LONG).show();
        }

    }
    public void signup(View view){
        Intent intent  = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
    }





    public void admin(View view){
        String newUser = username.getText().toString();
        String newPass = password.getText().toString();
        if(newUser.equals("admin") && newPass.equals("admin")) {
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Only admin can access", Toast.LENGTH_LONG).show();
        }
    }


}
