package com.example.infs3634app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.InsertUserAsyncTask;
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
import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.Quiz;
import com.example.infs3634app.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements RecipeRecyclerFragment.OnFragmentInteractionListener,
        RecipeDetailFragment.OnFragmentInteractionListener,
        BrowseRecipeCategoryFragment.OnFragmentInteractionListener,
        com.example.infs3634app.database.InsertUserDelegate
{
    public static User user = new User(1,"tester","0");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cheeky new user
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        InsertUserAsyncTask insertUserAsyncTask =  new InsertUserAsyncTask();
        insertUserAsyncTask.setDatabase(db);
        insertUserAsyncTask.setDelegate(this);
        insertUserAsyncTask.execute(user);

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

                        AppDatabase database = AppDatabase.getInstance(getApplicationContext());
                        /*Quiz quiz = new Quiz(1, "test", "great quiz");
                        Quiz quiz2 = new Quiz(2, "test2", "great quiz2");
                        database.quizDAO().insertNew(quiz, quiz2);

                        //adding test questions
                        Question question = new Question(0,"what's the answer?", "yes", "no", "no?", "ya", 2);
                        Question que = new Question(0,"what's the answer?2", "1", "no", "no?", "ya", 2);
                        database.questionDao().insertNew(question, que);*/

                        QuizRecyclerFragment quizFragment = new QuizRecyclerFragment();
                        FragmentManager fragmentManager2 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.fragment_slot, quizFragment);
                        fragmentTransaction2.commit();
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

    @Override
    public void handleTaskResult(User user) {

    }
}

