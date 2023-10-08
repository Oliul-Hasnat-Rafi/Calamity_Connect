package com.example.calamityconnect.Activitys.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.calamityconnect.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

public class detail_Activity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String image= getIntent().getStringExtra("image");
        String title= getIntent().getStringExtra("title");
        String des= getIntent().getStringExtra("des");

        getSupportActionBar().setTitle(title);

        Picasso.get()
                .load(image)
                .into(binding.detailImage);

        binding.description.setText(des);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
