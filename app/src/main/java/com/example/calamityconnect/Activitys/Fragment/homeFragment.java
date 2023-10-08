package com.example.calamityconnect.Activitys.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.calamityconnect.Activitys.Adapter.adapter;
import com.example.calamityconnect.Activitys.Retrofit.MyRetrofit;
import com.example.calamityconnect.Activitys.Retrofit.postapi;
import com.example.calamityconnect.Activitys.model.model;
import com.example.calamityconnect.databinding.FragmentHomeBinding;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homeFragment extends Fragment {
    FragmentHomeBinding binding;
    ArrayList<model> modelList;
    private adapter adapter;
    RequestQueue queue;
    String url ="https://zirwabd.000webhostapp.com/image/json_user_fetch.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(),container,false);


        slider();


        modelList=new ArrayList<>();

        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        binding.recylerview.setLayoutManager(layoutManager);


        postapi postapi = MyRetrofit.getRetrofit().create(postapi.class);

        Call<List<model>> listpost = postapi.getallpost();

        listpost.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                modelList = (ArrayList<model>) response.body();
                if (modelList.size()>0){
                    ArrayList<model> filteredList = new ArrayList<>();
                    for (model post : modelList) {
                        String postvalid = post.getValid();
                        if (!postvalid.equals("false")) {
                            String name = post.getTitle();
                            String des = post.getDes();
                            String image = post.getImage();
                            post.setTitle(name);
                            post.setDes(des);
                            String uriimage = "https://zirwabd.000webhostapp.com/image/images/" + image;
                            post.setImage(uriimage);
                            filteredList.add(post);
                        }
                    }
                   adapter = new adapter(getContext(), filteredList);
                    binding.recylerview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }});

//        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                adapter.notifyDataSetChanged();
//
//
//                binding33.refresh.setRefreshing(false);
//            }
//        });

        return binding.getRoot();
    }

    private void slider() {
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, url, null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i=0; i<response.length();i++){
                            try {
                                JSONObject jsonObject =response.getJSONObject(i);
                                String image = jsonObject.getString("image");
                                String uriimage = "https://zirwabd.000webhostapp.com/image/images/" + image;
                                binding.carousel.addData(new CarouselItem(uriimage));
                                //   Log.i("TAG", "onResponse: "+image);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }
}