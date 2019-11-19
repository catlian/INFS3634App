package com.example.infs3634app.database;

import android.os.AsyncTask;

public class InsertResultsAsyncTask extends AsyncTask<Void, Void, Void>{
        private UpdateUserDataDelegate delegate;
        private AppDatabase database;
        public void setDelegate(UpdateUserDataDelegate delegate) {
            this.delegate = delegate;
        }
        public void setDatabase(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void...voids) {

            database.userDao().updateUsers();

            return null;
        }
}
