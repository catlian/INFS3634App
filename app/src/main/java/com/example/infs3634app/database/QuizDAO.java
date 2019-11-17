package com.example.infs3634app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3634app.model.Question;
import com.example.infs3634app.model.Quiz;

import java.util.List;

@Dao
public interface QuizDAO {
    @Query("SELECT * FROM quizzes")
    List<Quiz> getAll();

    @Insert
    void insertNew(Quiz...quizzes);

    @Query("SELECT * FROM quizzes WHERE quizId = :id")
    Quiz findById(int id);
}
