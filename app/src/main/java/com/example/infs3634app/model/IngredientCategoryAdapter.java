package com.example.infs3634app.model;

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
        RequestQueue requestQueue = Volley.newRequestQueue(holder.v.getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                Glide.with(holder.categoryImage.getContext()).load(drinksImport.getDrinks().get(0).getStrDrinkThumb()).into(holder.categoryImage);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed"); }

        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="+categoryName;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }
}
