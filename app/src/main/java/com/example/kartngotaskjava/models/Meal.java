package com.example.kartngotaskjava.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "mealsTable", indices = {@Index(value = {"title"}, unique = true)})
public class Meal {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String mealTitle;
    @ColumnInfo(name = "price")
    private double mealPrice;
    @ColumnInfo(name = "image")
    private String mealImage;
    @ColumnInfo(name = "quantity")
    private int mealQuantity;

//    public Meal(){
//
//    }
    public Meal(String mealTitle, String mealImage, double mealPrice, int mealQuantity) {
        this.mealTitle = mealTitle;
        this.mealImage = mealImage;
        this.mealPrice = mealPrice;
        this.mealQuantity = mealQuantity;
    }

    // Full Constructor with id
//    public Meal(int id, String mealTitle, double mealPrice, String mealImage, int mealQuantity) {
//        this.id = id;
//        this.mealTitle = mealTitle;
//        this.mealPrice = mealPrice;
//        this.mealImage = mealImage;
//        this.mealQuantity = mealQuantity;
//    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public int getMealQuantity() {
        return mealQuantity;
    }

    public void setMealQuantity(int mealQuantity) {
        this.mealQuantity = mealQuantity;
    }
}
