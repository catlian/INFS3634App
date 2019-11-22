package com.example.infs3634app.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.infs3634app.R;
import com.example.infs3634app.database.AppDatabase;
import com.example.infs3634app.database.GetUserDelegate;
import com.example.infs3634app.database.GetUserListAsyncTask;
import com.example.infs3634app.database.GetUserListDelegate;
import com.example.infs3634app.database.GetUsernameAsyncTask;
import com.example.infs3634app.model.ID;
import com.example.infs3634app.model.User;

import java.util.List;

public class LeaderboardFragment extends Fragment implements GetUserListDelegate, GetUserDelegate {
    private AppDatabase database;
    private Context context;
    private TableLayout tableLayout;
    private TextView username;
    private Button btnShare;
    public LeaderboardFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        context = getContext();
        database = AppDatabase.getInstance(context);
        username = view.findViewById(R.id.lblUsername);
        btnShare = view.findViewById(R.id.btnShare);

        tableLayout = view.findViewById(R.id.tableLayout);

        //get username to set greeting message
        GetUsernameAsyncTask getUsernameAsyncTask = new GetUsernameAsyncTask();
        getUsernameAsyncTask.setDatabase(database);
        getUsernameAsyncTask.setDelegate(LeaderboardFragment.this);
        getUsernameAsyncTask.execute(ID.user_id);

        //get list of users to set leaderboard
        GetUserListAsyncTask getUserListAsyncTask = new GetUserListAsyncTask();
        getUserListAsyncTask.setDatabase(database);
        getUserListAsyncTask.setDelegate(LeaderboardFragment.this);
        getUserListAsyncTask.execute();



        return view;
    }

    @Override
    public void handleUserResult(List<User> userList) {
        //code ref for layout weight bless them tables are evil
        //https://stackoverflow.com/questions/4641072/how-to-set-layout-weight-attribute-dynamically-from-code
        //for each user create table row with username, total points, highscore
        for (User user : userList) {
            TableRow tbrow = new TableRow(context);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f);
            TextView username = new TextView(context);
            username.setText(user.getUsername());
            username.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            username.setGravity(Gravity.CENTER);
            username.setLayoutParams(layoutParams);
            tbrow.addView(username);

            TextView highScore = new TextView(context);
            highScore.setText(String.valueOf(user.getHighScore()));
            highScore.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            highScore.setGravity(Gravity.CENTER);
            highScore.setLayoutParams(layoutParams);
            tbrow.addView(highScore);

            TextView points = new TextView(context);
            points.setText(String.valueOf(user.getTotalPoints()));
            points.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            points.setGravity(Gravity.CENTER);
            points.setLayoutParams(layoutParams);
            tbrow.addView(points);

            tableLayout.addView(tbrow);
        }
    }

    @Override
    public void handleUserResult(User user) {
        final String highScore = user.getHighScore().toString();
        username.setText("Hi " + user.getUsername() + "!");
        btnShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_TEXT, highScore);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }
}
