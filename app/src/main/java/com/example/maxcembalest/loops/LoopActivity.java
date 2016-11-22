package com.example.maxcembalest.loops;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoopActivity extends AppCompatActivity {

    private double duration = 0.2; // seconds
    private final int sampleRate = 8000;
    private int numSamples = (int) ( duration * sampleRate);
    private double sample[];

    private boolean enabled;

    private Timer timerMain;

    private int currentBeat=0;

    private final Frequencies f = new Frequencies();

    private byte generatedSnd[] = new byte[2 * numSamples];

    Handler handler = new Handler();

    private LoopGridView loopGridView;

    private Thread firstNote;
    private Thread secondNote;
    private Thread thirdNote;
    private Thread fourthNote;

    private Thread songLoop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);

        loopGridView = (LoopGridView) findViewById(R.id.loopGridView);

        ButterKnife.bind(this);

        firstNote = new Thread() {
            public void run() {
                try {
                    genTone(loopGridView.getNoteFrequency(0));
                    playSound();
                }
                catch (Exception e) {}
            }
        };

        secondNote = new Thread() {
            public void run() {
                try {
                    genTone(loopGridView.getNoteFrequency(1));
                    playSound();
                }
                catch (Exception e) {}
            }
        };

        thirdNote = new Thread() {
            public void run() {
                try {
                    genTone(loopGridView.getNoteFrequency(2));
                    playSound();
                }
                catch (Exception e) {}
            }
        };

        fourthNote = new Thread() {
            public void run() {
                try {
                    genTone(loopGridView.getNoteFrequency(3));
                    playSound();
                }
                catch (Exception e) {}
            }
        };
    }

    @OnClick(R.id.btnSound1)
    void onClick1(){
        genTone(f.freqLowA);
        playSound();
    }

    @OnClick(R.id.btnSound2)
    void onClick2(){
        genTone(f.freqB);
        playSound();
    }

    @OnClick(R.id.btnSound3)
    void onClick3(){
        genTone(f.freqCSharp);
        playSound();
    }

    @OnClick(R.id.btnSound4)
    void onClick4(){
        genTone(f.freqE);
        playSound();
    }
    @OnClick(R.id.btnPlay)
    void onClickPlay(){
        enabled=true;
        timerMain=new Timer();
        timerMain.schedule(new PlayLoopTask(),0,1000);
    }
    @OnClick(R.id.btnStop)
    void onClickStop(){
        enabled=false;
    }




    @Override
    protected void onResume() {
        super.onResume();

        /* Use a new tread as this can take a while
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                genTone();
                handler.post(new Runnable() {

                    public void run() {
                        playSound(generatedSnd.length);
                    }
                });
            }
        });
        thread.start();*/


    }

    void genTone(double freqOfTone){
        numSamples = (int) ( duration * sampleRate);
        sample = new double[numSamples];
        generatedSnd = new byte[2 * numSamples];
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    void playSound(){
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_STEREO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }

    private class PlayLoopTask extends TimerTask {
        @Override
        public void run(){
            while(enabled) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat));
                        playSound();
                        updateCurrentBeat();
                    }
                });
            }
        }
    }

    public void updateCurrentBeat(){
        if (currentBeat<3){currentBeat++;}
        else{currentBeat=0;}
    }
}