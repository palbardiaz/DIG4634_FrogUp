package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    boolean isSettingsOpen;
    ImageButton trophy;
    ImageButton music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isSettingsOpen = false;
        trophy = findViewById(R.id.trophy);
        music = findViewById(R.id.music);

        if(!GlobalVariables.hasBeenLoaded){ // Checks that audio service is only called once at initial load up
            startService(new Intent(this, BackgroundAudioService.class));
            GlobalVariables.hasBeenLoaded = true;
        }
        trophy.setVisibility(View.INVISIBLE);
        music.setVisibility(View.INVISIBLE);
    }

    public void onStartClicked(View view){
        Intent myIntent = new Intent(getBaseContext(), SelectDifficultyActivity.class);
        startActivity(myIntent);
    }

    public void onSettingsClicked(View view){
        if(!isSettingsOpen) {
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

            isSettingsOpen = true;
        }
        else {
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
}