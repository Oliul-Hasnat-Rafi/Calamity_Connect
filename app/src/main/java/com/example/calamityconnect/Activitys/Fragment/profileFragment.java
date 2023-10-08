package com.example.calamityconnect.Activitys.Fragment;

import static android.app.Activity.RESULT_OK;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.calamityconnect.Activitys.Activitys.Login_Activity;
import com.example.calamityconnect.Activitys.model.User;
import com.example.calamityconnect.R;
import com.example.calamityconnect.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class profileFragment extends Fragment {
    Uri profileImgUri;
    FragmentProfileBinding binding;
    FirebaseAuth mauth;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseUser firebaseUser;
    String profileImage,uid;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        setupdialog();
        mauth =FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
         uid = firebaseUser.getUid();

        binding.logout.setOnClickListener(v -> {
            mauth.signOut();
            Intent intent = new Intent(getActivity(), Login_Activity.class);
            startActivity(intent);
        });

        binding.imageView2.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,100);

        });

        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                binding.profileName.setText(user.getUser_name());
                binding.profileEmail.setText(user.getUser_email());
                binding.mobileTv.setText(user.getUser_phone());
                try{
                    Picasso.get()
                            .load(user.getUser_profile())
                            .placeholder(R.drawable.imagehint)
                            .into(binding.imageView2);
                }catch (Exception e){
                    e.printStackTrace();
                    Picasso.get()
                            .load(R.drawable.imagehint)
                            .into(binding.imageView2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.updateProfileBtn.setOnClickListener(v -> {
            if (profileImgUri!=null){
                dialog.show();
                storageReference = FirebaseStorage.getInstance().getReference("profileimg").child(uid);
                storageReference.putFile(profileImgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dialog.dismiss();
                                    profileImage = String.valueOf(uri);
                                    Map<String, Object> userinfo = new HashMap<>();
                                    userinfo.put("user_profile", profileImage);
                                    databaseReference.child(uid).updateChildren(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                dialog.dismiss();
                                                binding.imageView2.setImageResource(R.drawable.imagehint);
                                            }
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Fail to Upload Image", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Fail to Upload Image", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return binding.getRoot();


    }



    private void setupdialog() {
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Uploading Image");
        dialog.setMessage("Please Wait...!");
        dialog.setCancelable(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
        if (resultCode==RESULT_OK){
            if (data!=null){
                profileImgUri=data.getData();
                binding.imageView2.setImageURI(profileImgUri);
            }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}