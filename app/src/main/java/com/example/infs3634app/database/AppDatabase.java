package com.example.infs3634app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.infs3634app.model.*;

@Database(entities = {Question.class, User.class, Quiz.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract QuestionDAO questionDao();
    public abstract UserDao userDao();
    public abstract QuizDAO quizDAO();


    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "quizDB")
                    .allowMainThreadQueries()
                    .build();
            instance.populateInitialData();
        }
        return instance;
    }
    /*https://github.com/android/architecture-components-samples/blob/master/PersistenceContentProviderSample/app/src/main/java/com/example/android/contentprovidersample/data/SampleDatabase.java
    code referenced from this link
     */
    private void populateInitialData() {
        if (quizDAO().count() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    InsertInitialDataAsyncTask insertInitialDataAsyncTask = new
                            InsertInitialDataAsyncTask();
                    insertInitialDataAsyncTask.setDatabase(instance);
                    insertInitialDataAsyncTask.execute();

                }
            });
        }
    }
}
