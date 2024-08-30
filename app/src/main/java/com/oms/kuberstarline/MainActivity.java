package com.oms.kuberstarline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.marsad.stylishdialogs.StylishAlertDialog;
import com.oms.kuberstarline.Uitls.HomeInterface;
import com.oms.kuberstarline.apis.BaseUrls;
import com.oms.kuberstarline.apis.RetrofitClient;
import com.oms.kuberstarline.databinding.ActivityMainBinding;
import com.oms.kuberstarline.fragments.AddMoneyFragment;
import com.oms.kuberstarline.fragments.HomeFragment;
import com.oms.kuberstarline.fragments.ProfileFragment;
import com.oms.kuberstarline.fragments.StarLineFragment;
import com.oms.kuberstarline.models.LoginModel;
import com.oms.kuberstarline.models.LoginRequestModel;
import com.oms.kuberstarline.models.ProfileModel;
import com.oms.kuberstarline.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements HomeInterface {

    ActivityMainBinding binding;
    Activity activity;
    Session session;
    boolean isHome = true;
    ProfileModel.Profile profile = null;
    LoginModel loginModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);

        loginModel = session.getUserModel(activity);

        binding.icMenu.setOnClickListener(view -> binding.drawerLayout.open());
        getProfile();

        binding.logoutNav.setOnClickListener(v -> {
            logout();
        });

        loadFrag(new HomeFragment(MainActivity.this));

        binding.navHome.setOnClickListener(v -> drawerListener(binding.navHome));
        binding.profileNav.setOnClickListener(v -> drawerListener(binding.profileNav));
        binding.starLine.setOnClickListener(v -> drawerListener(binding.starLine));
        binding.walletAmount.setOnClickListener(v -> drawerListener(binding.walletAmount));

        binding.walletImage.setOnClickListener(v -> binding.walletAmount.performClick());
        binding.walletNav.setOnClickListener(v -> binding.walletAmount.performClick());

    }

    private void loadFrag(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(binding.fullscreenContainer.getId(), fragment);
        ft.commit();
    }

    private void getProfile() {
        RetrofitClient.getClient(activity).getProfile(new LoginRequestModel(BaseUrls.apiKey, BaseUrls.prod, loginModel.getUniqueToken(), "")).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<ProfileModel> call, @NonNull Response<ProfileModel> response) {
                if (response.code() == 200)
                    if (response.body() != null) if (response.body().getStatus()) {
                        session.saveProfile(response.body().getProfile().get(0));
                        binding.userName.setText(response.body().getProfile().get(0).getUserName());
                    } else {
                        Toast.makeText(activity, "No record found!", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void drawerListener(TextView item) {
        isHome = false;
        if (item.getId() == binding.navHome.getId()) {
            loadFrag(new HomeFragment(MainActivity.this));
            isHome = true;
        } else if (item.getId() == binding.profileNav.getId()) {
            loadFrag(new ProfileFragment());
            isHome = false;
        } else if (item.getId() == binding.starLine.getId()) {
            loadFrag(new StarLineFragment());
            isHome = false;
        } else if (item.getId() == binding.walletAmount.getId()) {
            loadFrag(new AddMoneyFragment());
            isHome = false;
        }
        binding.drawerLayout.close();
    }

    private void logout() {
        StylishAlertDialog stylishDialog = new StylishAlertDialog(activity, StylishAlertDialog.WARNING);
        stylishDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        stylishDialog.setTitleText("Logout!").setContentText("Do you want to logout from this device?").setCancellable(false)
                //.setCancelledOnTouchOutside(false)
                .setConfirmButton("Yes", StylishAlertDialog -> {
                    session.logout();
                    session.setLogin(false);
                    StylishAlertDialog.dismiss();
                }).setCancelButton("No", StylishAlertDialog -> StylishAlertDialog.dismiss()).show();
    }

    @Override
    public void onBackPressed() {
        if (isHome) {
            StylishAlertDialog stylishDialog = new StylishAlertDialog(activity, StylishAlertDialog.WARNING);
            stylishDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            stylishDialog.setTitleText("Exit!").setContentText("do you want to exit?").setCancellable(false).setConfirmButton("yes", StylishAlertDialog -> {
                super.onBackPressed();
                StylishAlertDialog.dismiss();
            }).setCancelButton("No", StylishAlertDialog -> StylishAlertDialog.dismiss()).show();
        } else {
            loadFrag(new HomeFragment(MainActivity.this));
            isHome = true;
        }

    }

    @Override
    public void onClick(int n) {
        if (n == 0) binding.starLine.performClick();
        else if (n == 1) binding.walletAmount.performClick();
    }
}