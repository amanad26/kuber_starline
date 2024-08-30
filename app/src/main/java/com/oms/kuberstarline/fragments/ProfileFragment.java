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
import com.oms.kuberstarline.Uitls.ProgressDialog;
import com.oms.kuberstarline.adapters.HomeAdapter;
import com.oms.kuberstarline.apis.BaseUrls;
import com.oms.kuberstarline.apis.RetrofitClient;
import com.oms.kuberstarline.databinding.FragmentProfileBinding;
import com.oms.kuberstarline.models.HomeModel;
import com.oms.kuberstarline.models.LoginModel;
import com.oms.kuberstarline.models.LoginRequestModel;
import com.oms.kuberstarline.models.ProfileModel;
import com.oms.kuberstarline.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    Activity activity;
    Session session;
    ProfileModel.Profile data = null;
    ProgressDialog pd;
    LoginModel loginModel = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        activity = requireActivity();
        session = new Session(activity);
        pd = new ProgressDialog(activity);

        loginModel = session.getUserModel(activity);
        data = session.getProfile(activity);
        if (data == null) {
            pd.show();
        } else {
            setData(data);
        }
        getProfile();
        return binding.getRoot();
    }

    private void getProfile() {
        RetrofitClient.getClient(activity).getProfile(new LoginRequestModel(BaseUrls.apiKey, BaseUrls.prod, loginModel.getUniqueToken(), "")).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<ProfileModel> call, @NonNull Response<ProfileModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null) if (response.body().getStatus()) {
                        setData(response.body().getProfile().get(0));
                        session.saveProfile(response.body().getProfile().get(0));
                    } else {
                        Toast.makeText(activity, "No record found!", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding!", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    private void setData(ProfileModel.Profile data) {
        binding.name.setText(data.getUserName());
        binding.email.setText(data.getEmail());
        binding.mobile.setText(data.getMobile());
        binding.balance.setText(data.getWalletBalance());
    }
}