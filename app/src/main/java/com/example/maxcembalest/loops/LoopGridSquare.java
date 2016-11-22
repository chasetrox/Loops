package com.example.maxcembalest.loops;

/**
 * Created by maxcembalest on 11/21/16.
 */

public class LoopGridSquare {

    private int beat;//numbered 0 through 7
    private boolean clicked;
    private double frequency;
    private Frequencies frequencies = new Frequencies();

    public LoopGridSquare(int row, int column){
        beat = column;
        clicked = false;
        frequency = frequencies.columnToFreq(column);
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
}
