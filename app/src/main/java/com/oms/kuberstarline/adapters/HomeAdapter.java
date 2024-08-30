package com.oms.kuberstarline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oms.kuberstarline.R;
import com.oms.kuberstarline.databinding.HomeListLayoutBinding;
import com.oms.kuberstarline.models.HomeModel;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<HomeModel.Result> models;
    Context context;

    public HomeAdapter(Context context, List<HomeModel.Result> models) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.name.setText(models.get(position).getGameName());
        holder.binding.closeTime.setText(models.get(position).getCloseTime());
        holder.binding.openTime.setText(models.get(position).getOpenTime());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HomeListLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeListLayoutBinding.bind(itemView);
        }
    }

}
