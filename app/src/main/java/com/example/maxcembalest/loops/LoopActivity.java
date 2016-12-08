package com.example.maxcembalest.loops;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maxcembalest.loops.grid.LoopGridView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoopActivity extends BaseActivity implements OnLoopSettingsFragmentAnswer{
    static final String KEY_ROW = "KEY_ROW";
    static final String KEY_COL = "KEY_COL";
    static final String SETTINGS_FRAGMENT = "SETTINGS_FRAGMENT";

    private final Frequencies f = new Frequencies();
    private LoopGridView loopGridView;

    public static long durationMSecs = 500;
    public static int dimNotes = 6;
    public static int dimBeats = 6;

    private boolean playing = false;
    private int currentBeat = 0;
    private PlayRowThread[] rows = new PlayRowThread[8];
    private UpdateBeat beatThread;

    private ImageView playIcon;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);
        setupViews();
        ButterKnife.bind(this);
        setupThreads();
    }

    private void setupViews() {
        loopGridView = (LoopGridView) findViewById(R.id.loopGridView);
        playIcon = (ImageView) findViewById(R.id.ivPlay);
    }

    private void setupThreads() {
        for (int i = 0; i < 8; i++) {
            rows[i] = new PlayRowThread(i);
        }
        beatThread = new UpdateBeat();
    }

    @OnClick(R.id.btnSound1)
    void onClick1() {
        AudioGenerator.getInstance().playSingle(f.getFreqLowG());
    }

    @OnClick(R.id.btnNumRows)
    void onClick2() {
        AudioGenerator.getInstance().playSingle(f.getFreqLowA());
    }

    @OnClick(R.id.btnNumCols)
    void onClick3() {
        AudioGenerator.getInstance().playSingle(f.getFreqLowB());
    }

    @OnClick(R.id.btnDuration)
    void onClick4() {
        AudioGenerator.getInstance().playSingle(f.getFreqD());
    }


    private void playToneAtBeat(int note, int beat) {
        AudioGenerator.getInstance().playNote(loopGridView.getNoteFrequency(beat, note), note);
    }

    private class PlayThread extends Thread{
        private boolean playEnabled = true;

        public void run(){
            while (playEnabled) {
                startRowThreads();
                freezeRowThreads();

                startBeatThread();
                freezeBeatThread();
            }
        }

        public void setPlayEnabled(boolean playEnabled) {
            this.playEnabled = playEnabled;
        }
    }

    private void freezeBeatThread() {
        try {
            beatThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startBeatThread() {
        beatThread = new UpdateBeat();
        beatThread.start();
    }

    private void freezeRowThreads() {
        try {
            for (int i = 0; i < 8; i++){
                rows[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startRowThreads() {
        for (int i = 0; i < 8; i++){
            rows[i] = new PlayRowThread(i);
            rows[i].start();
        }
    }

    private PlayThread playThread;

    @OnClick(R.id.btnPlay)
    void onClickPlay() {
        if (!playing) {
            playIcon.setVisibility(View.VISIBLE);
            playing = true;

            playThread = new PlayThread();
            playThread.start();


        }
    }

    @OnClick(R.id.btnStop)
    void onClickStop() {
        playing = false;
        playThread.setPlayEnabled(false);
        currentBeat = 0;
        playIcon.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btnClear)
    void onClickClear() {
        loopGridView.clearGrid();
    }

    @OnClick(R.id.btnSave)
    void onClickSave() {
        MatrixDataManager.getInstance().save();
        Toast.makeText(this, "Saved :)", Toast.LENGTH_SHORT).show();
    }

    private class PlayRowThread extends Thread {
        private int row;
        public PlayRowThread(int row) {this.row = row;}
        public void run() {playToneAtBeat(row, currentBeat);}
    }

    private void updateCurrentBeat() {
        currentBeat = (currentBeat + 1) % dimBeats;
    }

    private class UpdateBeat extends Thread {
        public void run() {
            updateCurrentBeat();
            try {sleep(durationMSecs);}
            catch (Exception e) {e.printStackTrace();}
        }
    }

    //from the Settings fragment
    @Override
    public void onPositiveSelected(String row, String col) {
        int newRow = Integer.parseInt(row)-1;
        int newCol = Integer.parseInt(col)-1;
        dimNotes=newRow;
        dimBeats=newCol;
        loopGridView.invalidate();

    }

    @Override
    public void onNegativeSelected() {

    }

}