package com.example.infs3634app.database;

import android.os.AsyncTask;

import com.example.infs3634app.model.Drinks;

public class InsertDrinkAsyncTask extends AsyncTask<Drinks,Integer,Drinks> {
    private InsertDrinkDelegate delegate;
    private AppDatabase database;

    public void setDelegate(InsertDrinkDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected Drinks doInBackground(Drinks... drinks) {
        database.drinkDAO().insertNewDrink(drinks[0]);
        return drinks[0];
    }
    @Override
    protected void onPostExecute(Drinks drink){
        delegate.handleTaskResult(drink);
    }
}
