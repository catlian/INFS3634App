package com.example.infs3634app.model;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.R;
import com.example.infs3634app.fragments.RecipeDetailFragment;
import com.example.infs3634app.fragments.RecipeRecyclerFragment;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    View v;
    ImageView categoryImage;
    TextView categoryName;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        v = itemView;
        categoryName = itemView.findViewById(R.id.categoryName);
        categoryImage = itemView.findViewById(R.id.categoryImage);

        categoryImage.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String catName = categoryName.getText().toString();
                bundle.putString("CATEGORY_NAME",catName);
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                RecipeRecyclerFragment recipeFragment = new RecipeRecyclerFragment();
                recipeFragment.setArguments(bundle);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_slot, recipeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }));
    }


}
