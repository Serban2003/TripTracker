package com.example.triptracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    String[] titles;
    String type;
    OnItemClickListener listener;

    public RecyclerViewAdapter(Context context, String[] titles, String type, OnItemClickListener listener){
        this.titles = titles;
        this.context = context;
        this.type = type;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;

        if(Objects.equals(type, "mainCategory")) view = layoutInflater.inflate(R.layout.recycler_row_main, parent, false);
        else view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(titles[position]);
        holder.bind(titles[position], listener);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.settingsTitle);
        }

        public void bind(final String item, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }
}