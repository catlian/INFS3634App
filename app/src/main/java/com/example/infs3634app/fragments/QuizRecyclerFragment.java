package com.example.infs3634app.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634app.R;
import com.example.infs3634app.database.*;
import com.example.infs3634app.model.Quiz;
import com.example.infs3634app.model.QuizAdapter;

import java.util.List;

public class QuizRecyclerFragment extends Fragment implements GetQuizzesDelegate {
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;

    public QuizRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        quizAdapter = new QuizAdapter();

        AppDatabase database = AppDatabase.getInstance(getContext());

        GetQuizzesAsyncTask getQuizzesAsyncTask = new GetQuizzesAsyncTask();
        getQuizzesAsyncTask.setDatabase(database);
        getQuizzesAsyncTask.setDelegate(QuizRecyclerFragment.this);
        getQuizzesAsyncTask.execute();

        return view;
    }

    @Override
    public void handleTaskResult(List<Quiz> quizList) {
        quizAdapter.setData(quizList);
        recyclerView.setAdapter(quizAdapter);
    }
}
