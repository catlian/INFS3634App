package com.example.infs3634app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.infs3634app.R;
import com.example.infs3634app.fragments.RecipeDetailFragment;
import com.example.infs3634app.fragments.RecipeRecyclerFragment;

public class MainActivity extends AppCompatActivity implements RecipeRecyclerFragment.OnFragmentInteractionListener, RecipeDetailFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*RecipeRecyclerFragment recipeFragment = new RecipeRecyclerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, recipeFragment);
        fragmentTransaction.commit();*/
    }
    public void onClickBrowseRecipe(View view){
        RecipeRecyclerFragment recipeFragment = new RecipeRecyclerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, recipeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

