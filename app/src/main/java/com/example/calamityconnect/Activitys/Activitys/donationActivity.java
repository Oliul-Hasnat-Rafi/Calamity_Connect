package com.example.calamityconnect.Activitys.Activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.calamityconnect.Activitys.model.User;
import com.example.calamityconnect.databinding.ActivityDonationBinding;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

import java.util.HashMap;
import java.util.Map;

public class donationActivity extends AppCompatActivity implements SSLCTransactionResponseListener {
    public static final String UPLOAD_URL = "https://zirwabd.000webhostapp.com/image/donation.php";
    ActivityDonationBinding binding;
    User user = new User();
    String tran_id = "0234325443546";
    String pro_ctg = "it";
    String done_name, done_number, done_amount;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Donetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDonationButtonClick();
            }
        });
    }

    private void handleDonationButtonClick() {
        done_name = binding.doneName.getText().toString();
        done_number = binding.doneNumber.getText().toString();
        done_amount = binding.doneAmount.getText().toString();

        if (validateInput()) {
            initializeSSLCommerz();
        }
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (done_name.isEmpty()) {
            binding.doneName.setError("required");
            isValid = false;
        }

        if (done_number.isEmpty()) {
            binding.doneNumber.setError("required");
            isValid = false;
        }

        if (done_amount.isEmpty()) {
            binding.doneAmount.setError("required");
            isValid = false;
        }

        return isValid;
    }

    private void initializeSSLCommerz() {
        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization(
                "creat613377970f2ed",
                "creat613377970f2ed@ssl",
                Double.parseDouble(done_amount),
                SSLCCurrencyType.BDT,
                tran_id,
                pro_ctg,
                SSLCSdkType.TESTBOX
        );

        IntegrateSSLCommerz
                .getInstance(donationActivity.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .buildApiCall(donationActivity.this);
    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
        performDonation();
    }

    private void performDonation() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        clearInputFields();
                        Toast.makeText(donationActivity.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(donationActivity.this, Donation_list_Activity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(donationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Name", done_name);
                params.put("amount", done_amount);
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(donationActivity.this);
        requestQueue.add(stringRequest);
    }

    private void clearInputFields() {
        binding.doneName.setText("");
        binding.doneNumber.setText("");
        binding.doneAmount.setText("");
    }

    @Override
    public void transactionFail(String s) {
        // Handle transaction failure
    }

    @Override
    public void merchantValidationError(String s) {
        // Handle merchant validation error
    }
}
