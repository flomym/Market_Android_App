package com.example.tomoka_moriwaki.marketapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tomoka_moriwaki.marketapp.R;
import com.example.tomoka_moriwaki.marketapp.databinding.CellRecommendBinding;
import com.example.tomoka_moriwaki.marketapp.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomoka-moriwaki on 2016/08/15.
 */
public class RecommendAdapter extends RecyclerView.Adapter< RecommendAdapter.ViewHolder> {

    public interface ClickListener {
        void onClickItem(Item item, View view);
    }

    private  ClickListener listener;

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    private List<Item> items = new ArrayList<>();



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell_recommend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);
        final Context context = holder.binding.getRoot().getContext();


        holder.binding.itemName.setText(item.getName());
        holder.binding.itemValue.setText(item.getPrice() + "円");
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClickItem(item, view);
                }
            }
        });
    }

    public void add(Item item){
        items.add(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CellRecommendBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }

}