package com.example.infs3634app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.infs3634app.model.*;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM questions")
    List<Question> getAll();

    @Insert
    void insertNew(Question...questions);

    @Query("SELECT * FROM questions WHERE quizId = :id")
    List<Question> findByQuizId(int id);

    @Query("SELECT COUNT(*) FROM questions")
    int count();
}
