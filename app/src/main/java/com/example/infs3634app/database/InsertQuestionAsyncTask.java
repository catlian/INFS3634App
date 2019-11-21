package com.example.infs3634app.database;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.infs3634app.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertQuestionAsyncTask extends AsyncTask<Question,Integer,Void> {
    private AppDatabase database;
    private QuizDelegate delegate;
    private List<Question> questionsList;
    private int quizID;

    public void setQuizID(int quizID){
        this.quizID=quizID;
    }
    public void setDelegate(QuizDelegate delegate){
        this.delegate=delegate;
    }
    public void setDatabase(AppDatabase database) {
        this.database = database;
    }
    @Override
    protected Void doInBackground(Question... questions) {
        database.questionDao().deleteQuestionsForQuiz(quizID);
        database.questionDao().insertNew(questions);
        questionsList=Arrays.asList(questions);
        return null;
    }
    @Override
    protected void onPostExecute(Void a){
        delegate.handleQuestionResult(questionsList);
    }
}
