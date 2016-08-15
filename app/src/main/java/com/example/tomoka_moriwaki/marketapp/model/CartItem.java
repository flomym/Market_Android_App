package com.example.tomoka_moriwaki.marketapp.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by tomoka-moriwaki on 2016/08/15.
 */
@Table
public class CartItem {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column(indexed = true)
    public int itemId;

    @Column
    public String name;

    @Column
    public int price;

    @Column(value = "0")
    public int count;

    public CartItem() {
    }
}
