package com.example.calamityconnect.Activitys.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.calamityconnect.Activitys.model.User;
import com.example.calamityconnect.databinding.ActivityDonationBinding;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class donationActivity extends AppCompatActivity implements SSLCTransactionResponseListener {
    ActivityDonationBinding binding;
    User user = new User();
    String tran_id = "0234325443546";
    String pro_ctg = "it";
    String done_name,done_number,done_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.Donetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               done_name = binding.doneName.getText().toString();
                 done_number = binding.doneNumber.getText().toString();
                 done_amount = binding.doneAmount.getText().toString();

                if (done_name.equals("")) {
                    binding.doneName.setError("required");
                }
                if (done_number.equals("")) {
                    binding.doneNumber.setError("required");
                }
                if (done_amount.equals("")) {
                    binding.doneAmount.setError("required");
                } else {


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

            }
        });
    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
        Intent intent =new Intent(donationActivity.this, Donation_list_Activity.class);
        intent.putExtra("name",done_name);
        intent.putExtra("amount",done_amount);
        startActivity(intent);
    }

    @Override
    public void transactionFail(String s) {

    }

    @Override
    public void merchantValidationError(String s) {

    }
}