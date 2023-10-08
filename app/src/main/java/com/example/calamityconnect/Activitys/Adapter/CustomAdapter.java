package com.example.calamityconnect.Activitys.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.calamityconnect.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String namelist[];
    String amountlist[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] namelist, String[] amountlist) {
        this.context = context;
        this.namelist = namelist;
        this.amountlist = amountlist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return amountlist.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView d_name = (TextView) view.findViewById(R.id.nametv);
        TextView d_amount = (TextView) view.findViewById(R.id.amounttv);

        d_name.setText("Name: "+namelist[i]);
        d_amount.setText("Amount: "+amountlist[i]);

        return view;

    }
}