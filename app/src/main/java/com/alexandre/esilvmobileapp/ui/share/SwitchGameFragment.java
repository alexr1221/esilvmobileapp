package com.alexandre.esilvmobileapp.ui.share;

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
import com.alexandre.esilvmobileapp.Game;
import com.alexandre.esilvmobileapp.GameRecyclerAdapter;
import com.alexandre.esilvmobileapp.MainActivity;
import com.alexandre.esilvmobileapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwitchGameFragment extends Fragment {

    private SwitchGameViewModel switchGameViewModel;

    List<Game> gameList;
    RecyclerView recyclerView;
    GameRecyclerAdapter recyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        switchGameViewModel =
                ViewModelProviders.of(this).get(SwitchGameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_switchgame, container, false);
        final TextView textView = root.findViewById(R.id.text_games);
        switchGameViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        gameList = new ArrayList<>();
        recyclerView = (RecyclerView) root.findViewById(R.id.gamerecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new GameRecyclerAdapter(MainActivity.getInstance().getApplicationContext(), gameList);
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Game>> call = apiService.getGames();

        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                gameList = response.body();
                Log.d("TAG","Response = "+ gameList);
                recyclerAdapter.setGameList(gameList);
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        return root;
    }
}