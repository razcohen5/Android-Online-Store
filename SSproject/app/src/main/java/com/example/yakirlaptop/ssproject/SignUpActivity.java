package com.example.yakirlaptop.ssproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    DatabaseOpenHelper dbhelper;
    EditText first;
    EditText last;
    EditText un;
    EditText pass;
    EditText email;
    EditText credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbhelper = new DatabaseOpenHelper(this);
        first = findViewById(R.id.ETfirst);
        last = findViewById(R.id.ETlast);
        un = findViewById(R.id.ETuser);
        pass = findViewById(R.id.ETpass);
        email = findViewById(R.id.ETmail);
        credit = findViewById(R.id.ETcredit);
        setTitle("Sign Up");
    }

    public void signup(View view){
        String newFirst = first.getText().toString();
        String newLast = last.getText().toString();
        String newUser = un.getText().toString();
        String newPass = pass.getText().toString();
        String newMail = email.getText().toString();
        String newCredit = credit.getText().toString();
        if (newFirst.length() == 0 || newLast.length() == 0 || newUser.length() == 0 || newPass.length() == 0 || newMail.length() == 0 || newCredit.length() == 0){
            Toast.makeText(this,"Please fill all of the above",Toast.LENGTH_LONG).show();
        }
        else{
            if (dbhelper.alreadyExists(newUser)) {
                Toast.makeText(this, "User already exists!", Toast.LENGTH_LONG).show();
            }
            else{
                addData(newUser, newPass,newFirst,newLast,newMail,newCredit);
            }
        }
    }

    public void addData(String username, String password , String firstname, String lastname, String email, String creditcard){
        boolean insertData = dbhelper.addData(username,password,firstname,lastname,email,creditcard);
        if (insertData){
            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show();
        }


    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
