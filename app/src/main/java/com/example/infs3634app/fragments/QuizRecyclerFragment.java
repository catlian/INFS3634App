package com.example.infs3634app.fragments;


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
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.Quiz;
import com.example.infs3634app.model.QuizAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class QuizRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private ArrayList<String> categoryArray;
    private String selectedCategory;
    private ArrayList<Quiz> quizList;


    public QuizRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        quizAdapter = new QuizAdapter();
        quizList = new ArrayList<>();
        categoryArray = new ArrayList<>();

        //api call for the list of category types, removing the ones with small amounts of drinks
        //or have same image for drinks
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                ArrayList<Drinks> result = new ArrayList<>(drinksImport.getDrinks());
                for (Drinks drinks : result) {
                    categoryArray.add(drinks.getStrCategory());
                }
                categoryArray.remove("Cocoa");
                categoryArray.remove("Homemade Liqueur");
                categoryArray.remove("Soft Drink \\/ Soda");

                //creates quizzes for each category, adds this to quiz array to set recyclerview data
                for(int i=0 ; i<categoryArray.size() ; i++){
                    String category = categoryArray.get(i);
                    Quiz quiz = new Quiz(i,getResources().getString(R.string.name_the) + " "
                            + category, category, getResources().getString(R.string.quiz_description)
                            + " " + category + " " + getResources().getString(R.string.cat));
                    quizList.add(quiz);
                    i+=1;
                }

                quizAdapter.setData(quizList);
                recyclerView.setAdapter(quizAdapter);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed");
            }};
        String url = "https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
        return view;
    }
}
