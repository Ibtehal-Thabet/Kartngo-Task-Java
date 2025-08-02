package com.example.kartngotaskjava.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.kartngotaskjava.data.dao.MealDao;
import com.example.kartngotaskjava.data.database.MealsDatabase;
import com.example.kartngotaskjava.models.Meal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MealRepo {
    private final MealDao mealDao;
    private final LiveData<List<Meal>> allMeals; // Proxy AI
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public MealRepo(MealDao mealDao) { // Proxy AI
        this.mealDao = mealDao;
        this.allMeals = mealDao.getAllMeals();
    }

    public MealRepo(Application application) {
        MealsDatabase database = MealsDatabase.getInstance(application);
        mealDao = database.mealDao();
        allMeals = mealDao.getAllMeals();
    }

    //methods for database operations :-
    public void insertMeal(Meal meal) {
        executorService.execute(() -> mealDao.insertMeal(meal));
    }
    public void updateMealQuantity(int id, int quantity) {
        executorService.execute(() -> mealDao.updateMealQuantity(id, quantity));
    }
    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }

}