package com.example.infs3634app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.InsertDrinkAsyncTask;
import com.example.infs3634app.database.InsertDrinkDelegate;
import com.example.infs3634app.fragments.AddIngredientsFragment;
import com.example.infs3634app.fragments.AddMethodFragment;
import com.example.infs3634app.fragments.AddRecipeImageFragment;
import com.example.infs3634app.model.Drinks;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class NewRecipeActivity extends AppCompatActivity implements
        AddRecipeImageFragment.OnFragmentInteractionListener,
        AddIngredientsFragment.OnFragmentInteractionListener,
        AddMethodFragment.OnFragmentInteractionListener,
        InsertDrinkDelegate {
    private Drinks newDrink = new Drinks();

    public Drinks getNewDrink(){//TODO: think about how to display preview information?
        return newDrink;
    }

    public void onClickSubmitAll(View view){
        System.out.println(newDrink.getStrDrink());
        System.out.println(newDrink.getStrInstructions());
        InsertDrinkAsyncTask insertDrinkAsyncTask = new InsertDrinkAsyncTask();
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        insertDrinkAsyncTask.setDatabase(db);
        insertDrinkAsyncTask.setDelegate(this);
        insertDrinkAsyncTask.execute(newDrink);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        Intent intent = getIntent();
        AddRecipeImageFragment addRecipeImageFragment = new AddRecipeImageFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.new_recipe_slot, addRecipeImageFragment);
        fragmentTransaction.commit();
    }

    public void addNameImage(String name, String url){
        newDrink.setStrDrink(name);
        newDrink.setStrDrinkThumb(url);
    }
    public void addIngredientsToDrink(String[] ingredients, String[] qty, String[] measurements){
        newDrink.setStrIngredient1(ingredients[0]);
        newDrink.setStrMeasure1(qty[0]+measurements[0]);
        newDrink.setStrIngredient2(ingredients[1]);
        newDrink.setStrMeasure2(qty[1]+measurements[1]);
        newDrink.setStrIngredient3(ingredients[2]);
        newDrink.setStrMeasure3(qty[2]+measurements[2]);
        newDrink.setStrIngredient4(ingredients[3]);
        newDrink.setStrMeasure4(qty[3]+measurements[3]);
        newDrink.setStrIngredient5(ingredients[4]);
        newDrink.setStrMeasure5(qty[4]+measurements[4]);
        System.out.println(newDrink.getStrIngredient1()+newDrink.getStrMeasure1());
        System.out.println(newDrink.getStrIngredient5()+newDrink.getStrMeasure5());
    }

    public void addMethodToDrink(String method){
        newDrink.setStrInstructions(method);
        System.out.println(method);
    }
    public void onClickAddIngredients(View view){
        AddIngredientsFragment addIngredientsFragment = new AddIngredientsFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.new_recipe_slot, addIngredientsFragment);
        fragmentTransaction.commit();
    }
    public void onClickAddMethod(View view){
        AddMethodFragment addMethodFragment = new AddMethodFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.new_recipe_slot, addMethodFragment);
        fragmentTransaction.commit();
    }
    public void onClickLoadImage(View view){
        ImageView drinkImage = findViewById(R.id.newDrinkImage);
        EditText newDrinkName = (EditText)findViewById(R.id.newDrinkName);
        EditText newDrinkImageLink = (EditText)findViewById(R.id.imageURL);
        //String drinkName = newDrinkName.getText().toString();
        String drinkImageURL = newDrinkImageLink.getText().toString().replace("\\","");
        Glide.with(this).load(drinkImageURL).into(drinkImage);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void handleTaskResult(Drinks drink) {

    }
}
