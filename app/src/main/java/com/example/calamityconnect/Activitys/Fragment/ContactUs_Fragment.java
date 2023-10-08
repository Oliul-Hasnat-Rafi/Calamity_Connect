package com.example.calamityconnect.Activitys.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.FragmentContactUsBinding;

public class ContactUs_Fragment extends Fragment {
    FragmentContactUsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding =FragmentContactUsBinding.inflate(getLayoutInflater(),container,false);
        String emailid = "dbbhattacharjee484@gmail.com";
        binding.send.setOnClickListener(v -> {
            String sub = binding.subject.getText().toString();
            String body = binding.body.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            // add three fields to intent using putExtra function
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailid});
            intent.putExtra(Intent.EXTRA_SUBJECT, sub);
            intent.putExtra(Intent.EXTRA_TEXT, body);

            // set type of intent
            intent.setType("message/rfc822");

            // startActivity with intent with chooser as Email client using createChooser function
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));

        });



        return binding.getRoot();
    }
}