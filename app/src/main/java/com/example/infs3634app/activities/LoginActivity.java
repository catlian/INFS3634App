package com.example.infs3634app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.infs3634app.R;
import com.example.infs3634app.model.ID;

public class LoginActivity extends AppCompatActivity  {

    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Spinner spinner = findViewById(R.id.spinner);
        Button button = findViewById(R.id.btnSelect);

        Integer[] ids = new Integer[]{1,2,3};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>
                (this,android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID.user_id = (Integer.parseInt(String.valueOf(spinner.getSelectedItem())));
                Toast.makeText(v.getContext(), "Selected: " + ID.user_id, Toast.LENGTH_LONG).show();
                Intent intent= new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
