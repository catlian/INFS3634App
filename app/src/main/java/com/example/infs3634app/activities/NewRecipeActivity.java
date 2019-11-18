package com.example.infs3634app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.infs3634app.R;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class NewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        Intent intent = getIntent();
        TextView errormsg = findViewById(R.id.errorMsg);
        errormsg.setVisibility(View.GONE);
    }

    public void onClickLoadImage(View view){
        ImageView drinkImage = findViewById(R.id.newDrinkImage);
        EditText newDrinkName = (EditText)findViewById(R.id.newDrinkName);
        EditText newDrinkImageLink = (EditText)findViewById(R.id.imageURL);
        //String drinkName = newDrinkName.getText().toString();
        String drinkImageURL = newDrinkImageLink.getText().toString().replace("\\","");
        Glide.with(this).load(drinkImageURL).into(drinkImage);

    }

}
