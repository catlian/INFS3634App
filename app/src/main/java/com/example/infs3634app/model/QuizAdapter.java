package com.example.infs3634app.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634app.R;

import java.util.ArrayList;

public class QuizAdapter {//extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
       /* private ArrayList<Breeds> catsToAdapt;
        FragmentSwapper fs = new FragmentSwapper();

        public void setData(ArrayList<Breeds> catsToAdapt){
            this.catsToAdapt = catsToAdapt;
        }

        @NonNull
        @Override
        public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_chunk,
                    parent, false);
            QuizViewHolder favouritesViewHolder = new QuizViewHolder(view);
            return favouritesViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull QuizViewHolder holder, int position){
            final Breeds catAtPosition = catsToAdapt.get(position);
            holder.txtCatName.setText(catAtPosition.getName());
        }

        @Override
        public int getItemCount() {
            return catsToAdapt.size();
        }

        public static class QuizViewHolder extends RecyclerView.ViewHolder{
            public View view;
            public TextView txtCatName;

            public QuizViewHolder(View view){
                super(view);
                this.view = view;
                txtCatName = view.findViewById(R.id.txtCatName);
            }
        }
    }*/
}
