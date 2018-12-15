package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.ObjectClasses.Customer;
import com.example.yakirlaptop.ssproject.ObjectClasses.Product;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ListView lv;
    private TextView TVdetails;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Server.getServer().setContext(this);
        customer = UserHolder.getUserHolder().getCustomer();
        customer.getCart().calculateTotalPrice();
        setTitle("Cart");
        lv = findViewById(R.id.listview);
        TVdetails = findViewById(R.id.TVdetails);
        TVdetails.setText("Purchase Details: " +
                "\nName: " + customer.getName() +
                "\nCredit Card: " + customer.getCreditcard() +
                "\nTotal Price: " + customer.getCart().getTotalprice());
        populateList();
    }

    private void populateList() {
        ArrayList<Product> cart = UserHolder.getUserHolder().getCustomer().getCart().getProducts();
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cart);
        lv.setAdapter(adapter);
    }

    public void makePayment(View view)
    {
        if(UserHolder.getUserHolder().getCustomer().getCart().isEmpty())
            Toast.makeText(this, "Your cart is empty.", Toast.LENGTH_LONG).show();
        else
        {
            UserHolder.getUserHolder().getCustomer().makePayment();
            Toast.makeText(this, "Purchase completed.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent (getApplicationContext(), CustomerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), CustomerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
