package com.example.calamityconnect.Activitys.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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
import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.FragmentPostBinding;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class postFragment extends Fragment {
    FragmentPostBinding binding;
    private static final int STORAGE_PERMISSION_CODE = 4655;
    private final int PICK_IMAGE_REQUEST = 1;
    private Uri filepath;
    private Bitmap bitmap;
    String encodeImageString;
   public final String valid = "false" ;
   RequestQueue requestQueue;
    ProgressDialog dialog;
  public static final String UPLOAD_URL = "https://zirwabd.000webhostapp.com/image/Calamity.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(getLayoutInflater(),container,false);
        requestStoragePermission();
        setupdialog();
        binding.upload.setOnClickListener(v -> {
            ShowFileChooser();
        });
        binding.publishBtn.setOnClickListener(v -> {
            uploadImage();
        });

        int wordlimit =189;
        binding.WordLimit.setText("Word Limit:"+wordlimit);

        binding.mainDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int wordCount = s.toString().trim().split("\\s+").length;
                binding.WordCount.setText("Word Count:"+wordCount);

//                if (wordCount > wordlimit) {
//                    Toast.makeText(getContext(), "Word limit exceeded!", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return binding.getRoot();
    }

    private void setupdialog() {
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Uploading Data");
        dialog.setMessage("Please Wait...!");
        dialog.setCancelable(false);
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getActivity(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void ShowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            filepath = data.getData();
            try
            {
                InputStream inputStream=getActivity().getApplicationContext().getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                binding.selectimg.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
            }catch (Exception ex)
            {

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }


    private void uploadImage() {

        String title = binding.title.getText().toString().trim();
        String Description = binding.mainDescription.getText().toString();

        if (title.equals("")) {
            binding.title.setError("Required");
        } else if (Description.equals("")) {
            binding.mainDescription.setError("Required");
        }

        else if (Description.length()>1309) {
            binding.mainDescription.setError("Description is to Long");
        }
        else {
            dialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            binding.title.setText("");
                            binding.mainDescription.setText("");
                            binding.selectimg.setImageResource(R.drawable.imagehint);
                            dialog.dismiss();
                            Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Fail to Upload Data", Toast.LENGTH_SHORT).show();

                }
            })
//            Post Main Part
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("title", title);
                    params.put("description", Description);
                    params.put("upload", encodeImageString);
                    params.put("valid", valid);
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);

        }


//    private String getPath(Uri uri) {
//        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        String document_id = cursor.getString(0);
//        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
//        cursor = getActivity().getApplicationContext().getContentResolver().query(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + "=?", new String[]{document_id}, null
//        );
//        cursor.moveToFirst();
//        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//        cursor.close();
//        return path;
//
//    }
    }

}