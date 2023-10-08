package com.example.calamityconnect.Activitys.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calamityconnect.Activitys.Activitys.detail_Activity;
import com.example.calamityconnect.Activitys.model.model;
import com.example.calamityconnect.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.itemviewholder> {

    Context context;
    List<model> modelList;

    public adapter(Context context, List<model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemviewholder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simpol_raw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemviewholder holder, int position) {
        model model  = modelList.get(position);
        Picasso.get()
                .load(model.getImage())
                .into(holder.imageView);
        holder.Title.setText(model.getTitle());

        holder.imageView.setOnClickListener(view -> {
            Intent intent =new Intent(context, detail_Activity.class);
            intent.putExtra("image",model.getImage());
            intent.putExtra("title",model.getTitle());
            intent.putExtra("des",model.getDes());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class itemviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Title;
        public itemviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            Title=itemView.findViewById(R.id.name);
        }
    }

}
