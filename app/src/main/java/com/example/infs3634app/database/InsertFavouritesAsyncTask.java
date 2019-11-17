package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.List;

public class InsertFavouritesAsyncTask extends AsyncTask<User,Integer,List<Drinks>> {
    private GetFavouritesDelegate delegate;
    private AppDatabase database;

    public void setDelegate(GetFavouritesDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected List<Drinks> doInBackground(User...users) {
        database.userDao().updateUsers(users[0]);
        User user = database.userDao().getFavourites(users[0].getUserId());
        List<Drinks> favDrinks = user.getFavourites();
        return favDrinks;
    }
    @Override
    protected void onPostExecute (List<Drinks> favDrinks){
        delegate.handleTaskResult(favDrinks);
    }
}
