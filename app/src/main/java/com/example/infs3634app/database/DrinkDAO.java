package com.example.infs3634app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3634app.model.Drinks;
import com.example.infs3634app.model.User;

import java.util.List;

@Dao
public interface DrinkDAO {
    @Query("SELECT * FROM drinks")
    List<Drinks> getAllDrinks(User user);

    @Insert
    void insertNewDrink(Drinks...drinks);

}
