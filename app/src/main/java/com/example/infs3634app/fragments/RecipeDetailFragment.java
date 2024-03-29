package com.example.infs3634app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.GetFavouritesAsyncTask;
import com.example.infs3634app.database.GetFavouritesDelegate;
import com.example.infs3634app.database.GetMyRecipesAsyncTask;
import com.example.infs3634app.database.GetUserAsyncTask;
import com.example.infs3634app.database.GetUserDelegate;
import com.example.infs3634app.database.UpdateUserAsyncTask;
import com.example.infs3634app.database.UpdateUserDataDelegate;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.ID;
import com.example.infs3634app.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
This file controls fragment_recipe_detail.xml
 */
public class RecipeDetailFragment extends Fragment implements
        GetFavouritesDelegate, UpdateUserDataDelegate, GetUserDelegate {
    private Drinks selectedDrink;
    private AppDatabase database;
    private int id;
    private List<Drinks> favDrinks;
    private User user;

    //parameters
    private String drinkID;
    private int position;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance(String param1, String param2) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getInstance(getContext());
        id = ID.user_id;
        if (getArguments() != null) {
            drinkID = getArguments().getString("DRINK_ID");
            position = getArguments().getInt("USER_DRINK_INT");
        }
        /*
        checking if drinkID is null or not as results retrieved from the API will have a drinkID,
        whereas user generated drinks will not (which is bad coding practice, i know :( )
        future plans: add user generated drinks to API (Costs money to get API key), so that they
        have a drinkID too!
         */
        if(drinkID!=null){
            final RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            final Response.Listener<String> responseListener = new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    DrinksImport drinkImports = new Gson().fromJson(response,DrinksImport.class);
                    ArrayList<DrinksImport> drinkArrayList= new ArrayList<DrinksImport>(Arrays.asList(drinkImports));
                    selectedDrink = drinkArrayList.get(0).getDrinks().get(0);
                    setIngredients(selectedDrink);
                    setMethod(selectedDrink);
                    setTags(selectedDrink);
                    setLike();

                }
            };
            Response.ErrorListener errorListener=new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    System.out.println("Request failed");
                }

            };
            String url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i="+drinkID;
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url,responseListener,errorListener);
            requestQueue.add(stringRequest);
        }
        else{
            GetMyRecipesAsyncTask getMyRecipesAsyncTask = new GetMyRecipesAsyncTask();
            getMyRecipesAsyncTask.setDatabase(database);
            getMyRecipesAsyncTask.setDelegate(this);
            getMyRecipesAsyncTask.execute(id);
        }

    }

    /*
    Retrieves a list of favourited drinks from the Room database... see HandleUserResult for more info.
     */
    private void setLike() {
        final ImageView likeButton = getView().findViewById(R.id.likeButton);
        GetFavouritesAsyncTask getFavouritesAsyncTask = new GetFavouritesAsyncTask();
        getFavouritesAsyncTask.setDatabase(database);
        getFavouritesAsyncTask.setDelegate(this);
        getFavouritesAsyncTask.execute(id);

    }

    private void setTags(Drinks selectedDrink) {
        TextView alcoholic = getView().findViewById(R.id.alcoholic);
        TextView category = getView().findViewById(R.id.category);
        TextView tags = getView().findViewById(R.id.tags);
        TextView glass = getView().findViewById(R.id.glass);
        alcoholic.setText("Alcoholic: "+selectedDrink.getStrAlcoholic());
        category.setText("Category: "+selectedDrink.getStrCategory());
        glass.setText("Glass: "+selectedDrink.getStrGlass());
        if(selectedDrink.getStrTags()!=null){
            tags.setText("Tags: "+selectedDrink.getStrTags());
        }
        else{
            tags.setVisibility(View.GONE);
        }
        ImageView drinkImage = getView().findViewById(R.id.detailImage);
        Glide.with(drinkImage.getContext()).load(selectedDrink.getStrDrinkThumb()).into(drinkImage);
    }

    private void setMethod(Drinks selectedDrink) {
        TextView method = getView().findViewById(R.id.method);
        try {
            method.setText(selectedDrink.getStrInstructions());
        }
        catch(NullPointerException e){
            method.setText("No instructions available");
        }
    }

    private void setIngredients(Drinks selectedDrink) {

        TextView drinkName = getView().findViewById(R.id.drinkName);
        drinkName.setText(selectedDrink.getStrDrink());
        ArrayList<String> methodResult=new ArrayList<>();
        methodResult.add(selectedDrink.getStrIngredient1());
        methodResult.add(selectedDrink.getStrIngredient2());
        methodResult.add(selectedDrink.getStrIngredient3());
        methodResult.add(selectedDrink.getStrIngredient4());
        methodResult.add(selectedDrink.getStrIngredient5());
        methodResult.add(selectedDrink.getStrIngredient6());
        methodResult.add(selectedDrink.getStrIngredient7());
        methodResult.add(selectedDrink.getStrIngredient8());
        methodResult.add(selectedDrink.getStrIngredient9());
        methodResult.add(selectedDrink.getStrIngredient10());
        methodResult.add(selectedDrink.getStrIngredient11());
        methodResult.add(selectedDrink.getStrIngredient12());
        methodResult.add(selectedDrink.getStrIngredient13());
        methodResult.add(selectedDrink.getStrIngredient14());
        methodResult.add(selectedDrink.getStrIngredient15());


        ArrayList<String> amountResult = new ArrayList<>();
        amountResult.add(selectedDrink.getStrMeasure1());
        amountResult.add(selectedDrink.getStrMeasure2());
        amountResult.add(selectedDrink.getStrMeasure3());
        amountResult.add(selectedDrink.getStrMeasure4());
        amountResult.add(selectedDrink.getStrMeasure5());
        amountResult.add(selectedDrink.getStrMeasure6());
        amountResult.add(selectedDrink.getStrMeasure7());
        amountResult.add(selectedDrink.getStrMeasure8());
        amountResult.add(selectedDrink.getStrMeasure9());
        amountResult.add(selectedDrink.getStrMeasure10());
        amountResult.add(selectedDrink.getStrMeasure11());
        amountResult.add(selectedDrink.getStrMeasure12());
        amountResult.add(selectedDrink.getStrMeasure13());
        amountResult.add(selectedDrink.getStrMeasure14());
        amountResult.add(selectedDrink.getStrMeasure15());

        ArrayList<TextView> ingredients = new ArrayList<>();
        ingredients.add((TextView)getView().findViewById(R.id.ing1));
        ingredients.add((TextView)getView().findViewById(R.id.ing2));
        ingredients.add((TextView)getView().findViewById(R.id.ing3));
        ingredients.add((TextView)getView().findViewById(R.id.ing4));
        ingredients.add((TextView)getView().findViewById(R.id.ing5));
        ingredients.add((TextView)getView().findViewById(R.id.ing6));
        ingredients.add((TextView)getView().findViewById(R.id.ing7));
        ingredients.add((TextView)getView().findViewById(R.id.ing8));
        ingredients.add((TextView)getView().findViewById(R.id.ing9));
        ingredients.add((TextView)getView().findViewById(R.id.ing10));
        ingredients.add((TextView)getView().findViewById(R.id.ing11));
        ingredients.add((TextView)getView().findViewById(R.id.ing12));
        ingredients.add((TextView)getView().findViewById(R.id.ing13));
        ingredients.add((TextView)getView().findViewById(R.id.ing14));
        ingredients.add((TextView)getView().findViewById(R.id.ing15));

        ArrayList<TextView> amounts = new ArrayList<>();
        amounts.add((TextView)getView().findViewById(R.id.amt1));
        amounts.add((TextView)getView().findViewById(R.id.amt2));
        amounts.add((TextView)getView().findViewById(R.id.amt3));
        amounts.add((TextView)getView().findViewById(R.id.amt4));
        amounts.add((TextView)getView().findViewById(R.id.amt5));
        amounts.add((TextView)getView().findViewById(R.id.amt6));
        amounts.add((TextView)getView().findViewById(R.id.amt7));
        amounts.add((TextView)getView().findViewById(R.id.amt8));
        amounts.add((TextView)getView().findViewById(R.id.amt9));
        amounts.add((TextView)getView().findViewById(R.id.amt10));
        amounts.add((TextView)getView().findViewById(R.id.amt11));
        amounts.add((TextView)getView().findViewById(R.id.amt12));
        amounts.add((TextView)getView().findViewById(R.id.amt13));
        amounts.add((TextView)getView().findViewById(R.id.amt14));
        amounts.add((TextView)getView().findViewById(R.id.amt15));

        for(int i=0;i<ingredients.size();i++){
            checkNull(methodResult.get(i),amountResult.get(i),ingredients.get(i),amounts.get(i));
        }
    }

    private void checkNull(String result, String amountResult, TextView ingredient, TextView amount){
        if(result!=null){
            ingredient.setText(result);
            if(amountResult!=null){
                amount.setText(amountResult);
            }else{
                amount.setText("N/A");
            }

        }
        else{
            ingredient.setVisibility(View.GONE);
            amount.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    }

    @Override
    public void handleTaskResult(List<Drinks> favDrinks) {
        this.favDrinks = favDrinks;
        if (drinkID==null) {
            selectedDrink = favDrinks.get(position);
            setIngredients(selectedDrink);
            setMethod(selectedDrink);
            setTags(selectedDrink);
            getView().findViewById(R.id.likeButton).setVisibility(View.GONE);
        }
        else{
            GetUserAsyncTask getUserAsyncTask = new GetUserAsyncTask();
            getUserAsyncTask.setDatabase(database);
            getUserAsyncTask.setDelegate(RecipeDetailFragment.this);
            getUserAsyncTask.execute(id);
        }
    }

    /*
    Retrieves a user because the favourites list is an attribute of the User class.
    then sets the user's favourites in Java (user.setFavourites(favDrinks);
    before pushing the user update through the database (UpdateAsyncTask)
    In between is code for toggling the state of the like button.
     */
    @Override
    public void handleUserResult(final User user) {
        user.setFavourites(favDrinks);
        final ImageView likeButton = getView().findViewById(R.id.likeButton);
        if(favDrinks.size()>0){
            for(int i=0;i<favDrinks.size();i++) {
                if (drinkID.equals(favDrinks.get(i).getIdDrink())) {
                    likeButton.setImageResource(R.drawable.liked);
                    break;
                }
                else{
                    likeButton.setImageResource(R.drawable.like);
                }
            }
        }
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)getView().getContext();
                if(likeButton.getDrawable().getConstantState()==getResources().getDrawable(R.drawable.like).getConstantState()){
                    likeButton.setImageResource(R.drawable.liked);
                    user.addToFavourite(selectedDrink);
                }
                else{
                    likeButton.setImageResource(R.drawable.like);
                    for(int i=0;i<favDrinks.size();i++){
                        if(drinkID.equals(favDrinks.get(i).getIdDrink())){
                            user.deleteFromFavourite(i);
                        }
                    }
                }
                UpdateUserAsyncTask updateUserAsyncTask = new UpdateUserAsyncTask();
                updateUserAsyncTask.setDatabase(database);
                updateUserAsyncTask.setDelegate((RecipeDetailFragment.this));
                updateUserAsyncTask.execute(user);
            }
        });
    }
/*
After retrieving list of favourite drinks, check if they are already liked or not by calling setLike()
It kind of loops because after a favourites list is updated, the favourites button needs to be
updated to reflect the change. Hence why it calls setLike();

 */
    @Override
    public void handleTaskResult(String string) {
        setLike();
    }

}
