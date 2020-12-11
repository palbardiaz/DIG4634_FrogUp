package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    boolean isSettingsOpen;
    ImageButton trophy;
    ImageButton music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        isSettingsOpen = false;
        trophy = findViewById(R.id.trophy);
        music = findViewById(R.id.music);

        trophy.setVisibility(View.INVISIBLE);
        music.setVisibility(View.INVISIBLE);

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

    public void onSettingsClicked(View view){
        if(!isSettingsOpen) {
            FadeIn();
            isSettingsOpen = true;
        }
        else {
            FadeOut(view);
            isSettingsOpen = false;
        }
    }

    public void onMusicClicked(View view){
        music = (ImageButton)findViewById(R.id.music);
        if(GlobalVariables.isMusicOn){
            music.setImageResource(R.drawable.music_off);
            stopService(new Intent(this, BackgroundAudioService.class));
            GlobalVariables.isMusicOn = false;
        }
        else {
            music.setImageResource(R.drawable.music_on);
            startService(new Intent(this, BackgroundAudioService.class));
            GlobalVariables.isMusicOn = true;
        }
    }

    public void onLeaderboardClicked(View view){
        Intent myIntent = new Intent(getBaseContext(), LeaderboardActivity.class);
        startActivity(myIntent);
    }

    public void FadeIn(){
        trophy.setVisibility(View.VISIBLE);
        trophy.setAlpha(0.0f);
        trophy.animate()
                .translationY(0)
                .alpha(1.0f)
                .setListener(null);

        music.setVisibility(View.VISIBLE);
        music.setAlpha(0.0f);
        music.animate()
                .translationY(0)
                .alpha(1.0f)
                .setListener(null);

    }

    public void FadeOut(View view){
        trophy.animate()
                .translationY(view.getHeight())
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        trophy.setVisibility(View.GONE);
                    }
                });
        music.animate()
                .translationY(view.getHeight())
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        music.setVisibility(View.GONE);
                    }
                });
    }
}