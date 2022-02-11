package com.example.lab_1_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "lab1_2_DB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "products";

    private static final String PRODUCT_ID_COL = "id";

    private static final String PRODUCT_NAME_COL = "name";

    private static final String DESCRIPTION_COL = "description";

    private static final String PRICE_COL = "price";

    private static final String LOCATION_COL = "location";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + PRODUCT_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_NAME_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + LOCATION_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewProduct(String productName, String productPrice, String productDescription, String productLocation) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PRODUCT_NAME_COL, productName);
        values.put(PRICE_COL, productPrice);
        values.put(DESCRIPTION_COL, productDescription);
        values.put(LOCATION_COL, productLocation);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }

}
