package com.example.tomoka_moriwaki.marketapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomoka_moriwaki.marketapp.adapter.RecommendAdapter;
import com.example.tomoka_moriwaki.marketapp.api.MarketServiceHolder;
import com.example.tomoka_moriwaki.marketapp.databinding.ActivityItemDetailBinding;
import com.example.tomoka_moriwaki.marketapp.model.Item;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static Intent createIntent(Context context, int itemId) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int item_id = -1;

        super.onCreate(savedInstanceState);
        RecommendAdapter adapter = new RecommendAdapter();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail);

        Intent intent = getIntent();
        if (intent != null) {
            item_id = intent.getIntExtra("id", -1);
        }

        MarketServiceHolder.get()
                .item(item_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Item>() {
                    @Override
                    public void call(Item item) {
                        binding.itemName.setText(item.getName());
                        binding.itemValue.setText(String.valueOf(item.getPrice() + "円"));
                        binding.itemDescription.setText(item.getDescription());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        // RecommendAdapterに更新イベントを送る
        adapter.notifyDataSetChanged();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ItemDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.tomoka_moriwaki.marketapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ItemDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.tomoka_moriwaki.marketapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

