package com.example.infs3634app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634app.R;
import com.example.infs3634app.fragments.BrowseRecipeCategoryFragment;
import com.example.infs3634app.fragments.RecipeDetailFragment;
import com.example.infs3634app.fragments.RecipeRecyclerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements RecipeRecyclerFragment.OnFragmentInteractionListener, RecipeDetailFragment.OnFragmentInteractionListener, BrowseRecipeCategoryFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                BrowseRecipeCategoryFragment browseRecipeCategoryFragment = new BrowseRecipeCategoryFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.browseButton:
                        Toast.makeText(MainActivity.this, "Browse", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_slot, browseRecipeCategoryFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.quizButton:
                        Toast.makeText(MainActivity.this, "Quiz", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.myRecipes:
                        Toast.makeText(MainActivity.this, "My Recipes", Toast.LENGTH_SHORT).show();

                        break;
                }
                return true;
            }
        });
    }
    public void onClickBrowseRecipe(View view){
        BrowseRecipeCategoryFragment browseRecipeCategoryFragment = new BrowseRecipeCategoryFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, browseRecipeCategoryFragment);
        fragmentTransaction.commit();
        /*RecipeRecyclerFragment recipeFragment = new RecipeRecyclerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, recipeFragment);
        fragmentTransaction.commit();*/
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

