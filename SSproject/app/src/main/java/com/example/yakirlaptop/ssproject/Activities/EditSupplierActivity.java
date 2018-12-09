package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

import java.net.Inet4Address;

public class EditSupplierActivity extends AppCompatActivity {
    private int s_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_supplier);
        setTitle("Edit Supplier");
        Intent intent = getIntent();
        s_id = intent.getIntExtra("s_id",0);
        Server.getServer().setContext(this);
    }

    public void delete(View view){
        UserHolder.getUserHolder().getAdmin().deleteSupplier(s_id);
        Toast.makeText(this, "Supplier deleted.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
    public void deleteAll(View view){
        UserHolder.getUserHolder().getAdmin().deleteAllSuppliers();
        Server.getServer().deleteAllUsers();
        Toast.makeText(this,"All suppliers deleted." ,Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
}
