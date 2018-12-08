package com.example.yakirlaptop.ssproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("Admin Activity");
    }

    public void viewdata(View view){
        Intent intent = new Intent(getApplicationContext(), ListDataActivity.class);
        startActivity(intent);
    }

    public void viewProducts(View view){
        Intent intent = new Intent(getApplicationContext(), ProductDataActivity.class);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
    public void addProduct(View view){
        Intent intent =  new Intent(getApplicationContext(), AddProductActivity.class);
        startActivity(intent);
    }
}
