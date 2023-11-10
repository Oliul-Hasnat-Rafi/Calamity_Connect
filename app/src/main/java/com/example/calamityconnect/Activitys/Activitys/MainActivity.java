package com.example.calamityconnect.Activitys.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.calamityconnect.Activitys.Fragment.ContactUs_Fragment;
import com.example.calamityconnect.Activitys.Fragment.homeFragment;
import com.example.calamityconnect.Activitys.Fragment.postFragment;
import com.example.calamityconnect.Activitys.Fragment.profileFragment;
import com.example.calamityconnect.Activitys.Fragment.volunteer_form_Fragment;
import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new homeFragment());
        transaction.commit();

//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawer, R.string.nav_open, R.string.nav_close);
//        // pass the Open and Close toggle for the drawer layout listener
//        // to toggle the button
//        binding.drawer.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });


//        binding.bottomMenu.addBubbleListener(new OnBubbleClickListener() {
//            @Override
//            public void onBubbleClick(int i) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                switch (i) {
//                    case 0:
//                        transaction.replace(R.id.content, new homeFragment());
//                        break;
//                    case 1:
//                        transaction.replace(R.id.content, new postFragment());
//                        break;
//                    case 2:
//                        transaction.replace(R.id.content, new profileFragment());
//                        break;
//
//                }
//                transaction.commit();
//
//            }
//        });
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.volunteer:
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new volunteer_form_Fragment()).commit();
                return true;

            case R.id.ContactUs:
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new ContactUs_Fragment()).commit();
                return true;


            case R.id.donationlist:
                Intent intent1 = new Intent(this, Donation_list_Activity.class);
                startActivity(intent1);
                return true;
            case R.id.resource:
                Intent intent2 = new Intent(this, pdfview.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}