package com.oms.kuberstarline.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oms.kuberstarline.R;
import com.oms.kuberstarline.Uitls.HomeInterface;
import com.oms.kuberstarline.adapters.HomeAdapter;
import com.oms.kuberstarline.apis.BaseUrls;
import com.oms.kuberstarline.apis.RetrofitClient;
import com.oms.kuberstarline.databinding.FragmentHomeBinding;
import com.oms.kuberstarline.models.HomeModel;
import com.oms.kuberstarline.models.LoginModel;
import com.oms.kuberstarline.models.LoginRequestModel;
import com.oms.kuberstarline.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    Activity activity;
    Session session;
    LoginModel loginModel;
    HomeInterface homeInterface;

    public HomeFragment(HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        activity = requireActivity();
        session = new Session(activity);
        loginModel = session.getUserModel(activity);
        getHomeData();

        binding.starLine.setOnClickListener(v -> homeInterface.onClick(0));
        binding.wallet.setOnClickListener(v -> homeInterface.onClick(1));

        return binding.getRoot();
    }

    private void getHomeData() {
        RetrofitClient.getClient(activity).dashboard(new LoginRequestModel(BaseUrls.apiKey, BaseUrls.prod, loginModel.getUniqueToken(), "")).enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(@NonNull Call<HomeModel> call, @NonNull Response<HomeModel> response) {
                binding.progress.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null) if (response.body().getStatus()) {
                        binding.recycler.setLayoutManager(new LinearLayoutManager(activity));
                        binding.recycler.setAdapter(new HomeAdapter(activity, response.body().getResult()));
                    } else {
                        Toast.makeText(activity, "No record found!", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(@NonNull Call<HomeModel> call, @NonNull Throwable t) {
                binding.progress.setVisibility(View.GONE);
                Toast.makeText(activity, "Server not responding!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}