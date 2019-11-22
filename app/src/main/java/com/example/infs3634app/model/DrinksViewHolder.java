package com.example.infs3634app.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.FragmentSwapper;
import com.example.infs3634app.R;
import com.example.infs3634app.activities.MainActivity;
import com.example.infs3634app.fragments.RecipeDetailFragment;
import com.example.infs3634app.fragments.RecipeRecyclerFragment;

public class DrinksViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView drinkName;
    TextView category;
    TextView alcoholic;
    ImageView drinkImage;
    ConstraintLayout previewLayout;
    TextView ing1;
    TextView ing2;
    TextView amt1;
    TextView amt2;
    String drinkID;
    Drinks selectedDrink;
    TextView glass;
    FragmentSwapper fs = new FragmentSwapper();

    public DrinksViewHolder(@NonNull View v) {
        super(v);
        this.view = v;
        drinkName = v.findViewById(R.id.drinkName);
        category = v.findViewById(R.id.previewCategory);
        alcoholic = v.findViewById(R.id.previewAlcoholic);
        drinkImage=v.findViewById(R.id.drinkImage);
        previewLayout=v.findViewById(R.id.previewLayout);
        ing1 = v.findViewById(R.id.ing1);
        ing2 = v.findViewById(R.id.ing2);
        amt1 = v.findViewById(R.id.amt1);
        amt2 = v.findViewById(R.id.amt2);


        previewLayout.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("DRINK_ID",drinkID);
                bundle.putInt("USER_DRINK_INT",getAdapterPosition());
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
                recipeDetailFragment.setArguments(bundle);
                fs.swapFragmentBackstack(recipeDetailFragment, v);
            }
        }));
    }

}
