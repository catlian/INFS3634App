package com.example.infs3634app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infs3634app.R;

import java.util.ArrayList;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksViewHolder> {
    public ArrayList<Drinks> drinksArrayList;
    public DrinksAdapter(ArrayList<Drinks> drinksArrayList) {
        this.drinksArrayList=drinksArrayList;
    }

    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_preview, parent, false);

        DrinksViewHolder drinksViewHolder = new DrinksViewHolder(view);
        return drinksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder holder, int position) {
        holder.drinkName.setText(drinksArrayList.get(position).getStrDrink());
        holder.category.setText(drinksArrayList.get(position).getStrCategory());
        holder.alcoholic.setText(drinksArrayList.get(position).getStrAlcoholic());
        Glide.with(holder.drinkImage.getContext()).load(drinksArrayList.get(position).getStrDrinkThumb()).into(holder.drinkImage);
    }

    @Override
    public int getItemCount() {
        return drinksArrayList.size();
    }
}
