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
import com.example.infs3634app.database.GetFavouritesAsyncTask;
import com.example.infs3634app.database.GetFavouritesDelegate;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksAdapter;
import com.example.infs3634app.model.ID;

import java.util.ArrayList;
import java.util.List;


public class FavouritesFragment extends Fragment implements GetFavouritesDelegate {
    public FavouritesFragment() {
        // Required empty public constructor
    }


    public static FavouritesFragment newInstance(String param1, String param2) {
        FavouritesFragment fragment = new FavouritesFragment();
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
        GetFavouritesAsyncTask getFavouritesAsyncTask = new GetFavouritesAsyncTask();
        getFavouritesAsyncTask.setDatabase(db);
        getFavouritesAsyncTask.setDelegate(this);
        getFavouritesAsyncTask.execute(ID.user_id);
        System.out.println("oncreateview");
        return inflater.inflate(R.layout.fragment_favourites, container, false);

    }
    public void onDestroy(){
        super.onDestroy();
        System.out.println("destroy");
    }
    public void onResume(){
        super.onResume();
        System.out.println("resume");
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
    }
    @Override
    public void handleTaskResult(List<Drinks> favDrinks) {
        RecyclerView myFavouritesRecycler = getView().findViewById(R.id.myFavouritesRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        myFavouritesRecycler.setLayoutManager(layoutManager);
        DrinksAdapter drinksAdapter = new DrinksAdapter((ArrayList<Drinks>)favDrinks);
        myFavouritesRecycler.setAdapter(drinksAdapter);
    }
}
