package com.example.infs3634app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634app.model.*;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Insert
    void insertNew(User...users);

    @Query("SELECT * FROM users WHERE userId = :id")
    User findById(int id);

    @Query("SELECT userId, favourites FROM users WHERE userId= :id")
    User getFavourites(int id);

    @Update
    void updateUsers(User...users);

}
