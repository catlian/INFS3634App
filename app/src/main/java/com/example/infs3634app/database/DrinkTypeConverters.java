package com.example.infs3634app.database;

import androidx.room.TypeConverter;

import com.example.infs3634app.model.Drinks;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
/* This code is inspired by https://medium.com/@toddcookevt/android-room-storing-lists-of-objects-766cca57e3f9 */

public class DrinkTypeConverters {
    static Gson gson = new Gson();

    @TypeConverter
    public static String drinksToString (List<Drinks> drinksList){
        return gson.toJson(drinksList);
    }
    @TypeConverter
    public static List<Drinks> stringToDrinks (String json){
        if(json==null){
            return Collections.emptyList();
        }
        else{
            Type listType = new TypeToken<List<Drinks>>() {}.getType();

            return gson.fromJson(json, listType);
        }
    }
}
