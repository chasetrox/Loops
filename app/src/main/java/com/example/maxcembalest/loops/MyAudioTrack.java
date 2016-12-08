package com.example.maxcembalest.loops;

import android.media.AudioTrack;

import static com.example.maxcembalest.loops.AudioGenerator.audioFormat;
import static com.example.maxcembalest.loops.AudioGenerator.channelConfig;
import static com.example.maxcembalest.loops.AudioGenerator.mode;
import static com.example.maxcembalest.loops.AudioGenerator.sampleRate;
import static com.example.maxcembalest.loops.AudioGenerator.streamType;


/**
 * Created by maxcembalest on 12/3/16.
 */

public class MyAudioTrack extends AudioTrack{

    private double sample[];
    private byte generatedSnd[];


    public MyAudioTrack(int numSamples) {
        super(streamType, sampleRate, channelConfig, audioFormat, 2*numSamples, mode);
        sample = new double[numSamples];
        generatedSnd= new byte[2 * numSamples];

    }

    public double[] getSample() {
        return sample;
    }

    public void setSample(double[] sample) {
        this.sample = sample;
    }

    public byte[] getGeneratedSnd() {
        return generatedSnd;
    }

    public void setGeneratedSnd(byte[] generatedSnd) {
        this.generatedSnd = generatedSnd;
    }

    public void genTone(double freqOfTone){

        for (int i = 0; i < sample.length; ++i) {
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

    public int write() {
        return super.write(generatedSnd, 0, generatedSnd.length);
    }

    public void play(double tone) {
        genTone(tone);
        write();
        setNotificationMarkerPosition(getBufferSizeInFrames());
        play();
        setPlaybackPositionUpdateListener(new OnPlaybackPositionUpdateListener() {
            @Override
            public void onMarkerReached(AudioTrack audioTrack) {
                stop();
                flush();
            }

            @Override
            public void onPeriodicNotification(AudioTrack audioTrack) {

            }
        });
    }
}
