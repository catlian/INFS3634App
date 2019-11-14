package com.example.infs3634app.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM questions")
    List<Question> getAll();

    @Insert
    void insertNew(Question...questions);

    @Query("SELECT * FROM questions WHERE QuestionId = :id")
    Question findById(int id);
}
