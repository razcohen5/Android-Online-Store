package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.R;

public class EditProductActivity extends AppCompatActivity {
    DatabaseOpenHelper dbhelper;
    private String productname;
    private String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        setTitle("Edit Product Data");
        dbhelper = new DatabaseOpenHelper(this);
        Intent intent = getIntent();
        productname = intent.getStringExtra("productname");
        price = intent.getStringExtra("price");
    }

    public void changeProduct(View view){
        Intent intent = new Intent(getApplicationContext(),ChangeProductActivity.class);
        intent.putExtra("productname",productname);
        startActivity(intent);
    }
    public void delete(View view){
        dbhelper.deleteProduct(productname,price);
        toast("product deleted");
        Intent intent = new Intent (getApplicationContext(), ListProductsActivity.class);
        startActivity(intent);
    }
    public void deleteAll(View view){
        dbhelper.deleteAllProducts();
        toast("All deleted");
        Intent intent = new Intent (getApplicationContext(), ListProductsActivity.class);
        startActivity(intent);
    }

    public void toast(String msg){
        Toast.makeText(this,msg ,Toast.LENGTH_LONG).show();
    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
        startActivity(intent);
    }
}
