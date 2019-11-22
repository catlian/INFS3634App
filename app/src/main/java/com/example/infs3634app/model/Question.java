package com.example.infs3634app.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity (tableName = "Questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int questionId;
    private String imageUrl;
    @NonNull private String question;
    @NonNull private String answer;
    @NonNull private String option2;
    @NonNull private String option3;
    @NonNull private String option4;
    @NonNull private int quizId;

    @Ignore
    public Question(){}
    @Ignore
    public Question(int questionId, String question, String answer, String option2, String option3,
                    String option4, int quizId) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.quizId = quizId;
    }

    public Question(int questionId, String question, String imageUrl, String answer, String option2, String option3,
                    String option4, int quizId) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.quizId = quizId;
        this.imageUrl=imageUrl;
    }


    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
