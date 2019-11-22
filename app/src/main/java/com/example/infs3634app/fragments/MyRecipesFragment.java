package com.example.infs3634app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.infs3634app.R;
import com.example.infs3634app.activities.NewRecipeActivity;
import com.example.infs3634app.model.TabAdapter;
import com.google.android.material.tabs.TabLayout;

public class MyRecipesFragment extends Fragment{
    private String mParam1;
    private String mParam2;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        super.onPause();
        int count = adapter.getCount();
        for (int i =0;i<1;i++) {
            adapter.destroyItem(viewPager, i, adapter.getItem(i));
            tabLayout.removeTabAt(i);
        }
        tabLayout.invalidate();
        adapter.notifyDataSetChanged();
    }

    public void onClickAddRecipe(View view){
        Intent intent = new Intent(getContext(),NewRecipeActivity.class);
        startActivity(intent);
    }

}
