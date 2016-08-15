package com.example.tomoka_moriwaki.marketapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ItemDetailActivity extends MainActivity {
    public static String EXTRA_ITEM_ID = "extra_item_id";

    public static Intent createIntent(Context context, int itemId) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
//        if (intent != null) {
//            String xxx = intent.getStringExtra("key1");
//        }
    }
}

