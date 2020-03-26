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

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.MyviewHolder> {

    Context context;
    List<Review> reviewList;

    public ReviewRecyclerAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @Override
    public ReviewRecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewRecyclerAdapter.MyviewHolder holder, int position) {
        holder.title.setText(reviewList.get(position).getTitle());
        holder.description.setText(reviewList.get(position).getDescription());
        //Glide.with(context).load(reviewList.get(position).getDescription()).apply(RequestOptions.centerCropTransform()).into(holder.description);
    }

    @Override
    public int getItemCount() {
        if(reviewList != null){
            return reviewList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.rvTitle);
            description = (TextView) itemView.findViewById(R.id.rvDescription);
        }
    }
}