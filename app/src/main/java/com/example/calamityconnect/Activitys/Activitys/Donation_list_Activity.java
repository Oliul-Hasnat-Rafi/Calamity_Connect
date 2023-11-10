package com.example.calamityconnect.Activitys.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.calamityconnect.Activitys.Adapter.CustomAdapter;
import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.ActivityDonationBinding;
import com.example.calamityconnect.databinding.ActivityDonationListBinding;

public class Donation_list_Activity extends AppCompatActivity {
    ActivityDonationListBinding binding;
    String nametv;
    String amounttv;
    String namelist[];
    String amountlist[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonationListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nametv = getIntent().getStringExtra("name");
        amounttv = getIntent().getStringExtra("amount");


        Log.i("TAG", "onCreate: "+amounttv);
        // Initialize namelist with the data from nametv
        if (nametv != null) {
            namelist = new String[]{nametv};
            amountlist = new String[]{amounttv};
        }

        CustomAdapter customAdapter = new CustomAdapter(this, namelist, amountlist);
        binding.simpleListView.setAdapter(customAdapter);

    }
}