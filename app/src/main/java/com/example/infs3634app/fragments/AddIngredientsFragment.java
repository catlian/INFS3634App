package com.example.infs3634app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.infs3634app.R;
import com.example.infs3634app.activities.NewRecipeActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddIngredientsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddIngredientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddIngredientsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int countRows;
    private ArrayList<View> rows = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddIngredientsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddIngredientsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddIngredientsFragment newInstance(String param1, String param2) {
        AddIngredientsFragment fragment = new AddIngredientsFragment();
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
        countRows=1;
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState){
        final View row1 = view.findViewById(R.id.row1);
        final View row2 = view.findViewById(R.id.row2);
        final View row3 = view.findViewById(R.id.row3);
        final View row4 = view.findViewById(R.id.row4);
        final View row5 = view.findViewById(R.id.row5);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        for(View row:rows){
            row.setVisibility(View.GONE);
        }
        row1.setVisibility(View.VISIBLE);
        final ImageView addIngredientRow = view.findViewById(R.id.addIngredientRowButton);
        addIngredientRow.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v){
                countRows++;
                System.out.println("number of rows now: "+countRows);
                if(countRows<5){
                    rows.get(countRows-1).setVisibility(View.VISIBLE);
                }
                else{
                    rows.get(countRows-1).setVisibility(View.VISIBLE);
                    addIngredientRow.setVisibility(View.GONE);
                }
            };
        }));

        Button submitIngredients = view.findViewById(R.id.submitIngredients);
        final String[] ingredients = new String[5];
        final String[] qty = new String[5];
        final String[] measurement = new String[5];
        submitIngredients.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NewRecipeActivity activity = (NewRecipeActivity)getActivity();
                for(int i=0;i<rows.size();i++){
                    EditText ingInputEdit = (EditText)rows.get(i).findViewById(R.id.ingInput);
                    String ingInput = ingInputEdit.getText().toString();
                    String qtyInput = ((EditText)rows.get(i).findViewById(R.id.qtyInput)).getText().toString();
                    String measureInput = ((EditText)rows.get(i).findViewById(R.id.measurement)).getText().toString();
                    System.out.println(ingInput+" "+qtyInput+measureInput);
                    ingredients[i]=ingInput;
                    qty[i]=qtyInput;
                    measurement[i]=measureInput;
                }
                activity.addIngredientsToDrink(ingredients,qty,measurement);
                AddRecipeImageFragment addRecipeImageFragment = new AddRecipeImageFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.new_recipe_slot, addRecipeImageFragment);
                fragmentTransaction.commit();
            }
        }));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ingredients, container, false);
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
