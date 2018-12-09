package com.example.yakirlaptop.ssproject.DatabaseAPI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.yakirlaptop.ssproject.ObjectClasses.Admin;
import com.example.yakirlaptop.ssproject.ObjectClasses.Customer;
import com.example.yakirlaptop.ssproject.ObjectClasses.User;
import com.example.yakirlaptop.ssproject.ObjectClasses.Supplier;


public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME_1 = "users";
    private static final String[] TABLE_1_COLUMNS = {new String("username"),new String("password"),new String("admin"),new String("name"),new String("email"),new String("creditcard")};
    private static final String TABLE_NAME_2 = "products";
    private static final String[] TABLE_2_COLUMNS = {new String("p_id"),new String("s_id"),new String("name"),new String("price"),new String("quantity"),new String("image")};
    private static final String TABLE_NAME_3 = "suppliers";
    private static final String[] TABLE_3_COLUMNS = {new String("s_id"),new String("name"),new String("phone"),new String("email"),new String("company")};
    private static final String adminUsername = "admin";
    private static final String adminPassword = "admin";
    private static final String adminName = "admin";

    public DatabaseOpenHelper(Context context) {
        super(context, "db",null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable1 = "CREATE TABLE "+TABLE_NAME_1+" ("+TABLE_1_COLUMNS[0]+" TEXT, "+TABLE_1_COLUMNS[1]+" TEXT,"+TABLE_1_COLUMNS[2]+" INTEGER,"+TABLE_1_COLUMNS[3]+" TEXT,"+TABLE_1_COLUMNS[4] +" TEXT,"+TABLE_1_COLUMNS[5]+" TEXT)";
        String createTable2 = "CREATE TABLE "+TABLE_NAME_2+" ("+TABLE_2_COLUMNS[0]+" INTEGER PRIMARY KEY, "+TABLE_2_COLUMNS[1]+" INTEGER,"+TABLE_2_COLUMNS[2]+" TEXT,"+TABLE_2_COLUMNS[3]+" INTEGER,"+TABLE_2_COLUMNS[4]+" INTEGER,"+TABLE_2_COLUMNS[5]+" TEXT)";
        String createTable3 = "CREATE TABLE "+TABLE_NAME_3+" ("+TABLE_3_COLUMNS[0]+" INTEGER PRIMARY KEY, "+TABLE_3_COLUMNS[1]+" TEXT,"+TABLE_3_COLUMNS[2]+" TEXT,"+TABLE_3_COLUMNS[3]+" TEXT," + TABLE_3_COLUMNS[4] + " TEXT)";
        sqLiteDatabase.execSQL(createTable1);
        sqLiteDatabase.execSQL(createTable2);
        sqLiteDatabase.execSQL(createTable3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_3);
        onCreate(sqLiteDatabase);
    }

    public User getUserByUsername(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_1+" WHERE "+TABLE_1_COLUMNS[0]+" = '"+username+"'";
        User user = null;
        Cursor res = db.rawQuery(query, null);
        if (res.getCount() == 1)
        {
            res.moveToFirst();
            if(res.getInt(2)==1)
                user = new Admin(res.getString(0),
                                 res.getString(1),
                                 res.getInt(2),
                                 res.getString(3));
            else
                user = new Customer(res.getString(0),
                                    res.getString(1),
                                    res.getInt(2),
                                    res.getString(3),
                                    res.getString(4),
                                    res.getString(5));
        }
        res.close();
        return user;
    }

    public boolean addUser(String username, String password ,int admin, String name , String email , String creditcard){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TABLE_1_COLUMNS[0],username);
        cv.put(TABLE_1_COLUMNS[1],password);
        cv.put(TABLE_1_COLUMNS[2],admin);
        cv.put(TABLE_1_COLUMNS[3],name);
        cv.put(TABLE_1_COLUMNS[4],email);
        cv.put(TABLE_1_COLUMNS[5],creditcard);

        long result = db.insert(TABLE_NAME_1,null,cv);
        if (result == -1){
            return false;
        }
        return true;
    }

    public void deleteUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_1 + " WHERE " + TABLE_1_COLUMNS[0] + " = '" + username + "'";
        db.execSQL(query);
    }

    public void deleteAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE  FROM " + TABLE_NAME_1 ;
        db.execSQL(query);
    }

    public void deleteSupplier(int s_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_3 + " WHERE " + TABLE_3_COLUMNS[0] + " = '" + s_id + "'";
        db.execSQL(query);
    }

    public void deleteAllSuppliers(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE  FROM " + TABLE_NAME_3 ;
        db.execSQL(query);
    }

    public Supplier getSupplierByName(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Supplier supplier = null;
        String query = "SELECT * FROM "+TABLE_NAME_3+" WHERE "+TABLE_3_COLUMNS[1]+" = '"+name+"'";
        Cursor res = db.rawQuery(query, null);
        if (res.getCount() == 1)
        {
            res.moveToFirst();
            supplier = new Supplier(res.getInt(0),
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3),
                                    res.getString(4));
        }
        res.close();
        return supplier;
    }

    public boolean addSupplier(String name,String phone,String email,String company)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TABLE_3_COLUMNS[1],name);
        cv.put(TABLE_3_COLUMNS[2],phone);
        cv.put(TABLE_3_COLUMNS[3],email);
        cv.put(TABLE_3_COLUMNS[4],company);

        long result = db.insert(TABLE_NAME_3,null,cv);
        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean editProduct(int p_id,String name,int price,int quantity, String image){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TABLE_2_COLUMNS[3],price);
        cv.put(TABLE_2_COLUMNS[4],quantity);
        cv.put(TABLE_2_COLUMNS[5],image);

        long result = db.update(TABLE_NAME_2,cv,TABLE_2_COLUMNS[0]+"='"+p_id+"'",null);
        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean addProduct(int s_id, String name, String price ,String quantity , String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TABLE_2_COLUMNS[1],s_id);
        cv.put(TABLE_2_COLUMNS[2],name);
        cv.put(TABLE_2_COLUMNS[3],price);
        cv.put(TABLE_2_COLUMNS[4],quantity);
        cv.put(TABLE_2_COLUMNS[5],image);
        long result = db.insert(TABLE_NAME_2,null,cv);
        if (result == -1){
            return false;
        }
        return true;
    }
    public Cursor getUsersTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_1;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getProductsTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_2;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getSuppliersTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME_3;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public boolean userAlreadyExists(String username){
        String pass = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+TABLE_1_COLUMNS[1]+" FROM "+TABLE_NAME_1+" WHERE "+TABLE_1_COLUMNS[0]+" = '"+username+"'";
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
        String query = "SELECT "+TABLE_2_COLUMNS[3]+" FROM "+TABLE_NAME_2+" WHERE "+TABLE_1_COLUMNS[2]+" = '"+product+"'";
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
        String name  = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+TABLE_1_COLUMNS[3]+" FROM "+TABLE_NAME_1+" WHERE "+TABLE_1_COLUMNS[0]+" = '"+username+"' AND "+TABLE_1_COLUMNS[1]+" = '"+ password+"'";
        Cursor data = db.rawQuery(query,null);
        while(data.moveToNext()){
            name = data.getString(0);
        }
        if (name == null){
            return false;
        }
        return true;
    }

    public Cursor getPass(String username){
        return null;
    }
    public Cursor getPrice(String productname){
        return null;
    }
    public Cursor getQuantity(String productname){
        return null;
    }
    public Cursor getImgPath(String productname){
        return null;
    }
    public void delete(String username, String password){
    }
    public void deleteProduct(String product,String price){
    }
    public void deleteAll(){
    }
    public void deleteAllProducts(){
    }

