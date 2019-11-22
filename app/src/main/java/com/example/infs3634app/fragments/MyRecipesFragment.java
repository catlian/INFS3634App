package com.example.infs3634app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634app.R;
import com.example.infs3634app.activities.NewRecipeActivity;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.GetFavouritesAsyncTask;
import com.example.infs3634app.database.GetFavouritesDelegate;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksAdapter;
import com.example.infs3634app.model.TabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyRecipesFragment extends Fragment implements
        FavouritesFragment.OnFragmentInteractionListener,
        MyCreatedRecipesFragment.OnFragmentInteractionListener{
    private String mParam1;
    private String mParam2;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private OnFragmentInteractionListener mListener;

    public MyRecipesFragment() {
        // Required empty public constructor
    }

    public static MyRecipesFragment newInstance(String param1, String param2) {
        MyRecipesFragment fragment = new MyRecipesFragment();
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
        return inflater.inflate(R.layout.fragment_my_recipes, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void onResume() {

        super.onResume();
        viewPager = (ViewPager)getView().findViewById(R.id.viewPager);
        tabLayout = (TabLayout)getView().findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new FavouritesFragment(), "Favourites");
        adapter.addFragment(new MyCreatedRecipesFragment(), "My Recipes");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.notifyDataSetChanged();
    }

    public void onPause() {
        System.out.println("pause");
        super.onPause();
        int count = adapter.getCount();
        for (int i = count - 1; i >= 0; --i) {
            adapter.destroyItem(viewPager, i, adapter.getItem(i));
            tabLayout.removeTabAt(i);
        }
        tabLayout.invalidate();
        adapter.notifyDataSetChanged();
        System.out.println("pause");
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void onClickAddRecipe(View view){
        Intent intent = new Intent(getContext(),NewRecipeActivity.class);
        startActivity(intent);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
