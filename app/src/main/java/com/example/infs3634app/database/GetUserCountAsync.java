package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.List;

public class GetUserCountAsync extends AsyncTask<Void, Void, Integer> {
    private GetUserCountDelegate delegate;
    private AppDatabase database;

    public void setDelegate(GetUserCountDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected Integer doInBackground(Void...voids) {
        Integer i = database.userDao().count();
        return i;
    }
    @Override
    protected void onPostExecute(Integer i) {
        delegate.handleTaskResult(i);
    }
}