//    public Cursor getPass(String username){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT "+COL_1_2+" FROM "+TABLE_NAME_1+" WHERE "+COL_1_1+" = '"+username+"'";
//        Cursor data = db.rawQuery(query,null);
//        return data;
//    }
//    public Cursor getPrice(String productname){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT "+COL_2_2+" FROM "+TABLE_NAME_2+" WHERE "+COL_2_1+" = '"+productname+"'";
//        Cursor data = db.rawQuery(query,null);
//        return data;
//    }
//    public Cursor getQuantity(String productname){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT "+COL_2_3+" FROM "+TABLE_NAME_2+" WHERE "+COL_2_1+" = '"+productname+"'";
//        Cursor data = db.rawQuery(query,null);
//        return data;
//    }
//    public Cursor getImgPath(String productname){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT "+COL_2_4+" FROM "+TABLE_NAME_2+" WHERE "+COL_2_1+" = '"+productname+"'";
//        Cursor data = db.rawQuery(query,null);
//        return data;
//    }
//
//

//    public void deleteProduct(String product,String price){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DELETE FROM " + TABLE_NAME_2 + " WHERE " + COL_2_1 + " = '" + product + "'" + " AND " + COL_2_2 + " = '" + price + "'";
//        db.execSQL(query);
//    }
//

//
//    public void deleteAllProducts(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DELETE  FROM " + TABLE_NAME_2 ;
//        db.execSQL(query);
//    }

}
