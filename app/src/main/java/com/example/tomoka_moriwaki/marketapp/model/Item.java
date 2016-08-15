package com.example.tomoka_moriwaki.marketapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tomoka-moriwaki on 2016/08/15.
 */
public class Item {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private int price;

    @SerializedName("description")
    private String description;

    @SerializedName("image_url")
    private String imageUrl;


    public Item(int id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() { return price; }

    public String getDescription() { return description; }
}
