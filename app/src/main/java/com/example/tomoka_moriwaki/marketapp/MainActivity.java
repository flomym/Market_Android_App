package com.example.tomoka_moriwaki.marketapp;

import android.content.ClipData;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.tomoka_moriwaki.marketapp.adapter.RecommendAdapter;
import com.example.tomoka_moriwaki.marketapp.api.MarketServiceHolder;
import com.example.tomoka_moriwaki.marketapp.databinding.ActivityMainBinding;
import com.example.tomoka_moriwaki.marketapp.model.Item;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RecommendAdapter adapter = new RecommendAdapter();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter.setClickListener(new RecommendAdapter.ClickListener() {
            @Override
            public void onClickItem(Item item, View view) {
                Toast.makeText(MainActivity.this, "tapped", Toast.LENGTH_SHORT).show();

                // ItemDetailActivityを生成
                Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
                startActivity(intent);

            }
        });
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MarketServiceHolder.get()
                .recommendItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1 <List<Item>>() {
                    @Override
                    public void call(List<Item> items) {
                        for(Item i:items) {
                            adapter.add(i);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

//        // ダミーデータの追加
//        adapter.add(new Item(0, "Orange", 1000));
//        adapter.add(new Item(1, "Apple", 1000));
//        adapter.add(new Item(2, "Banana", 1000));
        // RecommendAdapterに更新イベントを送る
        adapter.notifyDataSetChanged();
    }
}