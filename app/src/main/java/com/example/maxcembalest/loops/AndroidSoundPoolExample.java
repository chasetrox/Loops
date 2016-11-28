package com.example.maxcembalest.loops;

import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class AndroidSoundPoolExample extends AppCompatActivity {

    private SoundPool soundPool;
    private int soundID;
    boolean plays = false, loaded = false;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    int counter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_sound_pool_example);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        counter = 0;
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        //soundID = soundPool.load(this, R.raw.dumb_story_no_click, 1);
    }

    public void playSound(View v) {

        // Is the sound loaded does it already play?

        if (loaded && !plays) {

            soundPool.play(soundID, volume, volume, 1, 0, 1f);
            counter = counter++;
            Toast.makeText(this, "Played sound", Toast.LENGTH_SHORT).show();
            plays = true;
        }

    }
    public void playLoop(View v) {

        // Is the sound loaded does it already play?
        if (loaded && !plays) {
            // the sound will play for ever if we put the loop parameter -1
            soundPool.play(soundID, volume, volume, 1, -1, 1f);
            counter = counter++;
            Toast.makeText(this, "Plays loop", Toast.LENGTH_SHORT).show();
            plays = true;
        }
    }

    public void pauseSound(View v) {
        if (plays) {
            soundPool.pause(soundID);
           // soundID = soundPool.load(this, R.raw.dumb_story_no_click, counter);
            Toast.makeText(this, "Pause sound", Toast.LENGTH_SHORT).show();
            plays = false;
        }
    }

    public void stopSound(View v) {
        if (plays) {
            soundPool.stop(soundID);
           // soundID = soundPool.load(this, R.raw.dumb_story_no_click, counter);
            Toast.makeText(this, "Stop sound", Toast.LENGTH_SHORT).show();
            plays = false;
        }
    }
}
