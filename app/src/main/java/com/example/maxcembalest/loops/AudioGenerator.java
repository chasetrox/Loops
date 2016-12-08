package com.example.maxcembalest.loops;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import static com.example.maxcembalest.loops.LoopActivity.durationMSecs;

/**
 * Created by maxcembalest on 11/30/16.
 */

public class AudioGenerator {

    public static AudioGenerator instance = null;

    public static int streamType = AudioManager.STREAM_MUSIC;
    public static int sampleRate = 8000;
    public static int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
    public static int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    public static int mode = AudioTrack.MODE_STATIC;

    private double durationA = 0.5;
    private int durationB = 500;
    private int numSamples = (int) (sampleRate*((durationMSecs+0.0)/1000.0));

    private MyAudioTrack[] audioTracks;
    private MyAudioTrack singleTrack;

    public static AudioGenerator getInstance() {
        if (instance == null) {
            instance = new AudioGenerator();
        }
        return instance;
    }

    public AudioGenerator(){
        audioTracks = new MyAudioTrack[8];
        for (int i = 0; i < 8; i++){
            audioTracks[i]=new MyAudioTrack(numSamples);
        }

        singleTrack = new MyAudioTrack(numSamples);
    }

    void playSingle(double tone){
        singleTrack.play(tone);
        //singleTrack.flush();
        //singleTrack.reloadStaticData();
    }

    void playNote(double tone, int note){
        audioTracks[note].play(tone);
    }
}
