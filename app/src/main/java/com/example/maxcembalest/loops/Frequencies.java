package com.example.maxcembalest.loops;

/**
 * Created by maxcembalest on 11/21/16.
 */

public class Frequencies {

    private double freqLowG = 196.0;
    private double freqLowA = 220.0;
    private double freqLowB = 246.94;
    private double freqD = 293.66;
    private double freqE = 329.63;
    private double freqHighG = 392.0;
    private double freqHighA = 440.0;
    private double freqHighB = 493.88;

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
