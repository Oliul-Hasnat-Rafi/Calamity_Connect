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

import com.example.calamityconnect.Activitys.model.ema_model;
import com.example.calamityconnect.R;

import java.util.List;

public class Ema_Adapter extends RecyclerView.Adapter<Ema_Adapter.EmaviewHolder> {
    Context context;
    List<ema_model> ema_modelList;

    public Ema_Adapter(Context context, List<ema_model> ema_modelList) {
        this.context = context;
        this.ema_modelList = ema_modelList;
    }

    @NonNull
    @Override
    public EmaviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Ema_Adapter.EmaviewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ema_simpol_raw, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmaviewHolder holder, int position) {
      ema_model ema_model = ema_modelList.get(position);
      holder.ema_name.setText(ema_model.getEma_name());
      holder.ema_call.setOnClickListener(v -> {
          Intent ema = new Intent(Intent.ACTION_VIEW, Uri.fromParts(
                  "tel",ema_model.getEma_number(), null));
          context.startActivity(ema);
      });
    }

    @Override
    public int getItemCount() {
        return ema_modelList.size();
    }

    public class EmaviewHolder extends RecyclerView.ViewHolder {

        TextView ema_name;
        ImageView ema_call;

        public EmaviewHolder(@NonNull View itemView) {
            super(itemView);

            ema_name = itemView.findViewById(R.id.ematv);
            ema_call = itemView.findViewById(R.id.ema_call_img);
        }
    }
}
