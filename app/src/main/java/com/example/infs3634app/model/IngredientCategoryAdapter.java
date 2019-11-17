package com.example.infs3634app.model;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.infs3634app.R;
import com.example.infs3634app.model.CategoryViewHolder;
import com.google.gson.Gson;

import java.util.ArrayList;

public class IngredientCategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    ArrayList<Drinks> listCategories;
    public IngredientCategoryAdapter (ArrayList<Drinks> categoryList){
        this.listCategories=categoryList;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_tiles, parent, false);

        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
        String categoryName = listCategories.get(position).getStrIngredient1();
        holder.categoryName.setText(categoryName);
        holder.categoryImage.setBackgroundColor(Color.GRAY);

    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }
}
