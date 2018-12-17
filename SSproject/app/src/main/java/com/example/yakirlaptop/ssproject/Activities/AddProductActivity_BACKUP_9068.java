package com.example.yakirlaptop.ssproject.Activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
<<<<<<< HEAD:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
import com.example.yakirlaptop.ssproject.ObjectClasses.Supplier;
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
import com.example.yakirlaptop.ssproject.Singletons.UserHolder;
=======
import com.example.yakirlaptop.ssproject.R;
import com.example.yakirlaptop.ssproject.Singletons.Server;
>>>>>>> upstream/master:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {
    EditText productname;
    EditText price;
    EditText quantity;
    private static int PICK_IMAGE_REQUEST = 1;
    ImageView imageView;
    Bitmap bitmap;
    String imgPath = null;
<<<<<<< HEAD:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
    Spinner dropdown;
    File directory;
    File mypath;
    ContextWrapper cw;
    ArrayList<String> suppliers;

=======
>>>>>>> upstream/master:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Server.getServer().setContext(this);
        productname = findViewById(R.id.ETname);
        price = findViewById(R.id.ETprice);
        quantity = findViewById(R.id.ETquantity);
<<<<<<< HEAD:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
        imageView = findViewById(R.id.imageView);
        dropdown = findViewById(R.id.spinner1);
        suppliers = Server.getServer().getSuppliersNames();
        cw = new ContextWrapper(getApplicationContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, suppliers);
        dropdown.setAdapter(adapter);
=======
>>>>>>> upstream/master:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
    }

    public void addPicture(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void addProduct(View view){
        String newProduct = productname.getText().toString();
        String newPrice = price.getText().toString();
        String newQuantity = quantity.getText().toString();
<<<<<<< HEAD:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
        int position = dropdown.getSelectedItemPosition();
        if (newProduct.length() == 0 || newPrice.length() == 0 || newQuantity.length() == 0 || imageView.getDrawable() == null|| position == dropdown.INVALID_POSITION)
                Toast.makeText(this,"One or more fields are invalid.",Toast.LENGTH_LONG).show();
        else if(Integer.parseInt(newPrice)<=0 || Integer.parseInt(newQuantity) <=0)
            Toast.makeText(this,"Price and quantity must be positive.",Toast.LENGTH_LONG).show();
=======
        String newImg = imgPath;
        if (newProduct.length() == 0 || newPrice.length() == 0 || newQuantity.length() == 0 || newImg == null){
            Toast.makeText(this,"Please fill all of the above",Toast.LENGTH_LONG).show();
        }
>>>>>>> upstream/master:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
        else{
            directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            mypath=new File(directory,newProduct+".jpg");
            imgPath = directory.getAbsolutePath()+"/"+newProduct+".jpg";
            Supplier supplier = Server.getServer().getSupplierByName(suppliers.get(position).split(",")[0]);
            if (UserHolder.getUserHolder().getAdmin().addProduct(supplier.getS_id(),newProduct,Integer.parseInt(newPrice),Integer.parseInt(newQuantity),imgPath) == false) {
                Toast.makeText(this, "Product already exists!", Toast.LENGTH_LONG).show();
            }
            else{
<<<<<<< HEAD:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
                saveToInternalStorage(bitmap);
                Toast.makeText(this,"Product added.",Toast.LENGTH_LONG).show();
=======
                //addData(newProduct,newPrice,newQuantity,newImg);
>>>>>>> upstream/master:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
                Intent intent = new Intent(getApplicationContext(),ListProductsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

<<<<<<< HEAD:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
=======
//    public void addData(String product, String price , String quantity, String img){
//        boolean insertData = dbhelper.addProducts(product,price,quantity,img);
//        if (insertData){
//            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_LONG).show();
//        }
//        else {
//            Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show();
//        }
//
//
//    }
>>>>>>> upstream/master:SSproject/app/src/main/java/com/example/yakirlaptop/ssproject/Activities/AddProductActivity.java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveToInternalStorage(Bitmap bitmapImage){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (getApplicationContext(), AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
