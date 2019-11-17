package com.example.infs3634app.database;

import com.example.infs3634app.model.Quiz;

import java.util.List;

public interface GetQuizzesDelegate {
    void handleTaskResult(List<Quiz> quizList);
}
