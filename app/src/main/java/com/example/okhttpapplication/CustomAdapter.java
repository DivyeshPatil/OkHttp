package com.example.okhttpapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewAdapter> {

    ArrayList<Pojo> pojos = new ArrayList<>();

    //get const..
    public CustomAdapter(ArrayList<Pojo> pojos) {
        this.pojos = pojos;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.itemquots, parent, false);
        ViewAdapter viewAdapter = new ViewAdapter(view);
        return viewAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.quots.setText(pojos.get(position).quote);
        holder.auther.setText(pojos.get(position).author);
    }

    @Override
    public int getItemCount() {
        return pojos.size();
    }

    public class ViewAdapter extends RecyclerView.ViewHolder {

        TextView quots, auther;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            quots = itemView.findViewById(R.id.qutes);
            auther = itemView.findViewById(R.id.author);
        }
    }
}
