package com.example.infs3634app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634app.R;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksImport;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String drinkID;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeDetailFragment newInstance(String param1, String param2) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
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
            drinkID = getArguments().getString("DRINK_ID");
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        final RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        final Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                DrinksImport drinkImports = new Gson().fromJson(response,DrinksImport.class);
                ArrayList<DrinksImport> drinkArrayList= new ArrayList<DrinksImport>(Arrays.asList(drinkImports));
                Drinks selectedDrink = drinkArrayList.get(0).getDrinks().get(0);
                setIngredients(selectedDrink);
                setMethod(selectedDrink);
                setTags(selectedDrink);
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                System.out.println("Request failed");
            }

        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i="+drinkID;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,responseListener,errorListener);
        requestQueue.add(stringRequest);
    }

    private void setTags(Drinks selectedDrink) {
        TextView alcoholic = getView().findViewById(R.id.alcoholic);
        TextView category = getView().findViewById(R.id.category);
        alcoholic.setText(selectedDrink.getStrAlcoholic());
        category.setText(selectedDrink.getStrCategory());
    }

    private void setMethod(Drinks selectedDrink) {
        TextView method = getView().findViewById(R.id.method);
        try {
            method.setText(selectedDrink.getStrInstructions());
        }
        catch(NullPointerException e){
            method.setText("No instructions available");
        }
    }

    private void setIngredients(Drinks selectedDrink) {

        TextView drinkName = getView().findViewById(R.id.drinkName);
        drinkName.setText(selectedDrink.getStrDrink());
        ArrayList<String> methodResult=new ArrayList<>();
        methodResult.add(selectedDrink.getStrIngredient1());
        methodResult.add(selectedDrink.getStrIngredient2());
        methodResult.add(selectedDrink.getStrIngredient3());
        methodResult.add(selectedDrink.getStrIngredient4());
        methodResult.add(selectedDrink.getStrIngredient5());
        methodResult.add(selectedDrink.getStrIngredient6());
        methodResult.add(selectedDrink.getStrIngredient7());
        methodResult.add(selectedDrink.getStrIngredient8());
        methodResult.add(selectedDrink.getStrIngredient9());
        methodResult.add(selectedDrink.getStrIngredient10());
        methodResult.add(selectedDrink.getStrIngredient11());
        methodResult.add(selectedDrink.getStrIngredient12());
        methodResult.add(selectedDrink.getStrIngredient13());
        methodResult.add(selectedDrink.getStrIngredient14());
        methodResult.add(selectedDrink.getStrIngredient15());


        ArrayList<String> amountResult = new ArrayList<>();
        amountResult.add(selectedDrink.getStrMeasure1());
        amountResult.add(selectedDrink.getStrMeasure2());
        amountResult.add(selectedDrink.getStrMeasure3());
        amountResult.add(selectedDrink.getStrMeasure4());
        amountResult.add(selectedDrink.getStrMeasure5());
        amountResult.add(selectedDrink.getStrMeasure6());
        amountResult.add(selectedDrink.getStrMeasure7());
        amountResult.add(selectedDrink.getStrMeasure8());
        amountResult.add(selectedDrink.getStrMeasure9());
        amountResult.add(selectedDrink.getStrMeasure10());
        amountResult.add(selectedDrink.getStrMeasure11());
        amountResult.add(selectedDrink.getStrMeasure12());
        amountResult.add(selectedDrink.getStrMeasure13());
        amountResult.add(selectedDrink.getStrMeasure14());
        amountResult.add(selectedDrink.getStrMeasure15());

        ArrayList<TextView> ingredients = new ArrayList<>();
        ingredients.add((TextView)getView().findViewById(R.id.ing1));
        ingredients.add((TextView)getView().findViewById(R.id.ing2));
        ingredients.add((TextView)getView().findViewById(R.id.ing3));
        ingredients.add((TextView)getView().findViewById(R.id.ing4));
        ingredients.add((TextView)getView().findViewById(R.id.ing5));
        ingredients.add((TextView)getView().findViewById(R.id.ing6));
        ingredients.add((TextView)getView().findViewById(R.id.ing7));
        ingredients.add((TextView)getView().findViewById(R.id.ing8));
        ingredients.add((TextView)getView().findViewById(R.id.ing9));
        ingredients.add((TextView)getView().findViewById(R.id.ing10));
        ingredients.add((TextView)getView().findViewById(R.id.ing11));
        ingredients.add((TextView)getView().findViewById(R.id.ing12));
        ingredients.add((TextView)getView().findViewById(R.id.ing13));
        ingredients.add((TextView)getView().findViewById(R.id.ing14));
        ingredients.add((TextView)getView().findViewById(R.id.ing15));

        ArrayList<TextView> amounts = new ArrayList<>();
        amounts.add((TextView)getView().findViewById(R.id.amt1));
        amounts.add((TextView)getView().findViewById(R.id.amt2));
        amounts.add((TextView)getView().findViewById(R.id.amt3));
        amounts.add((TextView)getView().findViewById(R.id.amt4));
        amounts.add((TextView)getView().findViewById(R.id.amt5));
        amounts.add((TextView)getView().findViewById(R.id.amt6));
        amounts.add((TextView)getView().findViewById(R.id.amt7));
        amounts.add((TextView)getView().findViewById(R.id.amt8));
        amounts.add((TextView)getView().findViewById(R.id.amt9));
        amounts.add((TextView)getView().findViewById(R.id.amt10));
        amounts.add((TextView)getView().findViewById(R.id.amt11));
        amounts.add((TextView)getView().findViewById(R.id.amt12));
        amounts.add((TextView)getView().findViewById(R.id.amt13));
        amounts.add((TextView)getView().findViewById(R.id.amt14));
        amounts.add((TextView)getView().findViewById(R.id.amt15));

        for(int i=0;i<ingredients.size();i++){
            checkNull(methodResult.get(i),amountResult.get(i),ingredients.get(i),amounts.get(i));
        }
    }

    private void checkNull(String result, String amountResult, TextView ingredient, TextView amount){
        if(result!=null){
            ingredient.setText(result);
            amount.setText(amountResult);
        }
        else{
            ingredient.setVisibility(View.GONE);
            amount.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
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
