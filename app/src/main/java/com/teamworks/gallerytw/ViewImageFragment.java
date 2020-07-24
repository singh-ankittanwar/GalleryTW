package com.teamworks.gallerytw;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewImageFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_image, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.mainRV);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiPlaceHolder apiPlaceHolder = retrofit.create(apiPlaceHolder.class);

        Call<List<apiItem>> call = apiPlaceHolder.getApiItems();
        call.enqueue(new Callback<List<apiItem>>() {
            @Override
            public void onResponse(Call<List<apiItem>> call, Response<List<apiItem>> response) {
                if (!response.isSuccessful()){
                    Log.d("HERE --->", response.toString());
                    return;
                }
                List<apiItem> apiItemsx = response.body();

                recyclerView.setAdapter(new xAdapter(getContext(), apiItemsx));
            }

            @Override
            public void onFailure(Call<List<apiItem>> call, Throwable t) {
                Log.d("HERE2 --->", t.toString());
            }
        });

        return v;
    }
}