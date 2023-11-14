package com.example.calamityconnect.Activitys.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.calamityconnect.Activitys.Fragment.ContactUs_Fragment;
import com.example.calamityconnect.Activitys.Fragment.homeFragment;
import com.example.calamityconnect.Activitys.Fragment.postFragment;
import com.example.calamityconnect.Activitys.Fragment.profileFragment;
import com.example.calamityconnect.Activitys.Fragment.volunteer_form_Fragment;
import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentTransaction transaction;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeFragments();
        setupBottomNavigationView();
    }

    private void initializeFragments() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new homeFragment());
        transaction.commit();
    }

    private void setupBottomNavigationView() {
        binding.bottomMenu.setOnNavigationItemSelectedListener(item -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.homemenu:
                    transaction.replace(R.id.content, new homeFragment());
                    break;
                case R.id.postmenu:
                    transaction.replace(R.id.content, new postFragment());
                    break;
                case R.id.profilemenu:
                    transaction.replace(R.id.content, new profileFragment());
                    break;
            }
            transaction.commit();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.volunteer:
                replaceFragment(new volunteer_form_Fragment());
                return true;
            case R.id.ContactUs:
                replaceFragment(new ContactUs_Fragment());
                return true;
            case R.id.donationlist:
                startActivity(Donation_list_Activity.class);
                return true;
            case R.id.resource:
                startActivity(pdfview.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            super.onBackPressed();
        } else {
            backPressedTime = System.currentTimeMillis();
            // Show a toast or a dialog to inform the user about the double-click action
             Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
    }
}
