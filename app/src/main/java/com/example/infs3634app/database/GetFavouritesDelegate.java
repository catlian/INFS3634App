package com.example.infs3634app.database;

import com.example.infs3634app.model.Drinks;

import java.util.List;

public interface GetFavouritesDelegate{
    void handleTaskResult(List<Drinks> favDrinks);
}
