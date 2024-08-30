package com.oms.kuberstarline.activities;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.oms.kuberstarline.MainActivity;
import com.oms.kuberstarline.R;
import com.oms.kuberstarline.session.Session;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    Activity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = this;
        session = new Session(activity);

        new Thread(() -> {
            try {
                sleep(2000);
                if (session.isLoggedIn())
                    startActivity(new Intent(activity, MainActivity.class));
                else
                    startActivity(new Intent(activity, LoginActivity.class));

                finish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}