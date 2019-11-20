package com.example.infs3634app.database;

import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.User;

import java.util.List;

public interface QuizDelegate {
    void handleQuestionResult(List<Question> questionList);
    void handleUserResult(User user);
}
