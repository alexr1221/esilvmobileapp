package com.alexandre.esilvmobileapp.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandre.esilvmobileapp.ApiClient;
import com.alexandre.esilvmobileapp.ApiInterface;
import com.alexandre.esilvmobileapp.MainActivity;
import com.alexandre.esilvmobileapp.R;
import com.alexandre.esilvmobileapp.Review;
import com.alexandre.esilvmobileapp.ReviewRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewsFragment extends Fragment {

    private ReviewsViewModel galleryViewModel;

    List<Review> reviewList;
    RecyclerView recyclerView;
    ReviewRecyclerAdapter recyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ReviewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reviews, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        reviewList = new ArrayList<>();
        recyclerView = (RecyclerView) root.findViewById(R.id.reviewrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new ReviewRecyclerAdapter(MainActivity.getInstance().getApplicationContext(), reviewList);
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Review>> call = apiService.getReviews("users/" + MainActivity.currentGame +"/reviews?steam=true&googlePlay=true");

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                reviewList = response.body();
                Log.d("TAG","Response = "+ reviewList);
                recyclerAdapter.setReviewList(reviewList);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        return root;
    }
}