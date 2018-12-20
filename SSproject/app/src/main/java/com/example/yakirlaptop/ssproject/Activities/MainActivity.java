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
                Intent intent;
                if (UserHolder.getUserHolder().getUser().getAdmin() == 1)//admin user->send to admin activity
                    intent = new Intent(getApplicationContext(), AdminActivity.class);
                else
                    intent = new Intent(getApplicationContext(), CustomerActivity.class);//customer user->send to customer activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        else
            Toast.makeText(this, "One or more fields are missing.", Toast.LENGTH_LONG).show();
    }

    public void signup(View view){
        Intent intent  = new Intent(getApplicationContext(),SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
