package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Quiz;

import java.util.List;

public class GetQuizzesAsyncTask extends AsyncTask<Void, Void, List<Quiz>>{
        private GetQuizzesDelegate delegate;
        private AppDatabase database;
        public void setDelegate(GetQuizzesDelegate delegate) {
            this.delegate = delegate;
        }
        public void setDatabase(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected List<Quiz> doInBackground(Void...voids) {
            List<Quiz> quizList = database.quizDAO().getAll();
            return quizList;
        }

        @Override
        protected void onPostExecute(List<Quiz> quizList) {
            delegate.handleTaskResult(quizList);
        }
}
