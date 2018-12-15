package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

public class EditProductActivity extends AppCompatActivity {
    private int p_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        setTitle("Edit Product");
        Intent intent = getIntent();
        p_id = intent.getIntExtra("p_id",0);
        Server.getServer().setContext(this);
    }
//
//    public void changeProduct(View view){
//        Intent intent = new Intent(getApplicationContext(),ChangeProductActivity.class);
//        intent.putExtra("productname",p_id);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }

    public void delete(View view){
        UserHolder.getUserHolder().getAdmin().deleteProduct(p_id);
        Toast.makeText(this,"Product deleted." ,Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), ListProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void deleteAll(View view){
        UserHolder.getUserHolder().getAdmin().deleteAllProducts();
        Toast.makeText(this,"All products deleted." ,Toast.LENGTH_LONG).show();
        Intent intent = new Intent (getApplicationContext(), ListProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), ListProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
