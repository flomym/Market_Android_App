package com.example.tomoka_moriwaki.marketapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomoka_moriwaki.marketapp.adapter.RecommendAdapter;
import com.example.tomoka_moriwaki.marketapp.api.MarketServiceHolder;
import com.example.tomoka_moriwaki.marketapp.databinding.ActivityItemDetailBinding;
import com.example.tomoka_moriwaki.marketapp.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ItemDetailActivity extends MainActivity {

    private ActivityItemDetailBinding binding;

    public static String EXTRA_ITEM_ID = "extra_item_id";

    private List<Item> items = new ArrayList<>();

    public static Intent createIntent(Context context, int itemId) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_item_detail);
        RecommendAdapter adapter = new RecommendAdapter();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail);

        Intent intent = getIntent();

        MarketServiceHolder.get()
                .item(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Item>() {
                    @Override
                    public void call(Item item) {
                        binding.itemName.setText(item.getName());
                        binding.itemValue.setText(String.valueOf(item.getPrice()));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        // RecommendAdapterに更新イベントを送る
        adapter.notifyDataSetChanged();
    }
}

