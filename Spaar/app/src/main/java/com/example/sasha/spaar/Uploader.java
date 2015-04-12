package com.example.sasha.spaar;

import android.content.Context;

import com.example.sasha.spaar.DAO.ProductsDAO;
import com.example.sasha.spaar.Models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sasha on 12.04.2015.
 */
public class Uploader {

    public void uploadAll(Context context) throws IOException {
        Parser dataParser = new Parser();
        ArrayList<Product> uploadedProducts = dataParser.GetProductsInRange(1,5,1);

        ProductsDAO productDAO = new ProductsDAO(context.getApplicationContext());
        productDAO.OpenDatabase();

        for (Iterator<Product> newProduct = uploadedProducts.iterator(); newProduct.hasNext(); ){
            productDAO.createProduct(newProduct.next());
        }

        productDAO.CloseDatabase();
    }

}
