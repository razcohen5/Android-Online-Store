package com.example.yakirlaptop.ssproject.Activities;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yakirlaptop.ssproject.R;

public class CustomerActivity extends AppCompatActivity {
    SQLiteOpenHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        setTitle("Shop");
    }
}
