package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Quiz;
import com.example.infs3634app.model.User;

import java.util.List;

public class GetUserAsyncTask extends AsyncTask<Integer, Void, User>{
        private GetUserDelegate delegate;
        private AppDatabase database;
        public void setDelegate(GetUserDelegate delegate) {
            this.delegate = delegate;
        }
        public void setDatabase(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected User doInBackground(Integer...integers) {
            int id = integers[0];
            User user = database.userDao().findById(id);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            delegate.handleUserResult(user);
        }
}
