package com.example.infs3634app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634app.R;
import com.example.infs3634app.model.CategoryAdapter;
import com.example.infs3634app.model.DrinksAdapter;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.IngredientCategoryAdapter;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BrowseRecipeCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BrowseRecipeCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowseRecipeCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BrowseRecipeCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrowseRecipeCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrowseRecipeCategoryFragment newInstance(String param1, String param2) {
        BrowseRecipeCategoryFragment fragment = new BrowseRecipeCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setCategories(view);
        setIngredients(view);

    }

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

    private void setCategories(final View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RecyclerView categoryRecycler = view.findViewById(R.id.categoryRecycler);
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
                categoryRecycler.setLayoutManager(layoutManager);
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                //TODO: make category import
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
