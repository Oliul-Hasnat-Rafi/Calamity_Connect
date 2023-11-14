package com.example.calamityconnect.Activitys.Activitys;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.calamityconnect.Activitys.Adapter.CustomAdapter;
import com.example.calamityconnect.Activitys.Retrofit.ApiInterface;
import com.example.calamityconnect.Activitys.Retrofit.MyRetrofit;
import com.example.calamityconnect.Activitys.model.Donation;
import com.example.calamityconnect.databinding.ActivityDonationListBinding;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Donation_list_Activity extends AppCompatActivity {
    private ActivityDonationListBinding binding;
    private List<Donation> donationList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonationListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Call your API using Retrofit
        ApiInterface apiInterface = MyRetrofit.getClient().create(ApiInterface.class);
        Call<List<Donation>> call = apiInterface.getDonations();

        call.enqueue(new Callback<List<Donation>>() {
            @Override
            public void onResponse(Call<List<Donation>> call, Response<List<Donation>> response) {
                if (response.isSuccessful()) {
                    donationList = response.body();

                    // Sort the donationList by amount in descending order
                    Collections.sort(donationList, new Comparator<Donation>() {
                        @Override
                        public int compare(Donation donation1, Donation donation2) {
                            // Parse amounts as integers for comparison
                            int amount1 = Integer.parseInt(donation1.getDamount());
                            int amount2 = Integer.parseInt(donation2.getDamount());

                            // Sort in descending order
                            return Integer.compare(amount2, amount1);
                        }
                    });

                    // Extract names and amounts from the sorted list
                    String[] namelist = new String[donationList.size()];
                    String[] amountlist = new String[donationList.size()];
                    String[] membershipLevel = new String[donationList.size()];

                    for (int i = 0; i < donationList.size(); i++) {
                        namelist[i] = donationList.get(i).getFullName();
                        amountlist[i] = donationList.get(i).getDamount();
                        membershipLevel[i] = donationList.get(i).getMembershipLevel();
                    }

                    CustomAdapter customAdapter = new CustomAdapter(Donation_list_Activity.this, namelist, amountlist,membershipLevel);
                    binding.simpleListView.setAdapter(customAdapter);
                } else {
                    // Handle error
                    Log.i("API Error", "Failed to fetch data from the API");
                }
            }

            @Override
            public void onFailure(Call<List<Donation>> call, Throwable t) {
                // Handle failure
                Log.e("API Error", "Network error: " + t.getMessage());
            }
        });
    }
}
