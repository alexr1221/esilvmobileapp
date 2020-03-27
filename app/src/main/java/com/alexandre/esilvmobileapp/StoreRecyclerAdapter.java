package com.alexandre.esilvmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class StoreRecyclerAdapter extends RecyclerView.Adapter<StoreRecyclerAdapter.MyviewHolder> {

    public static StoreRecyclerAdapter instance;

    Context context;
    List<Store> storeList;

    public StoreRecyclerAdapter(Context context, List<Store> storeList) {
        instance = this;
        this.context = context;
        this.storeList = storeList;
    }

    public void setStoreList(List<Store> reviewList) {
        this.storeList = reviewList;
        notifyDataSetChanged();
    }

    @Override
    public StoreRecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.store_recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreRecyclerAdapter.MyviewHolder holder, int position) {
        holder.title.setText(storeList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(storeList != null){
            return storeList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.sTitle);
        }
    }
}