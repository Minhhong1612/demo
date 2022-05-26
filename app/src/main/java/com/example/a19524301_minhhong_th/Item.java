package com.example.a19524301_minhhong_th;

import com.google.firebase.firestore.DocumentSnapshot;

import java.io.Serializable;

public class Item implements Serializable {
    private String ID;
    private String title;
    private int count;
    private double price;
    private String img;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public DocumentSnapshot getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Item(String ID, String title, int count, double price, String imgBook) {
        this.ID = ID;
        this.title = title;
        this.count = count;
        this.price = price;
        this.img = imgBook;
    }

    public Item() {
    }

}

