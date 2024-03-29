package com.example.infs3634app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
/*
This controls fragment_recipe_recycler.xml, which is what you see after you click a category in Browse.
 */
public class RecipeRecyclerFragment extends Fragment {
    // parameters
    private String categoryName;
    private String type;

    private Button btnSearch;
    private TextView searchText;

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
    }

    //checks if the category type is ingredient as ingredient uses a different url to query the API.
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
//search configuration
        searchText = view.findViewById(R.id.searchText);

        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = searchText.getText().toString();
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
                String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+searchString;
                StringRequest stringRequest = new StringRequest(Request.Method.GET,url,responseListener,errorListener);
                requestQueue.add(stringRequest);
                hideSoftKeyboard();
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_recycler, container, false);
    }

    //code ref: https://stackoverflow.com/questions/4005728/hide-default-keyboard-on-click-in-android
    private void hideSoftKeyboard(){
        if(getActivity().getCurrentFocus()!=null && getActivity().getCurrentFocus() instanceof EditText){
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
        }
    }
}
