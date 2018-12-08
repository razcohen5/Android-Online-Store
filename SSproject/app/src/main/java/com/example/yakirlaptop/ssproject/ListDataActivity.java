package com.example.yakirlaptop.ssproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    DatabaseOpenHelper dbhelper;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        setTitle("Data List");
        lv = findViewById(R.id.listview);
        dbhelper = new DatabaseOpenHelper(this);
        populateList();
        
    }

    private void populateList() {
        Cursor data = dbhelper.getData();
        ArrayList<String> listdata = new ArrayList<>();
        while(data.moveToNext()){
            listdata.add("Username: "+data.getString(0)+"\nPassword: "+data.getString(1)+"\nFirst Name: "+data.getString(2)+"\nLast Name: "+data.getString(3)+"\nEmail: " +data.getString(4)+"\nCredit Card: "+data.getString(5));

        }

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listdata);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = extractname(adapterView.getItemAtPosition(i).toString());
                Cursor data = dbhelper.getPass(name);
                String pass = null;
                while(data.moveToNext()){
                    pass = data.getString(0);
                }
                if(pass!=null){
                    Intent intent = new Intent(getApplicationContext(), EditDataActivity.class);
                    intent.putExtra("username",name);
                    intent.putExtra("password",pass);
                    startActivity(intent);
                }
                else{
                    toast("No password was generated for "+name);
                }
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
}
