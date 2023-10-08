package com.example.calamityconnect.Activitys.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.calamityconnect.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_Activity extends AppCompatActivity {
    FirebaseUser cuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        cuser = FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (cuser!=null){
                    startActivity(new Intent(Splash_Activity.this, MainActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(Splash_Activity.this, Login_Activity.class));
                    finish();

                }
            }
        }, 2000);



    }
}