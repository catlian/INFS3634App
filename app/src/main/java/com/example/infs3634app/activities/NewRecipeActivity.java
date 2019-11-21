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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.GetUserAsyncTask;
import com.example.infs3634app.database.GetUserDelegate;
import com.example.infs3634app.database.UpdateUserAsyncTask;
import com.example.infs3634app.database.UpdateUserDataDelegate;
import com.example.infs3634app.fragments.BrowseRecipeCategoryFragment;
import com.example.infs3634app.fragments.FavouritesFragment;
import com.example.infs3634app.fragments.MyCreatedRecipesFragment;
import com.example.infs3634app.fragments.MyRecipesFragment;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.ArrayList;

public class NewRecipeActivity extends AppCompatActivity implements
        UpdateUserDataDelegate,
        GetUserDelegate,
        FavouritesFragment.OnFragmentInteractionListener,
        MyCreatedRecipesFragment.OnFragmentInteractionListener,
        MyRecipesFragment.OnFragmentInteractionListener{
    private Drinks newDrink = new Drinks();
    private int countRows;
    private ArrayList<View> rows = new ArrayList<>();
    private int id;
    private AppDatabase database;

    public void onClickSubmitAll(View view) {
        EditText name = (EditText)view.findViewById(R.id.newDrinkName);
        if(name==null){
            Toast.makeText(this,"Drink must have name",Toast.LENGTH_LONG).show();
        }
        else{
            handleIngredients();
            handleNameImage();
            handleMethod();
            GetUserAsyncTask getUserAsyncTask = new GetUserAsyncTask();
            getUserAsyncTask.setDatabase(database);
            getUserAsyncTask.setDelegate(NewRecipeActivity.this);
            getUserAsyncTask.execute(id);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        Intent intent = getIntent();
        countRows=1;
        database = AppDatabase.getInstance(getApplicationContext());
        id = getResources().getInteger(R.integer.user_id);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final View row1 = findViewById(R.id.row1);
        final View row2 = findViewById(R.id.row2);
        final View row3 = findViewById(R.id.row3);
        final View row4 = findViewById(R.id.row4);
        final View row5 = findViewById(R.id.row5);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        for (View row : rows) {
            row.setVisibility(View.GONE);
        }
        row1.setVisibility(View.VISIBLE);
        final ImageView addIngredientRow = findViewById(R.id.addIngredientRowButton);
        addIngredientRow.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countRows++;
                System.out.println("number of rows now: " + countRows);
                if (countRows < 5) {
                    rows.get(countRows - 1).setVisibility(View.VISIBLE);
                } else {
                    rows.get(countRows - 1).setVisibility(View.VISIBLE);
                    addIngredientRow.setVisibility(View.GONE);
                }
            }

            ;
        }));

    }
    public void onClickShowImage(View view){
        ImageView drinkImage = findViewById(R.id.newDrinkImage);
        EditText newDrinkImageLink = (EditText)findViewById(R.id.imageURL);
        String drinkImageURL = newDrinkImageLink.getText().toString().replace("\\","");
        Glide.with(this).load(drinkImageURL).into(drinkImage);
    }
    public void handleNameImage(){
        ImageView drinkImage = findViewById(R.id.newDrinkImage);
        EditText newDrinkName = (EditText)findViewById(R.id.newDrinkName);
        EditText newDrinkImageLink = (EditText)findViewById(R.id.imageURL);
        String drinkName = newDrinkName.getText().toString();
        String drinkImageURL = newDrinkImageLink.getText().toString().replace("\\","");
        Glide.with(this).load(drinkImageURL).into(drinkImage);
        newDrink.setStrDrink(drinkName);
        newDrink.setStrDrinkThumb(drinkImageURL);
    }

    public void handleIngredients(){
        String[] ingredients = new String[5];
        String[] qty = new String[5];
        String[] measurement = new String[5];
        for (int i = 0; i < rows.size(); i++) {
            EditText ingInputEdit = (EditText)rows.get(i).findViewById(R.id.ingInput);
            String ingInput = ingInputEdit.getText().toString();
            String qtyInput = ((EditText) rows.get(i).findViewById(R.id.qtyInput)).getText().toString();
            String measureInput = ((EditText) rows.get(i).findViewById(R.id.measurement)).getText().toString();
            System.out.println(ingInput + " " + qtyInput + measureInput);
            ingredients[i] = ingInput;
            qty[i] = qtyInput;
            measurement[i] = measureInput;
        }
        newDrink.setStrIngredient1(ingredients[0]);
        newDrink.setStrMeasure1(qty[0] + measurement[0]);
        newDrink.setStrIngredient2(ingredients[1]);
        newDrink.setStrMeasure2(qty[1] + measurement[1]);
        newDrink.setStrIngredient3(ingredients[2]);
        newDrink.setStrMeasure3(qty[2] + measurement[2]);
        newDrink.setStrIngredient4(ingredients[3]);
        newDrink.setStrMeasure4(qty[3] + measurement[3]);
        newDrink.setStrIngredient5(ingredients[4]);
        newDrink.setStrMeasure5(qty[4] + measurement[4]);
    }
    public void handleMethod(){
        EditText methodInput = (EditText)findViewById(R.id.methodInput);
        String method = methodInput.getText().toString();
        newDrink.setStrInstructions(method);
    }

    @Override
    public void handleUserResult(User user) {
        user.addToMyRecipes(newDrink);

        UpdateUserAsyncTask updateUserAsyncTask = new UpdateUserAsyncTask();
        updateUserAsyncTask.setDatabase(database);
        updateUserAsyncTask.setDelegate((UpdateUserDataDelegate)this);
        updateUserAsyncTask.execute(user);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void handleTaskResult(String string) {
        finish();
    }
}
