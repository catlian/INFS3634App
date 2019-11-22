package com.example.infs3634app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634app.R;
import com.example.infs3634app.model.DrinksAdapter;
import com.example.infs3634app.model.DrinksImport;
import com.google.gson.Gson;

public class RecipeRecyclerFragment extends Fragment {
    // parameters
    private String categoryName;
    private String type;

    public RecipeRecyclerFragment() {
        // Required empty public constructor
    }

    public static RecipeRecyclerFragment newInstance(String param1, String param2) {
        RecipeRecyclerFragment fragment = new RecipeRecyclerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryName = getArguments().getString("CATEGORY_NAME");
            type = getArguments().getString("CATEGORY_TYPE");
        }
        System.out.println(categoryName);
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState){
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                RecyclerView recipeRecycler = view.findViewById(R.id.recipeRecycler);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recipeRecycler.setLayoutManager(layoutManager);
                DrinksImport drinksImport=new Gson().fromJson(response,DrinksImport.class);
                DrinksAdapter drinksAdapter=new DrinksAdapter(drinksImport.getDrinks());
                recipeRecycler.setAdapter(drinksAdapter);
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                System.out.println("Request failed");
            }

        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c="+categoryName;
        if(type.equals("ingredient")){
            url="https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="+categoryName;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,responseListener,errorListener);
        requestQueue.add(stringRequest);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_recycler, container, false);
    }
}
