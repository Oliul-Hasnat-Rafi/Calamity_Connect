package com.example.calamityconnect.Activitys.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calamityconnect.Activitys.model.volunteenmodel;
import com.example.calamityconnect.R;

import java.util.List;

public class volun_adapter extends RecyclerView.Adapter<volun_adapter.volunviewHolder> {
Context context;
List<volunteenmodel> volunteenList;

    public volun_adapter(Context context, List<volunteenmodel> volunteenList) {
        this.context = context;
        this.volunteenList = volunteenList;
    }

    @NonNull
    @Override
    public volunviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new volun_adapter.volunviewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.volunteen_simpol_raw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull volunviewHolder holder, int position) {
        volunteenmodel volunteenmodel =volunteenList.get(position);
        holder.name.setText(volunteenmodel.getName());
        holder.occupation.setText(volunteenmodel.getOccupation());
        holder.district.setText(volunteenmodel.getDistrict());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent1 = new Intent(Intent.ACTION_VIEW, Uri.fromParts(
                        "tel",volunteenmodel.getPhone(), null));
                context.startActivity(Intent1);
            }
        });

        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent2 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",volunteenmodel.getEmail(), null));
                context.startActivity(Intent2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return volunteenList.size();
    }

    public class volunviewHolder extends RecyclerView.ViewHolder {
        TextView name,occupation,district;
        ImageView call,email;
        public volunviewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.nametv);
            occupation=itemView.findViewById(R.id.occtv);
            district=itemView.findViewById(R.id.dis);
            call=itemView.findViewById(R.id.call_img);
            email=itemView.findViewById(R.id.email_img);
        }
    }
}
