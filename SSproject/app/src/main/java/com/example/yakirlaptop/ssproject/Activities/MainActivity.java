package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.ObjectClasses.User;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;


public class MainActivity extends AppCompatActivity {
    EditText usernameET;
    EditText passwordET;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameET = findViewById(R.id.ETname);
        passwordET = findViewById(R.id.ETpass);
        tv = findViewById(R.id.textView);
        setTitle("Welcome");
        Server.getServer().setContext(this);
    }

    public void login(View view)
    {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        if( username.length() != 0 && password.length() != 0)
        {
            User user = Server.getServer().login(username,password);//get user from server
            if(user==null)//username not found or password is incorrect
                Toast.makeText(this, "Wrong username or password.", Toast.LENGTH_LONG).show();
            else {
                UserHolder.getUserHolder().setUser(user);//set userholder with the user from server
                if (UserHolder.getUserHolder().getUser().getAdmin() == 1)//admin user->send to admin activity
                {
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);//customer user->send to customer activity
                    startActivity(intent);
                }
            }
        }
        else
            Toast.makeText(this, "One or more fields are missing.", Toast.LENGTH_LONG).show();
    }

//    public void enterBuyer(View view){
//        String newUser = usernameET.getText().toString();
//        String newPass = passwordET.getText().toString();
//        if( (newUser.length() != 0 && newPass.length() != 0)){
//           if(dbhelper.login(newUser,newPass)){
//               Intent intent  = new Intent(getApplicationContext(), CustomerActivity.class);
//               startActivity(intent);
//           }
//           else{
//
//               Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show();
//           }
//        }
//        else {
//            Toast.makeText(this, "You must put something in the text field", Toast.LENGTH_LONG).show();
//        }
//
//    }
    public void signup(View view){
        Intent intent  = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
    }





    public void admin(View view){
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(intent);
    }


}
