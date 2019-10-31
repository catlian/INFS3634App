package com.example.infs3634app.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.R;

public class DrinksViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView drinkName;
    TextView category;
    TextView alcoholic;


    public DrinksViewHolder(@NonNull View v) {
        super(v);
        this.view = v;
        drinkName = v.findViewById(R.id.drinkName);
        category = v.findViewById(R.id.category);
        alcoholic = v.findViewById(R.id.alcoholic);
    }
}
