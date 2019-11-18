package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.R;
import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.List;

public class InsertFavouritesAsyncTask extends AsyncTask<User,Integer,User> {
    private InsertFavouritesDelegate delegate;
    private AppDatabase database;

    public void setDelegate(InsertFavouritesDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected User doInBackground(User...users) {
        User user = users[0];
        database.userDao().updateUsers(user);
        return user;
    }
    @Override
    protected void onPostExecute (User user){
        delegate.handleTaskResult(user);
//        System.out.println("received details about "+user.getUsername());
//        List<Drinks> favDrinks = user.getFavourites();
//        delegate.handleTaskResult(favDrinks);
    }
}
