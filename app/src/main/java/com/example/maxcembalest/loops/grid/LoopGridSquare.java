package com.example.maxcembalest.loops.grid;

import com.example.maxcembalest.loops.Frequencies;

/**
 * Created by maxcembalest on 11/21/16.
 */

public class LoopGridSquare {

    private int beat;//numbered 0 through 7
    private boolean clicked;
    private double frequency;
    private String soundKey;
    private Frequencies frequencies = new Frequencies();

    public LoopGridSquare(double freq, String key, boolean c){
        clicked = c;
        frequency = freq;
        soundKey=key;
    }

    public int getBeat() {
        return beat;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getSoundKey() {
        return soundKey;
    }

    public void setSoundKey(String soundKey) {
        this.soundKey = soundKey;
    }
}
