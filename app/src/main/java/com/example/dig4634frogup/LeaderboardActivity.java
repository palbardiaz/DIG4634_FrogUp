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

        SharedPreferences mPrefs_causal = getSharedPreferences("casual_highscore",0);
        String score_casual = mPrefs_causal.getString("score", "");
        TextView casualText = (TextView)findViewById(R.id.scoreEasy);
        casualText.setText(score_casual);

        SharedPreferences mPrefs_med = getSharedPreferences("med_highscore",0);
        String score_med = mPrefs_med.getString("score", "0");
        TextView medText = (TextView)findViewById(R.id.scoreMedium);
        medText.setText(score_med);

        SharedPreferences mPrefs_hard = getSharedPreferences("hard_highscore",0);
        String score_hard = mPrefs_hard.getString("score", "0");
        TextView hardText = (TextView)findViewById(R.id.scoreHard);
        hardText.setText(score_hard);
    }
    public void onMenuClicked(View view){
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }
}