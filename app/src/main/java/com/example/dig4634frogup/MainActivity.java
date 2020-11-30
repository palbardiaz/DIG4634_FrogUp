package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onMusicClicked(View view){
        Intent myIntent = new Intent(getBaseContext(), MusicPlayerActivity.class);
        startActivity(myIntent);
    }
    public void onLeaderboardClicked(View view){
        Intent myIntent = new Intent(getBaseContext(), LeaderboardActivity.class);
        startActivity(myIntent);
    }
}