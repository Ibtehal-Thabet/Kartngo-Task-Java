package com.example.kartngotaskjava.screens.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kartngotaskjava.R;
import com.example.kartngotaskjava.databinding.ActivityMainBinding;
import com.example.kartngotaskjava.screens.fragments.BestOffersFragment;
import com.example.kartngotaskjava.screens.viewmodel.MealsViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MealsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MealsViewModel.class);

        binding.setIsLoading(true); // initially true
        binding.setLifecycleOwner(this);
        viewModel.getIsLoading().observe(this, isLoading -> {
            binding.setIsLoading(isLoading);
        });

        setFragment(new BestOffersFragment());

        List<String> categoriesList = List.of("أفضل العروض", "مستورد", "أجبان قابلة للدهن", "أجبان", "وجبات", "سندويتشات");
        bindTabs(categoriesList);
        updateSnackbar();

        // Finish app by back arrow
        binding.toolbar.setNavigationOnClickListener(view -> finishAffinity());  // Proxy AI
//        binding.fragmentContainer.getRootView().getOverlay().clear(); // Added line to prevent overlay
    }

    private void setFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void bindTabs(List<String> categories) {
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < categories.size(); i++) {
            String category = categories.get(i);

            TabLayout.Tab tab = binding.tabLayout.newTab();
            View tabView = inflater.inflate(R.layout.custom_tab, null);

            TextView tabText = tabView.findViewById(R.id.tab_title);
            ImageView checkIcon = tabView.findViewById(R.id.tab_icon);

            tabText.setText(category);

            boolean isSelected = i == 0;
            updateTabView(tabView, isSelected);

            tab.setCustomView(tabView);
            tab.setTag(category);
            binding.tabLayout.addTab(tab);

            // Set margins programmatically
            View tabLayoutView = tab.view;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tabLayoutView.getLayoutParams());
            layoutParams.setMargins(12, 18, 12, 12);
            tabLayoutView.setLayoutParams(layoutParams);
        }

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabView(tab.getCustomView(), true);
                setFragment(new BestOffersFragment());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabView(tab.getCustomView(), false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Optional behavior
            }
        });

        binding.tabLayout.getTabAt(0).select();
    }

    private void updateSnackbar() {
        viewModel.getTotalPrice().observe(this, total -> {
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) binding.fragmentContainer.getLayoutParams();

            if (total > 0) {
                binding.snackbarLayout.setVisibility(View.VISIBLE);
                binding.totalCart.setText(total + " SAR");

                layoutParams.setMargins(0, 8, 0, 160);
            } else {
                binding.snackbarLayout.setVisibility(View.GONE);
                layoutParams.setMargins(0, 8, 0, 8);
            }

            binding.fragmentContainer.setLayoutParams(layoutParams);
        });
    }

    private void updateTabView(View view, boolean isSelected) {
        if (view == null) return;

        view.setBackgroundResource(isSelected ? R.drawable.selected_tab_shape : R.drawable.unselected_tab_shape);

        ImageView checkIcon = view.findViewById(R.id.tab_icon);
        if (checkIcon != null) {
            checkIcon.setVisibility(isSelected ? View.VISIBLE : View.GONE);
        }
    }
}