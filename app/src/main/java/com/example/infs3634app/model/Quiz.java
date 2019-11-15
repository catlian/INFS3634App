package com.example.infs3634app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quizzes")
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    private int quizId;
    private String description;



    public Quiz(int quizId, String description) {
        this.quizId = quizId;
        this.description = description;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
