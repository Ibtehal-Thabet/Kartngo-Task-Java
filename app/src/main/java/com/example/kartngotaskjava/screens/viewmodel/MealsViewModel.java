package com.example.kartngotaskjava.screens.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.kartngotaskjava.data.repository.MealRepo;
import com.example.kartngotaskjava.models.Meal;

import java.util.ArrayList;
import java.util.List;


public class MealsViewModel extends AndroidViewModel {

    private final MealRepo mealRepo;
    private final MutableLiveData<List<Meal>> _allMeals = new MutableLiveData<>();
    public LiveData<List<Meal>> allMeals = _allMeals;
    private final MutableLiveData<Integer> _totalQuantity = new MutableLiveData<>();
    public LiveData<Integer> getTotalQuantity() { return _totalQuantity; }

    private final MutableLiveData<Double> _totalPrice = new MutableLiveData<>();
    public LiveData<Double> getTotalPrice() { return _totalPrice; }

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MealsViewModel(@NonNull Application application) {
        super(application);
        loadMeals();

        mealRepo = new MealRepo(application);
        allMeals = mealRepo.getAllMeals();

        insertMeals();

        allMeals.observeForever(this::updateTotalPrices);
    }

    private void insertMeals() {
        for (Meal meal : mealList) {
            mealRepo.insertMeal(meal);
        }
    }

    public void updateMealQuantity(int id, int quantity) {  // Proxy AI
        mealRepo.updateMealQuantity(id, quantity);
    }

    public void increaseMealQuantity(Meal meal) {
        int newQuantity = meal.getMealQuantity() + 1;
        updateMealQuantity(meal.getId(), newQuantity);
    }

    public void decreaseMealQuantity(Meal meal) {
        int newQuantity = Math.max(0, meal.getMealQuantity() - 1);
        updateMealQuantity(meal.getId(), newQuantity);
    }

    private void updateTotalPrices(List<Meal> mealList) {
        int sumQuantity = 0;
        double sumPrice = 0.0;
        for(Meal meal: mealList){
            sumQuantity += meal.getMealQuantity();
            sumPrice += meal.getMealPrice()* meal.getMealQuantity();
        }
        _totalQuantity.setValue(sumQuantity);
        _totalPrice.setValue(sumPrice);
    }

    public LiveData<List<Meal>> getAllMeals() { // Proxy AI
        return allMeals;
    }

    private void loadMeals() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            isLoading.setValue(false);
        }, 1000);
    }

    List<Meal> mealList = List.of(
            new Meal("Classic Burger", "classic_burger", 20.5, 0),
            new Meal("Chess Burger", "chess_burger", 25.5, 0),
            new Meal("Burger Double", "burger_double", 28.5, 0),
            new Meal("Veggie Burger", "veggie_burger", 30.5, 0),
            new Meal("Smash Burger", "smash_burger", 18.0, 0),
            new Meal("Burger and Fries","burger_and_fries",  24.0, 0),
            new Meal("Combo Burger", "double_burger_combo", 27.0, 0),
            new Meal("Chicken Burger", "chicken_burger", 17.5, 0),
            new Meal("Chicken Burger Double", "double_chicken_burger", 19.5, 0),
            new Meal("HotDog", "hot_dog", 16.5, 0),
            new Meal("Fries Bucket", "fries", 8.5, 0)
    );
}