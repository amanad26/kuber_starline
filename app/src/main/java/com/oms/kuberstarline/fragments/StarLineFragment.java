package com.oms.kuberstarline.fragments;

import static java.lang.Thread.sleep;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oms.kuberstarline.R;
import com.oms.kuberstarline.adapters.StarLineAdapter;
import com.oms.kuberstarline.databinding.FragmentStarLineBinding;

public class StarLineFragment extends Fragment {

    FragmentStarLineBinding binding;
    Activity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStarLineBinding.inflate(getLayoutInflater());

        activity = requireActivity();

        getStarLine();
        return binding.getRoot();
    }

    private void getStarLine() {

        new Thread(() -> {
            try {
                sleep(3000);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressbar.setVisibility(View.GONE);
                        binding.recycler.setLayoutManager(new LinearLayoutManager(activity));
                        binding.recycler.setAdapter(new StarLineAdapter(activity));
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}