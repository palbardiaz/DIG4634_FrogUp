package com.example.dig4634frogup;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectDifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_difficulty);
    }
    
    public void onCasualClicked(View view) {
        Intent myIntent = new Intent(getBaseContext(), CasualActivity.class);
        startActivity(myIntent);
    }

    public void onIntermediateClicked(View view) {
        Intent myIntent = new Intent(getBaseContext(), IntermediateActivity.class);
        startActivity(myIntent);
    }

    public void onProClicked(View view) {
        Intent myIntent = new Intent(getBaseContext(), ProActivity.class);
        startActivity(myIntent);
    }

}