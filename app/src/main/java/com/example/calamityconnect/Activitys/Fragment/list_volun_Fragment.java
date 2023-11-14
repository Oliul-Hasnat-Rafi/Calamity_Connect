package com.example.calamityconnect.Activitys.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.calamityconnect.Activitys.Adapter.adapter;
import com.example.calamityconnect.Activitys.Adapter.volun_adapter;
import com.example.calamityconnect.Activitys.Retrofit.MyRetrofit;
import com.example.calamityconnect.Activitys.Retrofit.postapi;
import com.example.calamityconnect.Activitys.Retrofit.volunapi;
import com.example.calamityconnect.Activitys.model.model;
import com.example.calamityconnect.Activitys.model.volunteenmodel;
import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.FragmentListVolunBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class list_volun_Fragment extends Fragment {
    ArrayList<volunteenmodel> volunteenList;
    FragmentListVolunBinding binding;
   private volun_adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentListVolunBinding.inflate(getLayoutInflater(),container,false);

        volunteenList=new ArrayList<>();

        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        binding.volunlist.setLayoutManager(layoutManager);
        volunapi volunapi= MyRetrofit.getClient().create(volunapi.class);
        Call<List<volunteenmodel>> listvolun = volunapi.getallvolunlist();
        listvolun.enqueue(new Callback<List<volunteenmodel>>() {
            @Override
            public void onResponse(Call<List<volunteenmodel>> call, Response<List<volunteenmodel>> response) {
                volunteenList = (ArrayList<volunteenmodel>) response.body();
                if (volunteenList.size()>0){
                    for (volunteenmodel volunlist : volunteenList){

                    }
                }
               adapter = new volun_adapter(getContext(), volunteenList);
                binding.volunlist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<volunteenmodel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
    });

        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                adapter.notifyDataSetChanged();


                binding.refreshLayout.setRefreshing(false);
            }
        });




        return binding.getRoot();

    }
}