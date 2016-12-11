package com.example.maxcembalest.loops;
import android.media.AudioTrack;

import static com.example.maxcembalest.loops.AudioGenerator.audioFormat;
import static com.example.maxcembalest.loops.AudioGenerator.channelConfig;
import static com.example.maxcembalest.loops.AudioGenerator.mode;
import static com.example.maxcembalest.loops.AudioGenerator.sampleRate;
import static com.example.maxcembalest.loops.AudioGenerator.streamType;

public class MyAudioTrack extends AudioTrack{
    private double sample[];
    private byte generatedSnd[];

    public MyAudioTrack(int numSamples) {

        super(streamType, sampleRate, channelConfig, audioFormat, 2*numSamples, mode);
        sample = new double[numSamples];
        generatedSnd= new byte[2 * numSamples];
    }
    public void genTone(double freqOfTone){

        for (int i = 0; i < sample.length; ++i) {sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));}

        int idx = 0;
        for (final double dVal : sample) {
            final short val = (short) ((dVal * 32767));
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
            public void onPeriodicNotification(AudioTrack audioTrack) {}});}
}
