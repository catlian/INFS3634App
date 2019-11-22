package com.example.infs3634app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.GetFavouritesDelegate;
import com.example.infs3634app.database.GetMyRecipesAsyncTask;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksAdapter;
import com.example.infs3634app.model.ID;

import java.util.ArrayList;
import java.util.List;


public class MyCreatedRecipesFragment extends Fragment
implements GetFavouritesDelegate {


    public MyCreatedRecipesFragment() {
        // Required empty public constructor
    }

    public static MyCreatedRecipesFragment newInstance(String param1, String param2) {
        MyCreatedRecipesFragment fragment = new MyCreatedRecipesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppDatabase db = AppDatabase.getInstance(getContext());
        GetMyRecipesAsyncTask getMyRecipesAsyncTask = new GetMyRecipesAsyncTask();
        getMyRecipesAsyncTask.setDatabase(db);
        getMyRecipesAsyncTask.setDelegate(this);
        getMyRecipesAsyncTask.execute(ID.user_id);
        return inflater.inflate(R.layout.fragment_my_created_recipes, container, false);
    }

    @Override
    public void handleTaskResult(List<Drinks> myRecipes) {
        RecyclerView myRecipeRecycler = getView().findViewById(R.id.myRecipesRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        myRecipeRecycler.setLayoutManager(layoutManager);
        DrinksAdapter drinksAdapter = new DrinksAdapter((ArrayList<Drinks>)myRecipes);
        myRecipeRecycler.setAdapter(drinksAdapter);
    }
}
