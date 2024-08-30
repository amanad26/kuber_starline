package com.oms.kuberstarline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oms.kuberstarline.R;
import com.oms.kuberstarline.databinding.HomeListLayoutBinding;
import com.oms.kuberstarline.databinding.StartlineListLayoutBinding;
import com.oms.kuberstarline.models.HomeModel;

import java.util.List;

public class StarLineAdapter extends RecyclerView.Adapter<StarLineAdapter.ViewHolder> {

    Context context;

    public StarLineAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.startline_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        StartlineListLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = StartlineListLayoutBinding.bind(itemView);
        }
    }

}
