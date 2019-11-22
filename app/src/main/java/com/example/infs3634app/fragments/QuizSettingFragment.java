package com.example.infs3634app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.infs3634app.model.CategoryAdapter;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.Question;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuizSettingFragment extends Fragment implements QuizDelegate {
    private int quizID;
    private String quizDescriptionString;
    private String quizNameString;
    private ArrayList<String> categoryArray;
    private Button btnSelect;
    private String selectedCategory;

    int numQuestionsCreated;
    Question question = new Question();
    ArrayList<Question> questionArrayList = new ArrayList<>();

    public QuizSettingFragment() {
        // Required empty public constructor
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
        categoryArray = new ArrayList<>();

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
        final Spinner categorySpinner = view.findViewById(R.id.spinner2);
        btnSelect = view.findViewById(R.id.selectNumQuestions);

        Integer[] numQuestions = new Integer[]{5, 10, 15, 20};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>
                (getContext(), android.R.layout.simple_spinner_item, numQuestions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RecyclerView categoryRecycler = getView().findViewById(R.id.categoryRecycler);
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                ArrayList<Drinks> result = new ArrayList<>(drinksImport.getDrinks());
                for (Drinks drinks : result){
                    categoryArray.add(drinks.getStrCategory());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_item, categoryArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorySpinner.setAdapter(adapter);

                btnSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int numQuestions = (Integer.parseInt(String.valueOf(spinner.getSelectedItem())));
                        selectedCategory = String.valueOf(categorySpinner.getSelectedItem());
                        System.out.println("numQuestions wanted: "+numQuestions);
                        System.out.println(selectedCategory);
                        loadMsg.setVisibility(View.VISIBLE);
                        loadMsg.setText("Please wait while we load your questions.");
                        progressBar.setVisibility(View.VISIBLE);
                        createQuestions(numQuestions, selectedCategory);
                    }
                });

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
    //create question options
    //create questions

    public void createQuestions(final int numQuestions, String selectedCategory) {
        createQuestionAnswer(numQuestions, selectedCategory);
    }

    private void createQuestionAnswer(final int numQuestions, String selectedCategory) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //set answer, option1, option 2, etc.
                //create question
                //insert questions async task
                DrinksImport drinksImport = new Gson().fromJson(response, DrinksImport.class);
                ArrayList<Drinks> drinksList = drinksImport.getDrinks();
                Collections.shuffle(drinksList);
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
        String url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=" + selectedCategory;
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
                Collections.shuffle(drinksList);
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
                        createQuestionAnswer(numQuestions, selectedCategory);
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
        String url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=" + selectedCategory;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
        System.out.println("added string request");
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
}
