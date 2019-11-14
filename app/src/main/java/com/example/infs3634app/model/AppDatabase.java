package com.example.infs3634app.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDAO questionDao();
    public abstract UserDao userDao();


    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "quizDB")
                    .allowMainThreadQueries()   // <== IMPORTANT TO NOTE:
                    //     This is NOT correct to do in a completed app.
                    //     Next week we will fix it, but for now this
                    //     line is necessary for the app to work.
                    //     This line will basically allow the database
                    //     queries to freeze the app.
                    .build();
        }
        return instance;
    }
}
