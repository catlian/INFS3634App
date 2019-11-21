package com.example.infs3634app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.InsertQuestionAsyncTask;
import com.example.infs3634app.database.InsertUserAsyncTask;
import com.example.infs3634app.fragments.FAQFragment;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634app.R;
import com.example.infs3634app.fragments.BrowseRecipeCategoryFragment;
import com.example.infs3634app.fragments.RecipeDetailFragment;
import com.example.infs3634app.fragments.RecipeRecyclerFragment;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements RecipeRecyclerFragment.OnFragmentInteractionListener,
        RecipeDetailFragment.OnFragmentInteractionListener,
        BrowseRecipeCategoryFragment.OnFragmentInteractionListener,
        com.example.infs3634app.database.InsertUserDelegate,
        MyRecipesFragment.OnFragmentInteractionListener,
        FavouritesFragment.OnFragmentInteractionListener,
        MyCreatedRecipesFragment.OnFragmentInteractionListener,
        FAQFragment.OnFragmentInteractionListener {
    int countLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase database = AppDatabase.getInstance(this);


        ImageView faqButton = findViewById(R.id.faqButton);
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                FragmentManager fragmentManager5 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction5 = fragmentManager5.beginTransaction();
                fragmentTransaction5.replace(R.id.fragment_slot, faqFragment);
                fragmentTransaction5.addToBackStack(null);
                fragmentTransaction5.commit();
            }
        });

        final BrowseRecipeCategoryFragment browseRecipeCategoryFragment = new BrowseRecipeCategoryFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionInitial = fragmentManager.beginTransaction();
        fragmentTransactionInitial.replace(R.id.fragment_slot, browseRecipeCategoryFragment);
        fragmentTransactionInitial.commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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
                        Toast.makeText(MainActivity.this, "Watch", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
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
/*
    public void onClickNumQuestions(View view) {
        createQuestions(5);
    }

    public void createQuestions(int numQuestions) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            int numOptions = 0;
            Question question = new Question();
            @Override
            public void onResponse(String response) {
                System.out.println("got response");
                //set answer, option1, option 2, etc.
                //create question
                //insert questions async task
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                ArrayList<Drinks> drinksList = drinksImport.getDrinks();
                Drinks selectedDrink = drinksList.get(0);
                switch(numOptions){
                    case 0: question.setAnswer(selectedDrink.getStrDrink());
                        String drinkImage = selectedDrink.getStrDrinkThumb();
                        question.setImageUrl(drinkImage);
                        break;
                    case 1: question.setOption2(selectedDrink.getStrDrink());
                        break;
                    case 2: question.setOption3(selectedDrink.getStrDrink());
                    case 3: question.setOption4(selectedDrink.getStrDrink());
                }



                question.setQuestion("What is this drink?");
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                InsertQuestionAsyncTask insertQuestionAsyncTask = new InsertQuestionAsyncTask();
                insertQuestionAsyncTask.setDatabase(db);
                insertQuestionAsyncTask.execute(question);
                numOptions++;
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed");
            }

        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);

        for (numOptions = 0; numOptions < 4; numOptions++) {
            requestQueue.add(stringRequest);
            System.out.println("added string request");
        }


    }

    private void generateRandomDrink() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //set answer, option1, option 2, etc.
                //create question
                //insert questions async task
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                ArrayList<Drinks> drinksList = drinksImport.getDrinks();
                Drinks selectedDrink = drinksList.get(0);
                option2 = selectedDrink.getStrDrink();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed");
            }

        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }*/
}

