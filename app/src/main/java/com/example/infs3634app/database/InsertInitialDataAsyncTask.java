package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.Quiz;
import com.example.infs3634app.model.User;

import java.util.List;

public class InsertInitialDataAsyncTask extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        public void setDatabase(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            User user = new User(1, "test", 0, 0);
            database.userDao().insertNew(user);
            Quiz quiz = new Quiz(1, "test", "great quiz");
            Quiz quiz2 = new Quiz(2, "test2", "great quiz2");
            database.quizDAO().insertNew(quiz, quiz2);

            Question question = new Question(0,"what's the answer?", "yes", "no", "no?", "ya", 2);
            Question que = new Question(0,"what's the answer?2", "1", "no", "no?", "ya", 2);
            database.questionDao().insertNew(question, que);
            return null;
        }
}
