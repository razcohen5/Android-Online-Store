package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.ObjectClasses.Product;
import com.example.yakirlaptop.ssproject.ObjectClasses.Supplier;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

public class OrderProductActivity extends AppCompatActivity {
    TextView TVsupplier;
    EditText ETquantity;
    private int p_id;
    private Product product;
    private Supplier supplier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product);
        Server.getServer().setContext(this);
        Intent intent = getIntent();
        p_id = intent.getIntExtra("p_id",0);
        setTitle("Order Product");
        ETquantity = findViewById(R.id.ETquantity);
        TVsupplier = findViewById(R.id.TVsupplier);
        product = Server.getServer().getProductById(p_id);
        supplier = Server.getServer().getSupplierById(product.getS_id());
        TVsupplier.setText(supplier.toString());
    }

    public void order(View view)
    {
        String quantity = ETquantity.getText().toString();
        if(quantity.length()==0)
            Toast.makeText(this,"One or more fields are missing.",Toast.LENGTH_LONG).show();
        else if(Integer.parseInt(quantity)<=0)
            Toast.makeText(this,"Invalid quantity.",Toast.LENGTH_LONG).show();
        else
        {
            UserHolder.getUserHolder().getAdmin().order(p_id,Integer.parseInt(quantity));
            Toast.makeText(this,"Product ordered.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),ListProductsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), ListProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
