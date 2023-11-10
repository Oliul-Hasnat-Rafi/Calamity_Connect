package com.example.calamityconnect.Activitys.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.ActivityMainBinding;
import com.example.calamityconnect.databinding.ActivityPdfviewBinding;
import com.github.barteksc.pdfviewer.PDFView;

public class pdfview extends AppCompatActivity {
    private PDFView pdfView;
    ActivityPdfviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pdf.fromAsset("calamity_connect.pdf")// Show only the first page
                .load();
    }
}