
package com.example.infs3634app.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.R;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private ArrayList<Question> questionsToAdapt;

    public void setData(ArrayList<Question> questionsToAdapt) {
        this.questionsToAdapt = questionsToAdapt;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_quiz, parent, false);

        QuestionViewHolder questionViewHolder = new QuestionViewHolder(view);
        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        final Question questionAtPosition = questionsToAdapt.get(position);

        // Contrast how I wrote this method with the method for ArticleAdapter. They both achieve
        // the same goal, but this way is cleaner. I defined my own method "bind" in the BookViewHolder
        // class, and all the assignment and setup is done in there instead.
        holder.bind(questionAtPosition);
    }

    @Override
    public int getItemCount() {
        return questionsToAdapt.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView txtResult;
        public TextView txtQuestion;
        public RadioButton option1;
        public RadioButton option2;
        public RadioButton option3;
        public RadioButton option4;
        public Button btnNext;


        // This constructor is used in onCreateViewHolder
        public QuestionViewHolder(View v) {
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
        public void bind(final Question question) {
            txtQuestion.setText("What?");
            option1.setText("1");
            option2.setText("2");
            option3.setText("3");
            option4.setText("4");
        }
    }
}