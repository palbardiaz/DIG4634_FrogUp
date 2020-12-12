package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        TextView casualText = (TextView)findViewById(R.id.scoreEasy);
        casualText.setText(Integer.toString(GlobalVariables.highscore_casual));

        TextView medText = (TextView)findViewById(R.id.scoreMedium);
        medText.setText(Integer.toString(GlobalVariables.highscore_med));

        TextView hardText = (TextView)findViewById(R.id.scoreHard);
        hardText.setText(Integer.toString(GlobalVariables.highscore_hard));
    }
    public void onMenuClicked(View view){
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }
}