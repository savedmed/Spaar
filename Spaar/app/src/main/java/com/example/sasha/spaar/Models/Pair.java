package com.example.sasha.spaar.Models;

/**
 * Created by Sasha on 02.04.2015.
 */
public class Pair {
    private String shop = "";
    private double price = 0.0;

    public Pair(String shop, double price)
    {
        this.shop = shop;
        this.price = price;
    }

    public Pair()
    {

    }

    public String getShopName() {
        return this.shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
