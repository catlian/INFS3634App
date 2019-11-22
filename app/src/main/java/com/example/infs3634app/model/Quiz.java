package com.example.infs3634app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Quiz {
    private int quizId;
    private String name;
    private String category;
    private String description;



    public Quiz(int quizId, String name, String category, String description) {
        this.quizId = quizId;
        this.name = name;
        this.category = category;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
