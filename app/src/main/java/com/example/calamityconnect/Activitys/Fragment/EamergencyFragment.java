package com.example.calamityconnect.Activitys.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calamityconnect.Activitys.Adapter.Ema_Adapter;
import com.example.calamityconnect.Activitys.model.ema_model;
import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.FragmentEamergencyBinding;

import java.util.ArrayList;

public class EamergencyFragment extends Fragment {
FragmentEamergencyBinding binding;
ArrayList<ema_model> ema_modelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEamergencyBinding.inflate(getLayoutInflater(),container,false);
        ema_modelList =new ArrayList<>();

        ema_modelList.add(new ema_model("National Emergency Hotline Number","999"));
        ema_modelList.add(new ema_model("Various government services","333"));
        ema_modelList.add(new ema_model("women and children are abused","109"));
        ema_modelList.add(new ema_model("Bangladesh Police helpdesk","100"));
        ema_modelList.add(new ema_model("RAB helpdesk","101"));
        ema_modelList.add(new ema_model("Fire Service Hotline","102"));
        ema_modelList.add(new ema_model("National helpline center for violence against women","10921"));
        ema_modelList.add(new ema_model("Kaan Pete Roi","01779554391"));
        ema_modelList.add(new ema_model("Mental Health & Psychosocial helpline","01688709965"));
        ema_modelList.add(new ema_model("Moner Bondhu","01776632344"));
        ema_modelList.add(new ema_model("Sajida Foundation","0177 7771515"));
        ema_modelList.add(new ema_model("National Identity Card","105"));
        ema_modelList.add(new ema_model("Government legal assistance","16430"));

        Ema_Adapter adapter =new Ema_Adapter(getContext(),ema_modelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.RecyclerView.setLayoutManager(linearLayoutManager);
        binding.RecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}