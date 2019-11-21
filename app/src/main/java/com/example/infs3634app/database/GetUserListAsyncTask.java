package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.User;

import java.util.List;

public class GetUserListAsyncTask extends AsyncTask<Void, Void, List<User>>{
        private GetUserListDelegate delegate;
        private AppDatabase database;
        public void setDelegate(GetUserListDelegate delegate) {
            this.delegate = delegate;
        }
        public void setDatabase(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected List<User> doInBackground(Void...voids) {
            List<User> userList = database.userDao().getLeaderboard();
            return userList;
        }

        @Override
        protected void onPostExecute(List<User> userList) {
            delegate.handleUserResult(userList);
        }
}
