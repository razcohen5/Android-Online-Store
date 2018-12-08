package com.example.yakirlaptop.ssproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {
    DatabaseOpenHelper dbhelper;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        setTitle("Edit Data");
        dbhelper = new DatabaseOpenHelper(this);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
    }

    public void delete(View view){
        dbhelper.delete(username,password);
        toast("user deleted");
        Intent intent = new Intent (getApplicationContext(), ListDataActivity.class);
        startActivity(intent);
    }
    public void deleteAll(View view){
        dbhelper.deleteAll();
        toast("All deleted");
        Intent intent = new Intent (getApplicationContext(), ListDataActivity.class);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }

    public void toast(String msg){
        Toast.makeText(this,msg ,Toast.LENGTH_LONG).show();
    }
}
