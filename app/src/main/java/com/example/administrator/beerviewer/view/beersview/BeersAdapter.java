package com.example.administrator.beerviewer.view.beersview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.BeerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tak on 2018. 1. 27..
 */

public class BeersAdapter extends RecyclerView.Adapter<BeersViewHolder> {

    private List<BeerModel> items = new ArrayList<>();

    @Override
    public BeersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item, parent, false);
        return new BeersViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BeersViewHolder holder, int position) {
        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<BeerModel> item) {
        this.items.addAll(0, item);
        this.notifyDataSetChanged();
    }
}
