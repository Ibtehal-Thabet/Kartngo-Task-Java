package com.example.kartngotaskjava.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.kartngotaskjava.models.Meal;

import java.util.List;


@Dao
public interface MealDao { // Proxy AI

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal... mealItems);

    @Query("UPDATE mealsTable SET quantity = :quantity WHERE id = :id")
    void updateMealQuantity(int id, int quantity);


    @Query("SELECT * FROM mealsTable")
    LiveData<List<Meal>> getAllMeals();
}