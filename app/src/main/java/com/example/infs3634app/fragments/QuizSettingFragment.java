package com.example.infs3634app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634app.R;
import com.example.infs3634app.activities.QuizActivity;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.InsertQuestionAsyncTask;
import com.example.infs3634app.database.QuizDelegate;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.ID;
import com.example.infs3634app.model.Question;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizSettingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizSettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizSettingFragment extends Fragment implements QuizDelegate {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int quizID;
    private String quizDescriptionString;
    private String quizNameString;

    int numQuestionsCreated;
    Question question = new Question();
    ArrayList<Question> questionArrayList = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public QuizSettingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QuizSettingFragment newInstance(String param1, String param2) {
        QuizSettingFragment fragment = new QuizSettingFragment();
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
            quizID = getArguments().getInt("QUIZ_ID");
            quizNameString = getArguments().getString("QUIZ_NAME");
            quizDescriptionString = getArguments().getString("QUIZ_DESCRIPTION");
        }
        numQuestionsCreated = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceBundle) {
        TextView quizName = view.findViewById(R.id.quizName);
        TextView quizDescription = view.findViewById(R.id.quizDescription);
        quizName.setText(quizNameString);
        quizDescription.setText(quizDescriptionString);
        final TextView loadMsg = view.findViewById(R.id.loadMsg);
        loadMsg.setVisibility(View.GONE);
        Button startQuizButton = view.findViewById(R.id.startQuizButton);
        startQuizButton.setVisibility(View.GONE);
        final ProgressBar progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        final Spinner spinner = view.findViewById(R.id.numQuestionsSpinner);
        Button button = view.findViewById(R.id.selectNumQuestions);

        Integer[] numQuestions = new Integer[]{5, 10, 15, 20};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>
                (getContext(), android.R.layout.simple_spinner_item, numQuestions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numQuestions = (Integer.parseInt(String.valueOf(spinner.getSelectedItem())));
                System.out.println("numQUestions wanted: "+numQuestions);
                loadMsg.setVisibility(View.VISIBLE);
                loadMsg.setText("Please wait while we load your questions.");
                progressBar.setVisibility(View.VISIBLE);
                createQuestions(numQuestions);
            }
        });
    }
    //create question options
    //create questions

    public void createQuestions(final int numQuestions) {
        createQuestionAnswer(numQuestions);
    }

    private void createQuestionAnswer(final int numQuestions) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //set answer, option1, option 2, etc.
                //create question
                //insert questions async task
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                ArrayList<Drinks> drinksList = drinksImport.getDrinks();
                Drinks selectedDrink = drinksList.get(0);
                question.setAnswer(selectedDrink.getStrDrink());
                System.out.println("Answer at createQuestionAnswer: "+question.getAnswer());
                String drinkImage = selectedDrink.getStrDrinkThumb();
                question.setImageUrl(drinkImage);
                createOptions(numQuestions);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed");
            }
        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    int numOptions=1;
    private void createOptions(final int numQuestions) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response in options received");
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                ArrayList<Drinks> drinksList = drinksImport.getDrinks();
                Drinks selectedDrink = drinksList.get(0);
                switch (numOptions) {
                    case 1:
                        System.out.println("set option 2");
                        question.setOption2(selectedDrink.getStrDrink());
                        break;
                    case 2:
                        System.out.println("set option 3");
                        question.setOption3(selectedDrink.getStrDrink());
                        break;
                    case 3:
                        System.out.println("set option 4");
                        question.setOption4(selectedDrink.getStrDrink());
                        break;
                }

                if(numOptions>=4){
                    System.out.println("Question complete!");
                    question.setQuizId(quizID);
                    question.setQuestion("What is this drink?");
                    question.setQuestionId(0);
                    System.out.println("Answer: "+question.getAnswer());
                    questionArrayList.add(question);
                    question = new Question();
                    numOptions=1;
                    numQuestionsCreated++;
                    System.out.println("numquestionscreated= "+numQuestionsCreated);
                    if(numQuestionsCreated>=numQuestions){
                        Question[] arrayQuestions = new Question[numQuestions];
                        for(int i=0;i<arrayQuestions.length;i++){
                            arrayQuestions[i]=questionArrayList.get(i);
                        }
                        AppDatabase db = AppDatabase.getInstance(getContext());
                        InsertQuestionAsyncTask insertQuestionAsyncTask = new InsertQuestionAsyncTask();
                        insertQuestionAsyncTask.setDatabase(db);
                        insertQuestionAsyncTask.setDelegate(QuizSettingFragment.this);
                        insertQuestionAsyncTask.setQuizID(quizID);
                        insertQuestionAsyncTask.execute(arrayQuestions);
                    }
                    else{
                        createQuestionAnswer(numQuestions);
                    }
                }
                else{
                    numOptions++;
                    System.out.println("updated numOptions: "+numOptions);
                    createOptions(numQuestions);
                    System.out.println("calling to create options again");

                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Request failed");
            }
        };
        String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
        System.out.println("added string request");
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

    @Override
    public void handleQuestionResult(List<Question> questionList) {
        System.out.println("received final question list");
        Button button = getView().findViewById(R.id.startQuizButton);
        TextView loadMsg = getView().findViewById(R.id.loadMsg);
        loadMsg.setText("Your quiz is now ready!");
        ProgressBar progressBar = getView().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                intent.putExtra("quizId", quizID);
                startActivity(intent);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
