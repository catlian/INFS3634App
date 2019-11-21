package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Question;

public class InsertQuestionAsyncTask extends AsyncTask<Question,Integer,Void> {
    private AppDatabase database;

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }
    @Override
    protected Void doInBackground(Question... questions) {
        database.questionDao().insertNew(questions[0]);
        return null;
    }
}
