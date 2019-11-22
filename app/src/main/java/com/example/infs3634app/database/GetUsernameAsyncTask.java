package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.List;

public class GetUsernameAsyncTask extends AsyncTask<Integer, Void, User> {
    private GetUserDelegate delegate;
    private AppDatabase database;

    public void setDelegate(GetUserDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected User doInBackground(Integer... integers) {
        User user = database.userDao().getUsername(integers[0]);
        return user;
    }
    @Override
    protected void onPostExecute(User user) {
        delegate.handleUserResult(user);
    }
}
