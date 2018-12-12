package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;

import java.util.ArrayList;

public class ListSuppliersActivity extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_suppliers);

        setTitle("Suppliers List");
        lv = findViewById(R.id.listviews);
        Server.getServer().setContext(this);
        populateList();
    }

    private void populateList() {
        ArrayList<String> supplierslist = Server.getServer().getTableList("suppliers");
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,supplierslist);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s_idString = extractname(adapterView.getItemAtPosition(i).toString());
                int s_id = Integer.parseInt(s_idString);
                Intent intent = new Intent(getApplicationContext(), EditSupplierActivity.class);
                intent.putExtra("s_id",s_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
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
