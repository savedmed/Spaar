package com.example.sasha.spaar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sasha.spaar.DataHandler;
import com.example.sasha.spaar.Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sasha on 29.03.2015.
 */
public class ProductsDAO {

    //Database fields
    private SQLiteDatabase database;
    private DataHandler dataHandler;
    private String[] columns = { DataHandler.COLUMN_ID, DataHandler.PRODUCTS_COLUMN_NAME};

    public ProductsDAO(Context context){
        dataHandler = new DataHandler(context);
    }

    public void OpenDatabase() {
        database = dataHandler.OpenDatabase();
    }

    public void CloseDatabase() {
        dataHandler.CloseDatabase();
    }

    public void createProduct(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataHandler.PRODUCTS_COLUMN_NAME, product.get_name());
        long insetId = database.insert(DataHandler.PRODUCTS_TABLE_NAME, null, contentValues);
    }

    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<Product>();

        Cursor cursor = database.query(DataHandler.PRODUCTS_TABLE_NAME, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Product product = cursorToProduct(cursor);
            productList.add(product);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return productList;
    }


    private Product cursorToProduct(Cursor cursor){
        Product product = new Product();
        product.set_id(cursor.getLong(0));
        product.set_name(cursor.getString(1));
        return product;
    }

}
