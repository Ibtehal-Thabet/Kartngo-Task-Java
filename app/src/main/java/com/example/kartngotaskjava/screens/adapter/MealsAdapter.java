package com.example.kartngotaskjava.screens.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kartngotaskjava.R;
import com.example.kartngotaskjava.databinding.MealCardBinding;
import com.example.kartngotaskjava.models.Meal;
import com.example.kartngotaskjava.screens.viewmodel.MealsViewModel;

import java.util.List;

public class MealsAdapter extends ListAdapter<Meal, MealsAdapter.ViewHolder> {
    private final MealsViewModel viewModel;
    public MealsAdapter(MealsViewModel viewModel) {
        super(new MealDiffCallback());
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MealCardBinding binding = MealCardBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = getItem(position);
        holder.bind(meal, viewModel);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MealCardBinding binding;

        public ViewHolder(MealCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Meal meal, MealsViewModel viewModel){
            binding.setMeal(meal);
            binding.setViewModel(viewModel); // Proxy AI
            binding.executePendingBindings(); // Proxy AI
        }

    }

    public static class MealDiffCallback extends DiffUtil.ItemCallback<Meal> {

        @Override
        public boolean areItemsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem.equals(newItem);
        }
    }
}
