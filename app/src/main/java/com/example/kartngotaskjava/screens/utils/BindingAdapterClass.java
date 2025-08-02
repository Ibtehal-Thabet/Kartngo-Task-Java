package com.example.kartngotaskjava.screens.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.kartngotaskjava.R;

public class BindingAdapterClass {

    @BindingAdapter({"app:imageFromName"})
    public static void loadImageFromName(ImageView imageView, String imageName) {
        if (imageName != null && !imageName.isEmpty()) {
            int resId = imageView.getContext()
                    .getResources()
                    .getIdentifier(imageName, "drawable", imageView.getContext().getPackageName());

            if (resId != 0) {
                imageView.setImageResource(resId);
            } else {
                imageView.setImageResource(R.drawable.placeholder);
            }
        } else {
            imageView.setImageResource(R.drawable.placeholder);
        }
    }
}