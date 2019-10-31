package com.example.infs3634app.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.R;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private ArrayList<Quiz> quizzesToAdapt;

    public void setData(ArrayList<Quiz> quizzesToAdapt) {
        this.quizzesToAdapt = quizzesToAdapt;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_quiz, parent, false);

        QuizViewHolder quizViewHolder = new QuizViewHolder(view);
        return quizViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        final Quiz quizAtPosition = quizzesToAdapt.get(position);

        // Contrast how I wrote this method with the method for ArticleAdapter. They both achieve
        // the same goal, but this way is cleaner. I defined my own method "bind" in the BookViewHolder
        // class, and all the assignment and setup is done in there instead.
        holder.bind(quizAtPosition);
    }

    @Override
    public int getItemCount() {
        return quizzesToAdapt.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView txtResult;
        public TextView txtQuestion;
        public RadioButton option1;
        public RadioButton option2;
        public RadioButton option3;
        public RadioButton option4;
        public Button btnNext;


        // This constructor is used in onCreateViewHolder
        public QuizViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            txtResult = v.findViewById(R.id.txtResult);
            txtQuestion = v.findViewById(R.id.txtQuestion);
            option1 = v.findViewById(R.id.btnOption1);
            option2= v.findViewById(R.id.btnOption2);
            option3 = v.findViewById(R.id.btnOption3);
            option4 = v.findViewById(R.id.btnOption4);

        }

        // See comment in onBindViewHolder
        public void bind(final Quiz quiz) {
            txtQuestion.setText("What?");
            option1.setText("1");
            option2.setText("2");
            option3.setText("3");
            option4.setText("4");
        }
    }
}
