package com.example.calamityconnect.Activitys.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    FirebaseAuth mauth;
//    FirebaseUser cuser;

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mauth = FirebaseAuth.getInstance();
//        cuser = FirebaseAuth.getInstance().getCurrentUser();



        binding.signUpNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, Register_Activity.class));
            }
        });

        binding.signInBtn.setOnClickListener(view -> {
            String email = binding.userEmail.getText().toString().trim();
            String password = binding.userPassword.getText().toString().trim();
            if (email.equals("")){
                binding.userEmail.setError("Required");
            }else if (password.equals("")){
                binding.userPassword.setError("Required");
            }else {
                UserSignIn(email, password);
            }
        });


    }

    private void UserSignIn(String email, String password) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.signInBtn.setVisibility(View.GONE);

        mauth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login_Activity.this, "Loging......", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login_Activity.this, MainActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressBar.setVisibility(View.GONE);
                binding.signInBtn.setVisibility(View.VISIBLE);
                ShowAlert(e.getLocalizedMessage());
            }
        });
    }

    private void ShowAlert(String localizedMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();
        builder.setTitle("Caution!");
        builder.setMessage(localizedMessage);
        builder.setIcon(android.R.drawable.stat_notify_error);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
    }