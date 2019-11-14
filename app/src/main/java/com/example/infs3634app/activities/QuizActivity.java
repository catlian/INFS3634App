package com.example.infs3634app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.infs3634app.R;

public class QuizActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton btnSelection;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Intent intent = getIntent();

        txtResult = findViewById(R.id.txtResult);
        radioGroup = findViewById(R.id.radioGroup);


        /* Alternatively could check after pressing of confirmation button? might be slow and annoying
        alternatively again could record results only after doing all qs - seems slow and unnecessary
        back and forth between pages for our level lmao unless we do the recyclerview/adapter thing
        points for quiz total/results screen needed?
        */

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnSelection = findViewById(checkedId);
                Log.d("chk", "id" + checkedId);

                if (btnSelection.getText().equals("String of correct answer from db retrieval")){
                    txtResult.setText("hell yeah");
                }
                else{
                    txtResult.setText("wuh oh");
                }
            }
        });


    }


}
