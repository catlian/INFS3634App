package com.example.infs3634app.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.R;
import com.example.infs3634app.activities.QuizActivity;
import com.example.infs3634app.activities.YoutubeActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
        private List<Quiz> quizzesToAdapt;

        public void setData(List<Quiz> quizzesToAdapt){ this.quizzesToAdapt = quizzesToAdapt; }

        @NonNull
        @Override
        public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_preview,
                    parent, false);
            QuizViewHolder quizViewHolder = new QuizViewHolder(view);
            return quizViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull QuizViewHolder holder, int position){
            final Quiz quizAtPosition = quizzesToAdapt.get(position);
            holder.bind(quizAtPosition);
        }

        @Override
        public int getItemCount() { return quizzesToAdapt.size(); }

        public static class QuizViewHolder extends RecyclerView.ViewHolder{
            public View view;
            public TextView txtQuizName;
            public TextView txtQuizDescription;
            public Button btnSelect;

            public QuizViewHolder(View view){
                super(view);
                this.view = view;
                txtQuizDescription = view.findViewById(R.id.txtQuizDescription);
                txtQuizName = view.findViewById(R.id.txtQuizName);
                btnSelect = view.findViewById(R.id.btnSelect);
            }
            public void bind(final Quiz quiz){
                txtQuizName.setText(quiz.getName());
                txtQuizDescription.setText(quiz.getDescription());
                btnSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, QuizActivity.class);
                        intent.putExtra("quizId", quiz.getQuizId());
                        context.startActivity(intent);
                    }
                });
            }
        }


}
