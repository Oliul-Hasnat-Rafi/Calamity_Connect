package com.example.calamityconnect.Activitys.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.calamityconnect.databinding.FragmentVolunteerFormBinding;

import java.util.HashMap;
import java.util.Map;

public class volunteer_form_Fragment  extends Fragment {
    private static final String UPLOAD_URL = "https://zirwabd.000webhostapp.com/image/volunteer.php";

    private FragmentVolunteerFormBinding binding;
    private RequestQueue requestQueue;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVolunteerFormBinding.inflate(inflater, container, false);

        binding.Join.setOnClickListener(v -> volunteerForm());

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Uploading Data");
        dialog.setMessage("Please Wait...!");
        dialog.setCancelable(false);

        return binding.getRoot();
    }

    private void volunteerForm() {
        if (!isNetworkAvailable()) {
            Toast.makeText(getActivity(), "No active network connection", Toast.LENGTH_SHORT).show();
            return;
        }

        if (validateInput()) {
            dialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    response -> {
                        clearFields();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    },
                    error -> {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", binding.vname.getText().toString());
                    params.put("phone", binding.vPhone.getText().toString());
                    params.put("email", binding.vEmail.getText().toString());
                    params.put("district", binding.vdistrict.getText().toString());
                    params.put("occupation", binding.occupation.getText().toString());
                    params.put("NID", binding.NID.getText().toString());
                    Log.i("TAG", "getParams: " + params);
                    return params;
                }
            };

            getRequestQueue().add(stringRequest);
        }
    }

    private boolean validateInput() {
        String vname = binding.vname.getText().toString();
        String vPhone = binding.vPhone.getText().toString();
        String vEmail = binding.vEmail.getText().toString();
        String vdistrict = binding.vdistrict.getText().toString();
        String occupation = binding.occupation.getText().toString();
        String NID = binding.NID.getText().toString();

        if (vname.isEmpty() || vPhone.isEmpty() || vEmail.isEmpty() || vdistrict.isEmpty() || occupation.isEmpty() || NID.isEmpty()) {
            Toast.makeText(getActivity(), "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void clearFields() {
        binding.vname.setText("");
        binding.vPhone.setText("");
        binding.vEmail.setText("");
        binding.vdistrict.setText("");
        binding.occupation.setText("");
        binding.NID.setText("");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(requireContext().getApplicationContext());
        }
        return requestQueue;
    }
}