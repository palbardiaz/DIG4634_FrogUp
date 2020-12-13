package com.example.dig4634frogup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    boolean isSettingsOpen;
    ImageButton trophy;
    ImageButton music;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build();
        GlobalVariables.jumpSound = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(audioAttributes)
                .build();

        GlobalVariables.jumpSoundId = GlobalVariables.jumpSound.load(getApplicationContext(), R.raw.jump2, 1);

        GlobalVariables.boostSound = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(audioAttributes)
                .build();

        GlobalVariables.boostSoundId = GlobalVariables.boostSound.load(getApplicationContext(), R.raw.jump1, 1);

        //GlobalVariables.jumpSound = MediaPlayer.create(this, R.raw.jump2);
        //GlobalVariables.boostSound = MediaPlayer.create(this,R.raw.jump1);
    }

    public void onStartClicked(View view){
        Intent myIntent = new Intent(getBaseContext(), SelectDifficultyActivity.class);
        startActivity(myIntent);
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