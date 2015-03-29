package com.example.sasha.spaar.Models;

/**
 * Created by Sasha on 29.03.2015.
 */
public class Location {
    private long _id;
    private long _supermarketId;
    private long _longitude;
    private long _latitude;

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

    public long get_longitude() {
        return _longitude;
    }

    public void set_longitude(long _longitude) {
        this._longitude = _longitude;
    }

    public long get_latitude() {
        return _latitude;
    }

    public void set_latitude(long _latitude) {
        this._latitude = _latitude;
    }
}
