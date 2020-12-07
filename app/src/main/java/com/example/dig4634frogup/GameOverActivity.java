package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        String score = getIntent().getStringExtra("SCORE");

        TextView scoreText = this.findViewById(R.id.scoreView);

        scoreText.setText("SCORE: " + score);
    }

    public void onPlayAgain(View view) {
        String level = getIntent().getStringExtra("LEVEL");

        if (level.equals("CASUAL")) {
            Intent intent = new Intent(getBaseContext(), CasualActivity.class);
            startActivity(intent);
        } else if (level.equals("INTERMEDIATE")) {
            Intent intent = new Intent(getBaseContext(), IntermediateActivity.class);
            startActivity(intent);
        } else if (level.equals("PRO")) {
            Intent intent = new Intent(getBaseContext(), ProActivity.class);
            startActivity(intent);
        }
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}