package com.example.yakirlaptop.ssproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME_1 = "userDB";
    private static final String COL_1_1 = "username";
    private static final String COL_1_2 = "password";
    private static final String COL_1_3 = "firstname";
    private static final String COL_1_4 = "lastname";
    private static final String COL_1_5 = "email";
    private static final String COL_1_6 = "creditcard";
    private static final String TABLE_NAME_2 = "productsDB";
    private static final String COL_2_1 = "productname";
    private static final String COL_2_2 = "price";
    private static final String COL_2_3 = "quantity";
    private static final String COL_2_4 = "image";




    public DatabaseOpenHelper(Context context) {
        super(context, "db",null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE "+TABLE_NAME_1+" ("+COL_1_1+" TEXT, "+COL_1_2+" TEXT,"+COL_1_3+" TEXT,"+COL_1_4+" TEXT,"+COL_1_5 +" TEXT,"+COL_1_6+" TEXT)";
        String createTable2 = "CREATE TABLE "+TABLE_NAME_2+" ("+COL_2_1+" TEXT, "+COL_2_2+" TEXT,"+COL_2_3+" TEXT,"+COL_2_4+" TEXT)";
        sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL(createTable2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String username, String password ,String firstname , String lastname, String email , String creditcard){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1_1,username);
        cv.put(COL_1_2,password);
        cv.put(COL_1_3,firstname);
        cv.put(COL_1_4,lastname);
        cv.put(COL_1_5,email);
        cv.put(COL_1_6,creditcard);

        long result = db.insert(TABLE_NAME_1,null,cv);
        if (result == -1){
            return false;
        }
        return true;

    }
    public boolean changeProduct(String product,String price,String quantity, String imgpath){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2_2,price);
        cv.put(COL_2_3,quantity);
        cv.put(COL_2_4,imgpath);

        long result = db.update(TABLE_NAME_2,cv,COL_2_1+"='"+product+"'",null);
        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean addProducts(String productname, String price ,String quantity , String img){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2_1,productname);
        cv.put(COL_2_2,price);
        cv.put(COL_2_3,quantity);
        cv.put(COL_2_4,img);
        long result = db.insert(TABLE_NAME_2,null,cv);
        if (result == -1){
            return false;
        }
        return true;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_1;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getProductData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_2;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public boolean alreadyExists(String username){
        String pass = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_1_2+" FROM "+TABLE_NAME_1+" WHERE "+COL_1_1+" = '"+username+"'";
        Cursor data = db.rawQuery(query,null);
        while(data.moveToNext()){
            pass = data.getString(0);
        }
        if (pass == null){
            return false;
        }
        return true;
    }

    public boolean productAlreadyExists(String product){
        String price = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_2_2+" FROM "+TABLE_NAME_2+" WHERE "+COL_2_1+" = '"+product+"'";
        Cursor data = db.rawQuery(query,null);
        while(data.moveToNext()){
            price = data.getString(0);
        }
        if (price == null){
            return false;
        }
        return true;
    }
    public  boolean login(String username, String password){
        String first  = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_1_3+" FROM "+TABLE_NAME_1+" WHERE "+COL_1_1+" = '"+username+"' AND "+COL_1_2+" = '"+ password+"'";
        Cursor data = db.rawQuery(query,null);
        while(data.moveToNext()){
            first = data.getString(0);
        }
        if (first == null){
            return false;
        }
        return true;
    }
    public Cursor getPass(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_1_2+" FROM "+TABLE_NAME_1+" WHERE "+COL_1_1+" = '"+username+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public Cursor getPrice(String productname){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_2_2+" FROM "+TABLE_NAME_2+" WHERE "+COL_2_1+" = '"+productname+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public Cursor getQuantity(String productname){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_2_3+" FROM "+TABLE_NAME_2+" WHERE "+COL_2_1+" = '"+productname+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public Cursor getImgPath(String productname){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL_2_4+" FROM "+TABLE_NAME_2+" WHERE "+COL_2_1+" = '"+productname+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }


    public void delete(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_1 + " WHERE " + COL_1_1 + " = '" + username + "'" + " AND " + COL_1_2 + " = '" + password + "'";
        db.execSQL(query);
    }
    public void deleteProduct(String product,String price){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_2 + " WHERE " + COL_2_1 + " = '" + product + "'" + " AND " + COL_2_2 + " = '" + price + "'";
        db.execSQL(query);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE  FROM " + TABLE_NAME_1 ;
        db.execSQL(query);
    }

    public void deleteAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE  FROM " + TABLE_NAME_2 ;
        db.execSQL(query);
    }

}
