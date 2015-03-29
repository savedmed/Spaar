package com.example.sasha.spaar.Models;

/**
 * Created by Sasha on 29.03.2015.
 */
public class Product {
    private long _id;
    private String _name;

    public Product(){

    }

    public Product(String name){
        _name = name;
    }

    public long get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_id(long id) {
        this._id = id;
    }

    public void set_name(String name) {
        this._name = name;
    }

    @Override
    public String toString(){
        return _name;
    }
}
