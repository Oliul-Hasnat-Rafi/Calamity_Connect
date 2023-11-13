package com.example.calamityconnect.Activitys.Activitys;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.ActivityPdfviewBinding;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class pdfview extends AppCompatActivity {
    private PDFView pdfView;
    private ActivityPdfviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pdfView = binding.pdf;

        // Load PDF from assets
        pdfView.fromAsset("calamity_connect.pdf").load();

        // Set up download button click listener
        binding.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = downloadPdf("calamity_connect.pdf");
                if (filePath != null) {
                    // Open the PDF using an external PDF viewer
                    openPdf(filePath);
                }
            }
        });
    }

    private String downloadPdf(String pdfFileName) {
        try {
            // Create a directory for your app to save the downloaded PDF
            File directory = new File(getExternalFilesDir(null) + File.separator + "PDFs");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Get InputStream from assets
            InputStream inputStream = getAssets().open(pdfFileName);

            // Create FileOutputStream to save the PDF
            FileOutputStream fileOutputStream = new FileOutputStream(new File(directory, pdfFileName));

            // Copy the PDF from assets to the external storage
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }

            // Close streams
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();

            // Return the file path
            return directory.getAbsolutePath() + File.separator + pdfFileName;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error downloading PDF", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void openPdf(String filePath) {
        File file = new File(filePath);
        Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No PDF viewer installed", Toast.LENGTH_SHORT).show();
        }
    }
}
