package com.example.kartngotaskjava.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.kartngotaskjava.data.dao.MealDao;
import com.example.kartngotaskjava.models.Meal;

@Database(entities = {Meal.class},version = 1,exportSchema = false)
public abstract class MealsDatabase extends RoomDatabase {

    private static volatile MealsDatabase instance; //only one interface

    public abstract MealDao mealDao();

    public static MealsDatabase getInstance(Context context){
        if(instance == null){
            synchronized (MealsDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MealsDatabase.class,
                            "MEALS_DATABASE"
                    ).build();
                }
            }
        }
        return instance;
    }
}