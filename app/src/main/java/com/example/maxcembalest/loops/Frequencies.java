package com.example.maxcembalest.loops;

/**
 * Created by maxcembalest on 11/21/16.
 */

public class Frequencies {

    public double freqLowA = 440.00;
    public double freqB = 493.88;
    public double freqCSharp = 554.37;
    public double freqD = 587.33;
    public double freqE = 659.25;
    public double freqFSharp = 739.99;
    public double freqGSharp = 830.61;
    public double freqHighA = 880.00;

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

    public double determineFreq(int f){
        double amt = 0;
        switch (f){
            case 7:
                amt = getFreqLowA();break;
            case 6:
                amt = getFreqB();break;
            case 5:
                amt = getFreqCSharp();break;
            case 4:
                amt = getFreqD();break;
            case 3:
                amt = getFreqE();break;
            case 2:
                amt = getFreqFSharp();break;
            case 1:
                amt = getFreqGSharp();break;
            case 0:
                amt = getFreqHighA();break;
        }
        return amt;
    }
}
