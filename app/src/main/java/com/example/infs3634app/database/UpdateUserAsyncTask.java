package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.User;

public class UpdateUserAsyncTask extends AsyncTask<User,Integer,String> {
    private UpdateUserDataDelegate delegate;
    private AppDatabase database;

    public void setDelegate(UpdateUserDataDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected String doInBackground(User...users) {
        User user = users[0];
        database.userDao().updateUsers(user);
        String string = "Saved successfully.";
        return string;
    }
    @Override
    protected void onPostExecute (String string){
        delegate.handleTaskResult(string);
    }
}
