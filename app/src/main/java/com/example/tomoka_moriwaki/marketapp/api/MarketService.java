package com.example.tomoka_moriwaki.marketapp.api;

import com.example.tomoka_moriwaki.marketapp.model.Item;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MarketService {

    @GET("items/recommended.json")
    Observable<List<Item>> recommendItems();

    @GET("items/{id}.json")
    Observable<Item> item(@Path("id") int id);

}