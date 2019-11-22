package com.example.infs3634app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.infs3634app.FragmentSwapper;
import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.fragments.BrowseRecipeCategoryFragment;
import com.example.infs3634app.fragments.FAQFragment;
import com.example.infs3634app.fragments.LeaderboardFragment;
import com.example.infs3634app.fragments.MyRecipesFragment;
import com.example.infs3634app.fragments.QuizRecyclerFragment;
import com.example.infs3634app.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements com.example.infs3634app.database.InsertUserDelegate{
    FragmentSwapper fs = new FragmentSwapper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase database = AppDatabase.getInstance(this);
        final View activityView = getWindow().getDecorView().findViewById(android.R.id.content);

        ImageView faqButton = findViewById(R.id.faqButton);
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQFragment faqFragment = new FAQFragment();
                fs.swapFragmentBackstack(faqFragment, v);
            }
        });

        ImageView leaderboardButton = findViewById(R.id.leaderboardButton);
        leaderboardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LeaderboardFragment leaderboardFragment = new LeaderboardFragment();
                fs.swapFragmentBackstack(leaderboardFragment,v);
            }
        });

        final BrowseRecipeCategoryFragment browseRecipeCategoryFragment = new BrowseRecipeCategoryFragment();
        fs.swapFragment(browseRecipeCategoryFragment, activityView);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.browseButton:
                        fs.swapFragment(browseRecipeCategoryFragment, activityView);
                        break;
                    case R.id.quizButton:
                        QuizRecyclerFragment quizFragment = new QuizRecyclerFragment();
                        fs.swapFragment(quizFragment, activityView);
                        break;
                    case R.id.myRecipes:
                        MyRecipesFragment myRecipesFragment = new MyRecipesFragment();
                        fs.swapFragment(myRecipesFragment, activityView);
                        break;
                    case R.id.youtubeButton:
                        Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void handleTaskResult(User user) {

    }

    public void onClickAddRecipe(View view) {
        Intent intent = new Intent(getApplicationContext(), NewRecipeActivity.class);
        startActivity(intent);
    }


}