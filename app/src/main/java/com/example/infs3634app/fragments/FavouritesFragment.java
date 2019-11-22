package com.example.infs3634app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private OnFragmentInteractionListener mListener;

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
        return inflater.inflate(R.layout.fragment_favourites, container, false);
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
    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
