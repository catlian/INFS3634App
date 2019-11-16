package com.example.infs3634app.activities;

//used https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-5-quiz-activity as ref for some parts
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rbSelection;
    private RadioButton rbOne;
    private RadioButton rbTwo;
    private RadioButton rbThree;
    private RadioButton rbFour;
    private TextView txtScore;
    private TextView txtQuestion;
    private Button btnConfirm;

    private int questionCount;
    private int scoreCount;
    private String answer;
    private List<Question> questionList;
    private int questionListSize;
    private Question currentQuestion;
    private ArrayList<String> optionsList = new ArrayList<>();
    private boolean responded;
    private String selectedResponse;

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

        AppDatabase database = AppDatabase.getInstance(getApplicationContext());

        questionList = database.questionDao().findByQuizId(quizId); //will be passing quiz id via intent and using that
        Collections.shuffle(questionList);
        questionListSize = questionList.size();

        showNextQuestion();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!responded){
                    if(rbOne.isChecked() || rbTwo.isChecked() || rbThree.isChecked() || rbFour.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });

    }

    private void showNextQuestion(){
        if(questionCount < questionListSize){
            rbOne.setTextColor(Color.BLACK);
            rbTwo.setTextColor(Color.BLACK);
            rbThree.setTextColor(Color.BLACK);
            rbFour.setTextColor(Color.BLACK);
            radioGroup.clearCheck();

            currentQuestion = questionList.get(questionCount);
            answer = currentQuestion.getAnswer();
            txtQuestion.setText(currentQuestion.getQuestion());

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
            //show results screen - new xml? toast? dialogbos?
            //store points in user db
            Toast.makeText(this, "Score: "
                    + scoreCount, Toast.LENGTH_SHORT).show();
            saveResults();
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

        if(questionCount < questionListSize){
            btnConfirm.setText("Next");
        }else{
            btnConfirm.setText("Save Results");
        }

    }
    private void saveResults(){
        //save in user table

    }
    //insert results delegate response here - toast confirm save, finish?

}
