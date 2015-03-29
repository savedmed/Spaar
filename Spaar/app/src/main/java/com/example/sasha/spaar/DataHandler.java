package com.example.sasha.spaar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sasha on 29.03.2015.
 */
public class DataHandler {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database name
    public static final String DATABASE_NAME = "SpaarDB";

    // Database version
    public static final int DATABASE_VERSION = 1;

    // Tables names
    public static final String PRODUCTS_TABLE_NAME = "Products";
    public static final String PRICES_TABLE_NAME = "Prices";
    public static final String SUPERMARKETS_TABLE_NAME = "Supermarkets";
    public static final String LOCATIONS_TABLE_NAME = "Locations";

    // Common columns names
    public static final String COLUMN_ID = "Id";

    // Products - columns names
    public static final String PRODUCTS_COLUMN_NAME = "Name";
    public static final String PRODUCTS_COLUMN_LOGO = "Logo";

    // Prices - columns names
    public static final String PRICES_COLUMN_PRODUCT_ID = "ProductId";
    public static final String PRICES_COLUMN_SUPERMARKET_ID = "SupermarketId";
    public static final String PRICES_COLUMN_PRICE = "Price";

    // Supermarket - columns names
    public static final String SUPERMARKETS_COLUMN_NAME = "Name";
    public static final String SUPERMARKETS_COLUMN_LOGO = "Logo";

    // Locations - columns names
    public static final String LOCATIONS_COLUMN_SUPERMARKET_ID = "SupermatketId";
    public static final String LOCATIONS_COLUMN_LONGITUDE = "Longitude";
    public static final String LOCATIONS_COLUMN_LATITUDE = "Latitude";

    // Create table statements
    public static final String CREATE_TABLE_PRODUCTS = String.format("CREATE TABLE %s (%s DOUBLE, %s VARCHAR(255));",
            PRODUCTS_TABLE_NAME, COLUMN_ID, PRODUCTS_COLUMN_NAME);

    public static final String CREATE_TABLE_SUPERMARKETS = String.format("CREATE TABLE %s (%s DOUBLE, %s VARCHAR(255));",
            SUPERMARKETS_TABLE_NAME, COLUMN_ID, SUPERMARKETS_COLUMN_NAME);

    public static final String CREATE_TABLE_PRICES = String.format("CREATE TABLE %s (%s DOUBLE, %s DOUBLE, %s DOUBLE, %s DOUBLE);",
            PRICES_TABLE_NAME, COLUMN_ID, PRICES_COLUMN_PRODUCT_ID, PRICES_COLUMN_SUPERMARKET_ID, PRICES_COLUMN_PRICE);

    public static final String CREATE_TABLE_LOCATIONS = String.format("CREATE TABLE %s (%s DOUBLE, %s DOUBLE, %s DOUBLE, %s DOUBLE);",
            LOCATIONS_TABLE_NAME, COLUMN_ID, LOCATIONS_COLUMN_SUPERMARKET_ID, LOCATIONS_COLUMN_LONGITUDE, LOCATIONS_COLUMN_LATITUDE);

    DataBaseHelper dbHelper;
    Context context;
    SQLiteDatabase database;

    public DataHandler(Context context){
        this.context = context;
        dbHelper = new DataBaseHelper(context);
    }

    public SQLiteDatabase OpenDatabase(){
        database = dbHelper.getWritableDatabase();
        return database;
    }
    public void CloseDatabase(){
        dbHelper.close();
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(CREATE_TABLE_PRODUCTS);
            database.execSQL(CREATE_TABLE_LOCATIONS);
            database.execSQL(CREATE_TABLE_PRICES);
            database.execSQL(CREATE_TABLE_SUPERMARKETS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PRODUCTS);
            database.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PRICES);
            database.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SUPERMARKETS);
            database.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_LOCATIONS);

            onCreate(database);
        }
    }
}
