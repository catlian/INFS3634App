package com.example.infs3634app.database;

import com.example.infs3634app.model.Question;

import java.util.List;

public interface GetQuestionsDelegate {
    void handleTaskResult(List<Question> questionList);
}
