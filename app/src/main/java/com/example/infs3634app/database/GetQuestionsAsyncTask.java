package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.Quiz;

import java.util.List;

public class GetQuestionsAsyncTask extends AsyncTask<Integer, Void, List<Question>> {
    private GetQuestionsDelegate delegate;
    private AppDatabase database;
    public void setDelegate(GetQuestionsDelegate delegate) {
        this.delegate = delegate;
    }
    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected List<Question> doInBackground(Integer...integers) {
        int id = integers[0].intValue();
        List<Question> questionList = database.questionDao().findByQuizId(id);
        return questionList;
    }

    @Override
    protected void onPostExecute(List<Question> questionList) {
        delegate.handleTaskResult(questionList);
    }
}
