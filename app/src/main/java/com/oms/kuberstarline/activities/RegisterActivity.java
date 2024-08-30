package com.oms.kuberstarline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.oms.kuberstarline.MainActivity;
import com.oms.kuberstarline.R;
import com.oms.kuberstarline.Uitls.ProgressDialog;
import com.oms.kuberstarline.Uitls.UtilClass;
import com.oms.kuberstarline.apis.BaseUrls;
import com.oms.kuberstarline.apis.RetrofitClient;
import com.oms.kuberstarline.databinding.ActivityRegisterBinding;
import com.oms.kuberstarline.models.LoginModel;
import com.oms.kuberstarline.models.LoginRequestModel;
import com.oms.kuberstarline.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    Activity activity;
    ProgressDialog pd;
    private boolean isVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        pd = new ProgressDialog(activity);

        binding.gotoLogin.setOnClickListener(v -> finish());

        binding.btnRegister.setOnClickListener(v -> {
            if (isValidate())
                register();
        });

        binding.hideShowPassword.setOnClickListener(v -> {
            if (!isVisible) {
                UtilClass.showPassword(binding.editPassword, binding.hideShowPassword);
                isVisible = true;
            } else {
                UtilClass.hidePassword(binding.editPassword, binding.hideShowPassword);
                isVisible = false;
            }
        });


    }

    private void register() {
        pd.show();
        RetrofitClient.getClient(activity).register(new LoginRequestModel(
                        BaseUrls.apiKey,
                        BaseUrls.prod,
                        binding.editMobile.getText().toString(),
                        binding.editPassword.getText().toString(),
                        binding.editNmae.getText().toString(),
                        binding.editMobile.getText().toString()
                )
        ).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus()) {
                            Session session = new Session(activity);
                            session.setLogin(true);
                            session.saveUserModel(response.body());
                            Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(activity, MainActivity.class));
                            finish();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(activity, "Server not responding!", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });

    }

    private boolean isValidate() {
        if (binding.editMobile.getText().toString().equalsIgnoreCase("")) {
            binding.editMobile.setError("Enter Mobile Number!");
            binding.editMobile.requestFocus();
            return false;
        } else if (binding.editMobile.getText().toString().length() < 10) {
            binding.editMobile.setError("Number must be 10 digits!");
            binding.editMobile.requestFocus();
            return false;
        } else if (binding.editPassword.getText().toString().equalsIgnoreCase("")) {
            binding.editPassword.setError("Enter Password!");
            binding.editPassword.requestFocus();
            return false;
        } else if (binding.editEmail.getText().toString().equalsIgnoreCase("")) {
            binding.editEmail.setError("Enter Email!");
            binding.editEmail.requestFocus();
            return false;
        } else if (binding.editNmae.getText().toString().equalsIgnoreCase("")) {
            binding.editNmae.setError("Enter Name!");
            binding.editNmae.requestFocus();
            return false;
        } else if (!UtilClass.isValidEmail(binding.editEmail.getText().toString())) {
            binding.editEmail.setError("Invalid Email!");
            binding.editEmail.requestFocus();
            return false;
        } else {
            return true;
        }
    }

}