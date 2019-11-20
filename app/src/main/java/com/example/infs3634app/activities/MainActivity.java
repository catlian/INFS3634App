package com.example.infs3634app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.InsertUserAsyncTask;
import com.example.infs3634app.fragments.FavouritesFragment;
import com.example.infs3634app.fragments.MyCreatedRecipesFragment;
import com.example.infs3634app.fragments.MyRecipesFragment;
import com.example.infs3634app.fragments.QuizRecyclerFragment;

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
import com.example.infs3634app.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements RecipeRecyclerFragment.OnFragmentInteractionListener,
        RecipeDetailFragment.OnFragmentInteractionListener,
        BrowseRecipeCategoryFragment.OnFragmentInteractionListener,
        com.example.infs3634app.database.InsertUserDelegate,
        MyRecipesFragment.OnFragmentInteractionListener,
        FavouritesFragment.OnFragmentInteractionListener,
        MyCreatedRecipesFragment.OnFragmentInteractionListener
{
    public static User user = new User(1, "test", 0, 0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cheeky new user

        /*AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        InsertUserAsyncTask insertUserAsyncTask =  new InsertUserAsyncTask();
        insertUserAsyncTask.setDatabase(db);
        insertUserAsyncTask.setDelegate(this);
        insertUserAsyncTask.execute(user);*/

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

                        QuizRecyclerFragment quizFragment = new QuizRecyclerFragment();
                        FragmentManager fragmentManager2 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.fragment_slot, quizFragment);
                        fragmentTransaction2.commit();
                        break;
                    case R.id.myRecipes:
                        Toast.makeText(MainActivity.this, "My Recipes", Toast.LENGTH_SHORT).show();
                        MyRecipesFragment myRecipesFragment = new MyRecipesFragment();
                        FragmentManager fragmentManager3 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                        fragmentTransaction3.replace(R.id.fragment_slot, myRecipesFragment);
                        fragmentTransaction3.commit();
                        break;
                    case R.id.youtubeButton:
                        Toast.makeText(MainActivity.this,"Watch",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,YoutubeActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void handleTaskResult(User user) {

    }
    public void onClickAddRecipe(View view) {
        Intent intent = new Intent(getApplicationContext(), NewRecipeActivity.class);
        startActivity(intent);
    }
}

