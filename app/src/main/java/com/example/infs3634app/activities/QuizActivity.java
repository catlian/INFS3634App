package com.example.infs3634app.activities;

//used https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-5-quiz-activity as ref for some parts
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.GetQuestionsAsyncTask;
import com.example.infs3634app.database.GetUserAsyncTask;
import com.example.infs3634app.database.GetUserDelegate;
import com.example.infs3634app.database.QuizDelegate;
import com.example.infs3634app.database.UpdateUserAsyncTask;
import com.example.infs3634app.database.UpdateUserDataDelegate;
import com.example.infs3634app.model.ID;
import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements UpdateUserDataDelegate,
        GetUserDelegate, QuizDelegate {

    private RadioGroup radioGroup;
    private RadioButton rbOne;
    private RadioButton rbTwo;
    private RadioButton rbThree;
    private RadioButton rbFour;
    private TextView txtScore;
    private TextView txtQuestion;
    private Button btnConfirm;
    private ImageView image;

    private int questionCount;
    private int scoreCount;
    private String answer;
    private List<Question> questionList;
    private int questionListSize;
    private Question currentQuestion;
    private ArrayList<String> optionsList = new ArrayList<>();
    private boolean responded;
    private String selectedResponse;
    private User user;
    private int highScore;
    private int totalPoints;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();

        int quizId = intent.getIntExtra("quizId", 0);

        txtScore = findViewById(R.id.txtScore);
        radioGroup = findViewById(R.id.radioGroup);
        rbOne = findViewById(R.id.btnOption1);
        rbTwo = findViewById(R.id.btnOption2);
        rbThree = findViewById(R.id.btnOption3);
        rbFour = findViewById(R.id.btnOption4);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnConfirm = findViewById(R.id.btnConfirm);
        image = findViewById(R.id.imgQuestion);

        database = AppDatabase.getInstance(getApplicationContext());
        GetQuestionsAsyncTask getQuestionsAsyncTask = new GetQuestionsAsyncTask();
        getQuestionsAsyncTask.setDatabase(database);
        getQuestionsAsyncTask.setDelegate(QuizActivity.this);
        getQuestionsAsyncTask.execute(quizId);


    }

    private void showNextQuestion(){
        //if not last question reset option text colours and selected radio button
        if(questionCount < questionListSize){
            rbOne.setTextColor(Color.BLACK);
            rbTwo.setTextColor(Color.BLACK);
            rbThree.setTextColor(Color.BLACK);
            rbFour.setTextColor(Color.BLACK);
            radioGroup.clearCheck();

            currentQuestion = questionList.get(questionCount); //set current question to next question
            answer = currentQuestion.getAnswer(); //get answer of current question
            //load question data
            txtQuestion.setText(currentQuestion.getQuestion());
            if(currentQuestion.getImageUrl()!=null){
                String imgUrl = currentQuestion.getImageUrl().replace("\\","");
                Glide.with(this).load(imgUrl).into(image);
            }
            //get the four answer options
            getOptionsList();
            rbOne.setText(optionsList.get(0));
            rbTwo.setText(optionsList.get(1));
            rbThree.setText(optionsList.get(2));
            rbFour.setText(optionsList.get(3));

            questionCount++;
            responded = false;
            btnConfirm.setText("Confirm");
        }
        else{
            GetUserAsyncTask getUserAsyncTask = new GetUserAsyncTask();
            getUserAsyncTask.setDatabase(database);
            getUserAsyncTask.setDelegate(QuizActivity.this);
            int id = ID.user_id;
            getUserAsyncTask.execute(id);
        }
    }
    private List<String> getOptionsList(){
        //get 4 options via getters, put them in array, then shuffle
        if(optionsList!= null){
            optionsList.clear();
        }
        optionsList.add(answer);
        optionsList.add(currentQuestion.getOption2());
        optionsList.add(currentQuestion.getOption3());
        optionsList.add(currentQuestion.getOption4());

        Collections.shuffle(optionsList);
        return optionsList;
    }
    //method to check if the answer matches answer text and sets colour of answer options accordingly
    private void checkAnswer(){
        responded = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        selectedResponse = String.valueOf(rbSelected.getText());

        if(selectedResponse.equals(answer)){
            scoreCount++;
            txtScore.setText("Score: " + scoreCount);
        }

        rbOne.setTextColor(Color.RED);
        rbTwo.setTextColor(Color.RED);
        rbThree.setTextColor(Color.RED);
        rbFour.setTextColor(Color.RED);

        if(answer.equals(rbOne.getText())){
            rbOne.setTextColor(Color.GREEN);
        }else if(answer.equals(rbTwo.getText())){
            rbTwo.setTextColor(Color.GREEN);
        }else if(answer.equals(rbThree.getText())){
            rbThree.setTextColor(Color.GREEN);
        }else if(answer.equals(rbFour.getText())){
            rbFour.setTextColor(Color.GREEN);
        }

        //sets text of button to be next/save reuslts depending on whether that was last question
        if(questionCount < questionListSize){
            btnConfirm.setText("Next");
        }else{
            btnConfirm.setText("Save Results");
        }
    }

    @Override
    //gets list of questions and shuffles them for random order
    public void handleQuestionResult(List<Question> questionList) {
        this.questionList = questionList;
        Collections.shuffle(questionList);
        questionListSize = questionList.size();

        showNextQuestion();
        //checks if an answer was selected and if yes, checks if correct, show solution, go to next question
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!responded){
                    if(rbOne.isChecked() || rbTwo.isChecked() || rbThree.isChecked() || rbFour.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(QuizActivity.this, "Please select an answer",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });
    }
    @Override
    //gets user objects from db, checks for high score to display toast, save updated values
    public void handleUserResult(User user){
        this.user = user;
        highScore = user.getHighScore();
        totalPoints = user.getTotalPoints();

        if(scoreCount > highScore){
            user.setHighScore(scoreCount);
            Toast.makeText(QuizActivity.this, "Congrats that was a new high score!",
                    Toast.LENGTH_SHORT).show();
        }
        totalPoints +=scoreCount;
        user.setTotalPoints(totalPoints);

        UpdateUserAsyncTask updateUserAsyncTask = new UpdateUserAsyncTask();
        updateUserAsyncTask.setDatabase(database);
        updateUserAsyncTask.setDelegate(QuizActivity.this);
        updateUserAsyncTask.execute(user);
    }

    @Override
    //display save success toast and finish activity
    public void handleTaskResult(String string) {
        final String message = string;
        //slight delay to minimise toast overlap when high score congrats message displays
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(QuizActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }
        }, 1500);

    }
}
