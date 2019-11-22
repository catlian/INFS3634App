package com.example.infs3634app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.infs3634app.model.*;

@Database(entities = {Question.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase implements GetUserCountDelegate{
    public abstract QuestionDAO questionDao();
    public abstract UserDao userDao();


    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "quizDB")
                    .build();
            instance.populateInitialData();
        }
        return instance;
    }
    private void populateInitialData() {
        GetUserCountAsync getUserCountAsync = new
                GetUserCountAsync();
        getUserCountAsync.setDatabase(instance);
        getUserCountAsync.setDelegate(AppDatabase.this);
        getUserCountAsync.execute();

    }
    //if there are currently no users in the user table insert initial data
    @Override
    public void handleTaskResult(Integer i){
        if (i == 0) {
            InsertInitialDataAsyncTask insertInitialDataAsyncTask = new InsertInitialDataAsyncTask();
            insertInitialDataAsyncTask.setDatabase(instance);
            insertInitialDataAsyncTask.execute();
        }

    }
}
