package com.example.infs3634app.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634app.R;
import com.example.infs3634app.model.DrinksAdapter;
import com.example.infs3634app.model.DrinksImport;
import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.Quiz;
import com.example.infs3634app.model.User;
import com.google.gson.Gson;

import java.util.List;

public class InsertInitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
    private AppDatabase database;

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Quiz quiz = new Quiz(1, "test", "great quiz");
        Quiz quiz2 = new Quiz(2, "test2", "great quiz2");
        database.quizDAO().insertNew(quiz, quiz2);

        Question question = new Question(
                0, "what's the answer?",
                "https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/wpxpvu1439905379.jpg",
                "Margarita", "Black Russian", "Mojito-Cocktail", "Dry Martini", 2);
        Question que = new Question(0, "what's the answer?2", "1", "no", "no?", "ya", 2);
        database.questionDao().insertNew(question, que);

        User user = new User(1, "test", 0, 0);
        database.userDao().insertNew(user);
        return null;
    }


}
