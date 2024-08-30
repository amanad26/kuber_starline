package com.oms.kuberstarline.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oms.kuberstarline.R;
import com.oms.kuberstarline.databinding.FragmentAddMoneyBinding;
import com.oms.kuberstarline.session.Session;

public class AddMoneyFragment extends Fragment {

    FragmentAddMoneyBinding binding;
    Activity activity;
    Session session;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddMoneyBinding.inflate(getLayoutInflater());
        activity = requireActivity();

        return binding.getRoot();
    }
}