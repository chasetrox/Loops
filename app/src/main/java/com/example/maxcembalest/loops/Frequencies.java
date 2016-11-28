package com.example.maxcembalest.loops;

/**
 * Created by maxcembalest on 11/21/16.
 */

public class Frequencies {

    public double freqLowG = 392.00;
    public double freqLowA = 440.00;
    public double freqLowB = 493.88;
    public double freqD = 587.33;
    public double freqE = 659.25;
    public double freqHighG = 783.99;
    public double freqHighA = 880.00;
    public double freqHighB = 987.77;

    public Frequencies() {
    }

    public double getFreqLowG(){
        return freqLowG;
    }

    public double getFreqLowA() {
        return freqLowA;
    }

    public double getFreqLowB() {
        return freqLowB;
    }

    public double getFreqD() {
        return freqD;
    }

    public double getFreqE() {
        return freqE;
    }

    public double getFreqHighG() {
        return freqHighG;
    }

    public double getFreqHighA() {
        return freqHighA;
    }

    public double getFreqHighB(){
        return freqHighB;
    }

    public double determineFreq(int f){
        double amt = 0;
        switch (f){
            case 7:
                amt = getFreqLowG();break;
            case 6:
                amt = getFreqLowA();break;
            case 5:
                amt = getFreqLowB();break;
            case 4:
                amt = getFreqD();break;
            case 3:
                amt = getFreqE();break;
            case 2:
                amt = getFreqHighG();break;
            case 1:
                amt = getFreqHighA();break;
            case 0:
                amt = getFreqHighB();break;
        }
        return amt;
    }
}
