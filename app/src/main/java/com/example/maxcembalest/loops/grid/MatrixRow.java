package com.example.maxcembalest.loops.grid;

/**
 * Created by maxcembalest on 11/28/16.
 */

public class MatrixRow {

    private LoopGridSquare sq0;
    private LoopGridSquare sq1;
    private LoopGridSquare sq2;
    private LoopGridSquare sq3;
    private LoopGridSquare sq4;
    private LoopGridSquare sq5;
    private LoopGridSquare sq6;
    private LoopGridSquare sq7;
    private double frequency;
    private String soundKey;

    public MatrixRow() {
    }

    public MatrixRow(LoopGridSquare sq0, LoopGridSquare sq1, LoopGridSquare sq2, LoopGridSquare sq3, LoopGridSquare sq4, LoopGridSquare sq5, LoopGridSquare sq6, LoopGridSquare sq7, double f, String s) {
        this.sq0 = sq0;
        this.sq1 = sq1;
        this.sq2 = sq2;
        this.sq3 = sq3;
        this.sq4 = sq4;
        this.sq5 = sq5;
        this.sq6 = sq6;
        this.sq7 = sq7;
        frequency = f;
        soundKey = s;
    }

    public LoopGridSquare getSq0() {
        return sq0;
    }

    public void setSq0(LoopGridSquare sq0) {
        this.sq0 = sq0;
    }

    public LoopGridSquare getSq1() {
        return sq1;
    }

    public void setSq1(LoopGridSquare sq1) {
        this.sq1 = sq1;
    }

    public LoopGridSquare getSq2() {
        return sq2;
    }

    public void setSq2(LoopGridSquare sq2) {
        this.sq2 = sq2;
    }

    public LoopGridSquare getSq3() {
        return sq3;
    }

    public void setSq3(LoopGridSquare sq3) {
        this.sq3 = sq3;
    }

    public LoopGridSquare getSq4() {
        return sq4;
    }

    public void setSq4(LoopGridSquare sq4) {
        this.sq4 = sq4;
    }

    public LoopGridSquare getSq5() {
        return sq5;
    }

    public void setSq5(LoopGridSquare sq5) {
        this.sq5 = sq5;
    }

    public LoopGridSquare getSq6() {
        return sq6;
    }

    public void setSq6(LoopGridSquare sq6) {
        this.sq6 = sq6;
    }

    public LoopGridSquare getSq7() {
        return sq7;
    }

    public void setSq7(LoopGridSquare sq7) {
        this.sq7 = sq7;
    }

    public LoopGridSquare getSqJ(int j){
        LoopGridSquare sq = null;
        switch (j){
            case 0: sq = getSq0();break;
            case 1: sq = getSq1();break;
            case 2: sq = getSq2();break;
            case 3: sq = getSq3();break;
            case 4: sq = getSq4();break;
            case 5: sq = getSq5();break;
            case 6: sq = getSq6();break;
            case 7: sq = getSq7();
        }
        return sq;
    }

    public void setSqJ(int j, LoopGridSquare sq){
        switch (j){
            case 0: setSq0(sq);
            case 1: setSq1(sq);
            case 2: setSq2(sq);
            case 3: setSq3(sq);
            case 4: setSq4(sq);
            case 5: setSq5(sq);
            case 6: setSq6(sq);
            case 7: setSq7(sq);
        }
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
