package com.example.maxcembalest.loops;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.maxcembalest.loops.grid.LoopGridView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.maxcembalest.loops.grid.LoopGridView.dimBeats;

public class LoopActivity extends AppCompatActivity {
    private int duractionMSecs = 500; //milliseconds
    private double durationSecs = 0.5; // seconds.  These two variables must be changed simultaneously...this is dumb
    private final int sampleRate = 8000;
    private int numSamples = (int) ( durationSecs * sampleRate);
    private double sample[];

    private boolean enabled;

    private int currentBeat=0;

    private final Frequencies f = new Frequencies();

    private byte generatedSnd[] = new byte[2 * numSamples];

    Handler handler = new Handler();

    private LoopGridView loopGridView;
    DatabaseReference matricesRef = FirebaseDatabase.getInstance().getReference("matrices");
    DatabaseReference rowsRef = FirebaseDatabase.getInstance().getReference("rows");
    DatabaseReference sqsRef = FirebaseDatabase.getInstance().getReference("squares");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);

        loopGridView = (LoopGridView) findViewById(R.id.loopGridView);

        ButterKnife.bind(this);
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
        new PlayRow1Thread().start();
        new PlayRow2Thread().start();
        new PlayRow3Thread().start();
        new PlayRow4Thread().start();
        new PlayRow5Thread().start();
        new PlayRow6Thread().start();
        new PlayRow7Thread().start();
        new PlayRow8Thread().start();

    }
    @OnClick(R.id.btnStop)
    void onClickStop(){
        enabled=false;
        currentBeat=0;
    }
    @OnClick(R.id.btnClear)
    void onClickClear(){
        loopGridView.clearGrid();
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

    private class PlayRow1Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,0));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class PlayRow2Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,1));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class PlayRow3Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,2));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class PlayRow4Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,3));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class PlayRow5Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,4));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class PlayRow6Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,5));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class PlayRow7Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,6));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class PlayRow8Thread extends Thread {
        @Override
        public void run(){
            while (enabled){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        genTone(loopGridView.getNoteFrequency(currentBeat,7));
                        playSound();
                        updateCurrentBeat();
                    }
                });
                try {
                    sleep(duractionMSecs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    void genTone(double freqOfTone){
        numSamples = (int) ( durationSecs * sampleRate);
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

    public void updateCurrentBeat(){
        currentBeat=(currentBeat+1)%dimBeats;
    }
}