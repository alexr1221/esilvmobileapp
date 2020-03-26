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

public class GameRecyclerAdapter extends RecyclerView.Adapter<GameRecyclerAdapter.MyviewHolder> {

    Context context;
    List<Game> gameList;

    public GameRecyclerAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    public void setGameList(List<Game> reviewList) {
        this.gameList = reviewList;
        notifyDataSetChanged();
    }

    @Override
    public GameRecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.game_recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameRecyclerAdapter.MyviewHolder holder, int position) {
        holder.title.setText(gameList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(gameList != null){
            return gameList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.gTitle);
        }
    }
}