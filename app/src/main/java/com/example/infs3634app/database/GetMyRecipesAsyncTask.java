package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.List;

public class GetMyRecipesAsyncTask extends AsyncTask<Integer, Integer, List<Drinks>> {
    private GetFavouritesDelegate delegate;
    private AppDatabase database;

    public void setDelegate(GetFavouritesDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected List<Drinks> doInBackground(Integer... integers) {
        User updatedUser = database.userDao().getMyRecipes(integers[0]);
        List<Drinks> myRecipes = updatedUser.getMyRecipes();
        return myRecipes;
    }
    @Override
    protected void onPostExecute(List<Drinks> myRecipes){
        delegate.handleTaskResult(myRecipes);
    }
}
