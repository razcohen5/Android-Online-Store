package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

public class AddSupplierActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText email;
    EditText company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        Server.getServer().setContext(this);
        name = findViewById(R.id.ETname);
        phone = findViewById(R.id.ETphone);
        email = findViewById(R.id.ETemail);
        company = findViewById(R.id.ETcompany);
    }

    public void addSupplier(View view){
        String newName = name.getText().toString();
        String newPhone = phone.getText().toString();
        String newEmail = email.getText().toString();
        String newCompany = company.getText().toString();

        if (newName.length() == 0 || newPhone.length() == 0 || newEmail.length() == 0 || newCompany.length() == 0){
            Toast.makeText(this,"One or more fields are missing.",Toast.LENGTH_LONG).show();
        }
        else{
                if(UserHolder.getUserHolder().getAdmin().addSupplier(newName,newPhone,newEmail,newCompany)==false)
                    Toast.makeText(this, "Name is already taken.", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(this, "Supplier added.", Toast.LENGTH_LONG).show();
<<<<<<< HEAD
                    Intent intent = new Intent(getApplicationContext(), ListSuppliersActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
=======
                    Intent i = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(i);
>>>>>>> upstream/master
                }
            }
        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    }

