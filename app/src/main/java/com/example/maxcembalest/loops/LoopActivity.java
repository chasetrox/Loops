package com.example.maxcembalest.loops;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maxcembalest.loops.fragments.LoopNameFragment;
import com.example.maxcembalest.loops.grid.LoopGrid;
import com.example.maxcembalest.loops.grid.LoopGridView;
import com.example.maxcembalest.loops.grid.ToneMatrix;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoopActivity extends BaseActivity implements FragmentChangeDim.OptionsFragmentInterface{
    public static final String KEY_TYPE = "KEY_TYPE";

    private static final String DIM_FRAGMENT = "DIM_FRAGMENT";
    private boolean editMode = false;
    private String key;

    private final Frequencies f = new Frequencies();
    private LoopGridView loopGridView;

    public static int durationMSecs = 500;
    public static int dimNotes = 8;
    public static int dimBeats = 8;

    private boolean playing = false;
    private int currentBeat = 0;
    private PlayRowThread[] rows = new PlayRowThread[8];
    private UpdateBeat beatThread;

    private ImageView playIcon;
    private String name;
    private boolean hasName = false;

    private IntroThread introThread = new IntroThread();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = "";

        setupEditMode();

        setContentView(R.layout.activity_loop);
        setupViews();
        ButterKnife.bind(this);
        setupThreads();
        introThread.start();
    }

    private void setupEditMode() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            key = b.getString("key");
            editMode = true;
        }
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

    @OnClick(R.id.btnNumRows)
    void onClick2() {
        startFragDim("row");
    }

    @OnClick(R.id.btnNumCols)
    void onClick3() {
        startFragDim("col");
    }

    @OnClick(R.id.btnDuration)
    void onClick4() {
        startFragDim("dur");
    }

    @OnClick(R.id.btnPlay)
    void onClickPlay() {
        if (!playing) {
            playIcon.setVisibility(View.VISIBLE);
            playing = true;
            playThread = new PlayThread();
            playThread.start();
        }
    }

    public void play() {
        playing = true;
        playThread = new PlayThread();
        playThread.start();
    }
    public void stop() {
        if (playing) {
            playing = false;
            playThread.setPlayEnabled(false);
            currentBeat = 0;
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
        LoopNameFragment setName = new LoopNameFragment();
        setName.show(getSupportFragmentManager(), "LOOP_CREATOR");

        // Moved final save call (ie to MatrixDataManager) into OnCallback from Naming method
        // so that it waits for Loop name.
    }

    private void startFragDim(String type) {
        FragmentChangeDim dimFragment = new FragmentChangeDim();
        dimFragment.setCancelable(true);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_TYPE,type);
        dimFragment.setArguments(bundle);

        dimFragment.show(getSupportFragmentManager(),DIM_FRAGMENT);
    }

    @Override
    public void onOptionsFragmentResult(String newItem,String type) {
        switch (type){
            case "row":
                dimNotes=Integer.parseInt(newItem);break;
            case "col":
                dimBeats=Integer.parseInt(newItem);break;
            case "dur":
                durationMSecs=Integer.parseInt(newItem);break;
        }
        loopGridView.invalidate();
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



    private class PlayRowThread extends Thread {
        private int row;
        public PlayRowThread(int row) {this.row = row;}
        public void run() {playToneAtBeat(row, currentBeat);}
    }

    private void playToneAtBeat(int note, int beat) {
        AudioGenerator.getInstance().playNote(loopGridView.getNoteFrequency(beat, note), note);
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

    private class IntroThread extends Thread{
        public void run(){
            playTone3();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playTone2();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playTone1();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playTone2();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playTone3();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void playTone3(){
        AudioGenerator.getInstance().playSingle(f.getFreqHighB());
    }
    private void playTone2(){
        AudioGenerator.getInstance().playSingle(f.getFreqHighA());
    }
    private void playTone1(){
        AudioGenerator.getInstance().playSingle(f.getFreqHighG());
    }

    public void onUserSelectValue(String val) {
        LoopGrid.getInstance().setName(val);

        if (editMode) {
            MatrixDataManager.getInstance().edit(key);
        } else {
            MatrixDataManager.getInstance().save();
        }
        Toast.makeText(this, "Saved :)", Toast.LENGTH_SHORT).show();
        finish();
    }


}