package com.example.maxcembalest.loops;

/**
 * Created by maxcembalest on 11/21/16.
 */

public class Frequencies {

    public double freqLowA = 220.0;
    public double freqB = 246.94;
    public double freqCSharp = 277.18;
    public double freqD = 293.66;
    public double freqE = 329.63;
    public double freqFSharp = 369.99;
    public double freqGSharp = 415.30;
    public double freqHighA = 440.00;

    public Frequencies() {
    }

    public double getFreqLowA() {
        return freqLowA;
    }

    public double getFreqB() {
        return freqB;
    }

    public double getFreqCSharp() {
        return freqCSharp;
    }

    public double getFreqD() {
        return freqD;
    }

    public double getFreqE() {
        return freqE;
    }

    public double getFreqFSharp() {
        return freqFSharp;
    }

    public double getFreqGSharp() {
        return freqGSharp;
    }

    public double getFreqHighA() {
        return freqHighA;
    }

    public double columnToFreq(int col){
        double amt = 0;
        switch (col){
            case 0:
                amt = getFreqLowA();break;
            case 1:
                amt = getFreqB();break;
            case 2:
                amt = getFreqCSharp();break;
            case 3:
                amt = getFreqD();break;
            case 4:
                amt = getFreqE();break;
            case 5:
                amt = getFreqFSharp();break;
            case 6:
                amt = getFreqGSharp();break;
            case 7:
                amt = getFreqHighA();break;
        }
        return amt;
    }
}
