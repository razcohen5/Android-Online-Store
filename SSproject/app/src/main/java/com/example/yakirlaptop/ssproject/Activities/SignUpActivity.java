package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;

import static com.example.yakirlaptop.ssproject.Singletons.Server.getServer;

public class SignUpActivity extends AppCompatActivity {
    EditText name;
    EditText un;
    EditText pass;
    EditText email;
    EditText credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.ETname);
        un = findViewById(R.id.ETuser);
        pass = findViewById(R.id.ETpass);
        email = findViewById(R.id.ETmail);
        credit = findViewById(R.id.ETcredit);
        setTitle("Sign Up");
        Server.getServer().setContext(this);
    }

    public void signUp(View view){
        String newName = name.getText().toString();
        String newUsername = un.getText().toString();
        String newPass = pass.getText().toString();
        String newMail = email.getText().toString();
        String newCredit = credit.getText().toString();
        if (name.length() == 0 || newUsername.length() == 0 || newPass.length() == 0 || newMail.length() == 0 || newCredit.length() == 0){
            Toast.makeText(this,"One or more fields are missing.",Toast.LENGTH_LONG).show();
        }
        else {
                if(getServer().register(newUsername,newPass,0,newName,newMail,newCredit)==true)
                {
                    Toast.makeText(this,"Registered.",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(this,"Username is already taken.",Toast.LENGTH_LONG).show();
        }
    }

//    public void addData(String username, String password , String name, String email, String creditcard){
//        boolean insertData = dbhelper.addUser(username,password,name,email,creditcard);
//        if (insertData){
//            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_LONG).show();
//        }
//        else {
//            Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show();
//        }
//
//
//    }

    public void signup(View view){
        Intent intent  = new Intent(getApplicationContext(),SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
