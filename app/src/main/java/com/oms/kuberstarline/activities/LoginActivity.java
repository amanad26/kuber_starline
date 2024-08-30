package com.oms.kuberstarline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.oms.kuberstarline.MainActivity;
import com.oms.kuberstarline.R;
import com.oms.kuberstarline.Uitls.ProgressDialog;
import com.oms.kuberstarline.Uitls.UtilClass;
import com.oms.kuberstarline.apis.BaseUrls;
import com.oms.kuberstarline.apis.RetrofitClient;
import com.oms.kuberstarline.databinding.ActivityLoginBinding;
import com.oms.kuberstarline.models.LoginModel;
import com.oms.kuberstarline.models.LoginRequestModel;
import com.oms.kuberstarline.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    Activity activity;
    ProgressDialog pd;
    boolean isVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        pd = new ProgressDialog(activity);

        binding.gotoRegister.setOnClickListener(v -> startActivity(new Intent(activity, RegisterActivity.class)));

        binding.loginBtn.setOnClickListener(v -> {
            if (isValidate())
                login();
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

    private void login() {
        pd.show();

        RetrofitClient.getClient(activity).login(new LoginRequestModel(
                BaseUrls.apiKey,
                BaseUrls.prod,
                binding.editMobile.getText().toString(),
                binding.editPassword.getText().toString(),
                UtilClass.getDeviceId(activity)
        )).enqueue(new Callback<LoginModel>() {
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
        } else {
            return true;
        }
    }


}