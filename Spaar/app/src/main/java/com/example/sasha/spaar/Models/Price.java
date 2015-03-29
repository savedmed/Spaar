package com.example.sasha.spaar.Models;

/**
 * Created by Sasha on 29.03.2015.
 */
public class Price {
    private long _id;
    private long _supermarketId;
    private long _price;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long get_supermarketId() {
        return _supermarketId;
    }

    public void set_supermarketId(long _supermarketId) {
        this._supermarketId = _supermarketId;
    }

    public long get_price() {
        return _price;
    }

    public void set_price(long _price) {
        this._price = _price;
    }
}
