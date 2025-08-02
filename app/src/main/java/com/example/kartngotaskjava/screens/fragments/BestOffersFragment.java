package com.example.kartngotaskjava.screens.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.kartngotaskjava.databinding.FragmentBestOffersBinding;
import com.example.kartngotaskjava.screens.adapter.MealsAdapter;
import com.example.kartngotaskjava.screens.viewmodel.MealsViewModel;

import java.util.List;

public class BestOffersFragment extends Fragment {
    private FragmentBestOffersBinding viewBinding;
    private MealsViewModel viewModel;
    private MealsAdapter adapter;

    public BestOffersFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  // Proxy AI

        viewModel = new ViewModelProvider(this).get(MealsViewModel.class);
        viewBinding = FragmentBestOffersBinding.inflate(inflater, container, false);

        viewBinding.setViewModel(viewModel);
        viewBinding.setLifecycleOwner(this);

        adapter = new MealsAdapter(viewModel);
        viewBinding.mealsRecyclerView.setAdapter(adapter);
        viewModel.getAllMeals().observe(getViewLifecycleOwner(), meals -> { //called every time data changes
            adapter.submitList(meals);
        });

        return viewBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null; // Prevent memory leaks
    }
}