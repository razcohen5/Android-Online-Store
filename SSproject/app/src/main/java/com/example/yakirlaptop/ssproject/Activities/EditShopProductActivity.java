package com.example.yakirlaptop.ssproject.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.ObjectClasses.Product;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EditShopProductActivity extends AppCompatActivity {
    Product product;
    TextView TVproductname;
    TextView TVprice;
    TextView TVquantity;
    EditText ETquantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop_product);
        Server.getServer().setContext(this);
        setTitle("Edit Shop Product");
        Intent intent = getIntent();
        int p_id = intent.getIntExtra("p_id", 0);
        product = Server.getServer().getProductById(p_id);

        TVproductname = findViewById(R.id.TVproduct);
        TVprice = findViewById(R.id.TVprice);
        TVquantity = findViewById(R.id.TVquantity);
        ETquantity = findViewById(R.id.ETquantity);
        TVproductname.setText("Product name: " + product.getName());
        TVprice.setText("Price: " + Integer.toString(product.getPrice()));
        TVquantity.setText("Quantity: " + Integer.toString(product.getQuantity()));
        loadImageFromStorage(product.getImage());
    }

    public void addToCart(View view)
    {
        String quantity = ETquantity.getText().toString();
        if(quantity.length()==0)
            Toast.makeText(this,"One or more fields are missing.",Toast.LENGTH_LONG).show();
        else if(Integer.parseInt(quantity)>product.getQuantity())
            Toast.makeText(this,"There are not enough items in the shop.",Toast.LENGTH_LONG).show();
        else if(Integer.parseInt(quantity)<=0)
            Toast.makeText(this,"Invalid quantity.",Toast.LENGTH_LONG).show();
        else if(UserHolder.getUserHolder().getCustomer().getCart().Contains(product.getP_id()))
            Toast.makeText(this,"This product is already in your cart.",Toast.LENGTH_LONG).show();
        else
        {
            Toast.makeText(this,"Product added to cart.",Toast.LENGTH_LONG).show();
            UserHolder.getUserHolder().getCustomer().addToCart(new Product(product,Integer.parseInt(quantity)));
            Intent intent = new Intent(getApplicationContext(),ShopProductsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void loadImageFromStorage(String image)
    {

        try {
            File f=new File(image);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=findViewById(R.id.imageView);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), ShopProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
