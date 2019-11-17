package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.List;

public class InsertUserAsyncTask extends AsyncTask<User,Integer, User> {
    private InsertUserDelegate delegate;
    private AppDatabase database;

    public void setDelegate(InsertUserDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }
    @Override
    protected User doInBackground(User... users) {
        database.userDao().insertNew(users);
        return users[0];
    }
    @Override
    protected void onPostExecute(User user) {
        delegate.handleTaskResult(user);
    }
}
