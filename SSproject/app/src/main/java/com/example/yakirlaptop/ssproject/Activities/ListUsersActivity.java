package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;

import java.util.ArrayList;

public class ListUsersActivity extends AppCompatActivity {
    //DatabaseOpenHelper dbhelper;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        setTitle("Users List");
        lv = findViewById(R.id.listview);
        //dbhelper = new DatabaseOpenHelper(this);
        Server.getServer().setContext(this);
        populateList();
    }

    private void populateList() {
        ArrayList<String> userslist = Server.getServer().getTableList("users");
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,userslist);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = extractname(adapterView.getItemAtPosition(i).toString());
                    Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);
                    intent.putExtra("username",name);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
        });
    }

    public void toast(String msg)
    {
        Toast.makeText(this,msg ,Toast.LENGTH_LONG).show();
    }

    private String extractname(String name){
        String[] split = name.split("\n");
        String[] split2 = split[0].split(" ");

        return split2[1];


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
