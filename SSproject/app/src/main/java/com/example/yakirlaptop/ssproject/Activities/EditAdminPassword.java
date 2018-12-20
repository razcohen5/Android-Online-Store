package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.ObjectClasses.Admin;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

public class EditAdminPassword extends AppCompatActivity {
    EditText ETold;
    EditText ETnew1;
    EditText ETnew2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_password);
        ETold = findViewById(R.id.ETold);
        ETnew1 = findViewById(R.id.ETnew1);
        ETnew2 = findViewById(R.id.ETnew2);
        Server.getServer().setContext(this);
    }

    public void confirm(View view){
        if (!UserHolder.getUserHolder().getAdmin().getPassword().equals(ETold.getText().toString())){
            Toast.makeText(this,"Incorrect password.",Toast.LENGTH_LONG).show();
        }
        else if(!ETnew1.getText().toString().equals(ETnew2.getText().toString())){
            Toast.makeText(this,"Please confirm new password.",Toast.LENGTH_LONG).show();
        }
        else{
            UserHolder.getUserHolder().getAdmin().setPassword(ETnew1.getText().toString());
            Server.getServer().deleteUser("admin");
            Server.getServer().register("admin", UserHolder.getUserHolder().getAdmin().getPassword(), 1,"admin","","");
            Toast.makeText(this,"Password changed.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
