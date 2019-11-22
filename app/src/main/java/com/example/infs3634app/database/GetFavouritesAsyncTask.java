package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.Quiz;
import com.example.infs3634app.model.User;

import java.util.List;

public class GetFavouritesAsyncTask extends AsyncTask<Integer, Integer, User> {
    private GetFavouritesDelegate delegate;
    private AppDatabase database;

    public void setDelegate(GetFavouritesDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected User doInBackground(Integer... integers) {
        User user = database.userDao().getFavourites(integers[0]);
        return user;
    }
    @Override
    protected void onPostExecute(User user) {
        System.out.println("user id: "+user.getUserId());
        List<Drinks> favDrinks = user.getFavourites();
        delegate.handleTaskResult(favDrinks);
    }
}
