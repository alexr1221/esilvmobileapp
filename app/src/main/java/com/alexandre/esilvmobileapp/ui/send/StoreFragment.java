package com.alexandre.esilvmobileapp.ui.send;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandre.esilvmobileapp.ApiClient;
import com.alexandre.esilvmobileapp.ApiInterface;
import com.alexandre.esilvmobileapp.Store;
import com.alexandre.esilvmobileapp.StoreRecyclerAdapter;
import com.alexandre.esilvmobileapp.MainActivity;
import com.alexandre.esilvmobileapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreFragment extends Fragment {

    private StoreViewModel sendViewModel;

    List<Store> storeList;
    RecyclerView recyclerView;
    StoreRecyclerAdapter recyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(StoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stores, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        storeList = new ArrayList<>();
        recyclerView = (RecyclerView) root.findViewById(R.id.storerecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new StoreRecyclerAdapter(MainActivity.getInstance().getApplicationContext(), storeList);
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Store>> call = apiService.getStores("stores?game=" + MainActivity.currentGame);

        call.enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                storeList = response.body();
                Log.d("TAG","Response = "+ storeList);
                recyclerAdapter.setStoreList(storeList);
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        return root;
    }
}