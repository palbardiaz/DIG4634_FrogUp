package com.example.dig4634frogup;

import android.media.MediaPlayer;
import android.media.SoundPool;

public class GlobalVariables {
    public static boolean isMusicOn = true;
    public static boolean hasBeenLoaded = false;
    public static SoundPool jumpSound;
    public static SoundPool boostSound;
    public static int jumpSoundId;
    public static int boostSoundId;

    public static int highscore_casual = 0;
    public static int highscore_med = 0;
    public static int highscore_hard = 0;
}
