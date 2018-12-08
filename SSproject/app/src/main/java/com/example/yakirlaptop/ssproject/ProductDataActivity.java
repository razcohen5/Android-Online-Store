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

public class ProductDataActivity extends AppCompatActivity {
    DatabaseOpenHelper dbhelper;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_data);

        setTitle("Product Data List");
        lv = findViewById(R.id.listview);
        dbhelper = new DatabaseOpenHelper(this);
        populateList();
    }

    private void populateList() {
        Cursor data = dbhelper.getProductData();
        ArrayList<String> listdata = new ArrayList<>();
        while(data.moveToNext()){
            listdata.add("Productname: "+data.getString(0)+"\nPrice: "+data.getString(1)+"\nQuantity: "+data.getString(2)+"\nImage Path: "+data.getString(3));

        }

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listdata);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = extractname(adapterView.getItemAtPosition(i).toString());
                Cursor data = dbhelper.getPrice(name);
                String price = null;
                while(data.moveToNext()){
                    price = data.getString(0);
                }
                if(price!=null){
                    Intent intent = new Intent(getApplicationContext(), EditProductActivity.class);
                    intent.putExtra("productname",name);
                    intent.putExtra("price",price);
                    startActivity(intent);
                }
                else{
                    toast("No price was generated for "+name);
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
