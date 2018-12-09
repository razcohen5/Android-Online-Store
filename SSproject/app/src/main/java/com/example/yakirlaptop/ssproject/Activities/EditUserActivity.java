package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

public class EditUserActivity extends AppCompatActivity {
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        setTitle("Edit User");
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        Server.getServer().setContext(this);
    }

    public void delete(View view){
        UserHolder.getUserHolder().getAdmin().deleteUser(username);
        Toast.makeText(this, "User deleted.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
    public void deleteAll(View view){
        UserHolder.getUserHolder().getAdmin().deleteAllUsers();
        Server.getServer().deleteAllUsers();
        Toast.makeText(this,"All users deleted." ,Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
}
