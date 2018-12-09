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

public class ListProductsActivity extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        setTitle("Products List");
        lv = findViewById(R.id.listview);
        Server.getServer().setContext(this);
        populateList();
    }

    private void populateList() {
        ArrayList<String> productslist = Server.getServer().getTableList("products");
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,productslist);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String p_idString = extractname(adapterView.getItemAtPosition(i).toString());
                int p_id = Integer.parseInt(p_idString);
                Intent intent = new Intent(getApplicationContext(), EditProductActivity.class);
                intent.putExtra("p_id",p_id);
                startActivity(intent);
            }
        });
    }

    private String extractname(String name){
        String[] split = name.split("\n");
        String[] split2 = split[0].split(" ");

        return split2[1];


    }
}
