package com.example.yakirlaptop.ssproject.Activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.DatabaseAPI.DatabaseOpenHelper;
import com.example.yakirlaptop.ssproject.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChangeProductActivity extends AppCompatActivity {
    DatabaseOpenHelper dbhelper;
    TextView productname;
    EditText price;
    EditText quantity;
    private static int PICK_IMAGE_REQUEST = 1;
    ImageView imageView;
    Bitmap bitmap;
    String imgPath;
    private String product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_product);

        productname = findViewById(R.id.TVproduct);
        price = findViewById(R.id.ETprice);
        quantity = findViewById(R.id.ETquantity);
        dbhelper = new DatabaseOpenHelper(this);
        Intent intent = getIntent();
        product = intent.getStringExtra("productname");
        Cursor data1 = dbhelper.getPrice(product);
        Cursor data2 = dbhelper.getQuantity(product);
        Cursor data3 = dbhelper.getImgPath(product);
        String price = null;
        String quantity = null;
        imgPath = null;
        while(data1.moveToNext()){
            price = data1.getString(0);
        }
        while(data2.moveToNext()){
            quantity = data2.getString(0);
        }
        while(data3.moveToNext()){
            imgPath = data3.getString(0);
        }
        productname.setText(product);
        this.price.setText(price);
        this.quantity.setText(quantity);
        loadImageFromStorage(imgPath,product);

    }

    public void changePicture(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void addProduct(View view){
        int newPrice = Integer.parseInt(price.getText().toString());
        int newQuantity = Integer.parseInt(quantity.getText().toString());
        String newImg = imgPath;
        if (newPrice == 0 || newQuantity == 0 || newImg.length() == 0){
            Toast.makeText(this,"Please fill all of the above",Toast.LENGTH_LONG).show();
        }
        else{
                changeData(1,product,newPrice,newQuantity,newImg);
                Intent intent = new Intent(getApplicationContext(),ListProductsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }


    public void changeData(int p_id,String product, int price , int quantity, String img){
        boolean insertData = dbhelper.editProduct(p_id,product,price,quantity,img);
        if (insertData){
            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show();
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                saveToInternalStorage(bitmap,productname.getText().toString());
                imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveToInternalStorage(Bitmap bitmapImage, String name){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,name+".jpg");

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
        imgPath = directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path,String product)
    {

        try {
            File f=new File(path, product+".jpg");
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
        Intent intent = new Intent (getApplicationContext(), ListProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
