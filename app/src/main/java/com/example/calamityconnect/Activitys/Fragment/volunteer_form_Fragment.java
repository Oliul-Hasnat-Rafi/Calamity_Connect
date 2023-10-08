package com.example.calamityconnect.Activitys.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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


public class volunteer_form_Fragment extends Fragment {
    FragmentVolunteerFormBinding binding;

    RequestQueue requestQueue;
    ProgressDialog dialog;
    public static final String UPLOAD_URL = "https://zirwabd.000webhostapp.com/image/volunteer.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVolunteerFormBinding.inflate(getLayoutInflater(),container,false);

        binding.Join.setOnClickListener(v -> {
            volunteer_form();
        });

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Uploading Data");
        dialog.setMessage("Please Wait...!");
        dialog.setCancelable(false);

        return binding.getRoot();
    }

    private void volunteer_form() {
        String vname = binding.vname.getText().toString();
        String vPhone = binding.vPhone.getText().toString();
        String vEmail = binding.vEmail.getText().toString();
        String vdistrict = binding.vdistrict.getText().toString();
        String occupation = binding.occupation.getText().toString();
        String NID = binding.NID.getText().toString();


        if (vname.equals("")) {
            binding.vname.setError("Required");
        } else if (vPhone.equals("")) {
            binding.vPhone.setError("Required");
        }else if (vEmail.equals("")) {
            binding.vEmail.setError("Required");
        }else if (vdistrict.equals("")) {
            binding.vdistrict.setError("Required");
        }else if (occupation.equals("")) {
            binding.occupation.setError("Required");
        }else if (NID.equals("")) {
            binding.NID.setError("Required");
        }


        else {
            dialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                binding.vname.setText("");
                                binding.vPhone.setText("");
                                binding.vEmail.setText("");
                                binding.vdistrict.setText("");
                                binding.occupation.setText("");
                                binding.NID.setText("");
                                dialog.dismiss();
                                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                            
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();

                }
            })
//            Post Main Part
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", vname);
                    params.put("phone", vPhone);
                    params.put("email", vEmail);
                    params.put("district", vdistrict);
                    params.put("occupation", occupation);
                    params.put("NID", NID);
                    Log.i("TAG", "getParams: "+params);
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);

        }
    }
}