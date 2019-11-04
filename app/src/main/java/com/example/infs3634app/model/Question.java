package com.example.infs3634app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey
    private int questionId;
    private String name;
    private String answer;
    private String option2;
    private String option3;
    private String option4;

    public Question(){};


    public Question(int questionId, String name, String answer, String option2, String option3, String option4) {
        this.questionId = questionId;
        this.name = name;
        this.answer = answer;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }
}
