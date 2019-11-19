package com.example.infs3634app.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.infs3634app.database.DrinkTypeConverters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "users")
@TypeConverters(DrinkTypeConverters.class)
public class User {
    @PrimaryKey (autoGenerate = true)
    private int userId;
    private String username;
    private Integer totalPoints;
    private Integer highScore;
    private List<Drinks> favourites;

    @Ignore
    public User(int userId, String username, Integer totalPoints, Integer highScore) {
        this.userId = userId;
        this.username = username;
        this.totalPoints = totalPoints;
        this.highScore = highScore;
    }

    public User(int userId, List<Drinks> favourites){
        this.userId = userId;
        this.favourites = favourites;
    }
    @Ignore
    public User(int userId, String username){
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<Drinks> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Drinks> favourites) {
        this.favourites = favourites;
    }

    public void addToFavourite(Drinks selectedDrink){
        favourites.add(selectedDrink);
    }

    public void deleteFromFavourite (int index){
        favourites.remove(index);
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }
}
