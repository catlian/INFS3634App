package com.example.infs3634app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634app.R;
import com.example.infs3634app.model.CategoryAdapter;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.IngredientCategoryAdapter;
import com.google.gson.Gson;
/*
This fragment contains all the code for controlling fragment_browse_recipe_category.xml
Our API provided us the two separate queries to retrieve list of categories vs list of ingredients
which allowed us to include this function. Thank you CocktailDB! :)
 */
public class BrowseRecipeCategoryFragment extends Fragment {
    public BrowseRecipeCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setCategories(view);
        setIngredients(view);

    }
    //using Volley to retrieve list of ingredients and populate ingredients recycler view
    //used the DrinksImport instead of creating separate imports for ingredients/categories to make code more reusable.
    private void setIngredients(final View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RecyclerView ingredientRecycler = view.findViewById(R.id.ingredientRecycler);
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
                ingredientRecycler.setLayoutManager(layoutManager);
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                IngredientCategoryAdapter ingredientCategoryAdapter = new IngredientCategoryAdapter(drinksImport.getDrinks());
                ingredientRecycler.setAdapter(ingredientCategoryAdapter);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed"); }

        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    //using Volley to retrieve list of categories and populate categories recycler view
    private void setCategories(final View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RecyclerView categoryRecycler = view.findViewById(R.id.categoryRecycler);
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
                categoryRecycler.setLayoutManager(layoutManager);
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                CategoryAdapter categoryAdapter = new CategoryAdapter(drinksImport.getDrinks());
                categoryRecycler.setAdapter(categoryAdapter);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed"); }

        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse_recipe_category, container, false);
    }

}
