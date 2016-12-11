package com.example.maxcembalest.loops.grid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.example.maxcembalest.loops.LoopActivity.dimBeats;
import static com.example.maxcembalest.loops.LoopActivity.dimNotes;

/**
 * Created by maxcembalest on 11/19/16.
 */

public class LoopGridView extends View{

    private Paint paintLine;
    private Paint paintBgSquare;

    public LoopGridView(Context context){
        super(context);
    }


    public LoopGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(2);

        paintBgSquare = new Paint();
        paintBgSquare.setColor(Color.WHITE);
        paintBgSquare.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        drawLines(canvas);
        fillSquares(canvas);
    }

    private void drawLines(Canvas canvas) {
        for (int i = 1; i < dimNotes; i++) {
            canvas.drawLine(0, i * getHeight() / dimNotes, getWidth(), i * getHeight() / dimNotes, paintLine);
        }
        for (int i = 1; i < dimBeats; i++) {
            canvas.drawLine(i * getWidth() / dimBeats, 0, i * getWidth() / dimBeats, getHeight(), paintLine);
        }
    }

    private void fillSquares(Canvas canvas) {
        for (int i = 0; i < dimBeats; i++) {
            for (int j = 0; j < dimNotes; j++) {
                drawRectAt(canvas, i, j);
            }
        }
    }

    private void drawRectAt(Canvas canvas, int i, int j) {
        LoopGridSquare sqcontent = LoopGrid.getInstance().getFieldContent(i,j);
        LoopGrid lg = LoopGrid.getInstance();
        Log.d("IN DRAWR", ""+ Integer.toString(i)+" "+Integer.toString(j));
        if (sqcontent.isClicked()){
            canvas.drawRect(i * getHeight() / dimBeats, (j)*getHeight()/dimNotes,(i+1) * getHeight() / dimBeats, (j+1)*getHeight()/dimNotes, paintBgSquare);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int tX = ((int) event.getX()) / (getWidth() / dimBeats);
            int tY = ((int) event.getY()) / (getHeight() / dimNotes);
            if (tX < dimBeats && tY < dimNotes) {clickSquare(tX, tY);}
            invalidate();
            Log.d("TAG_CLICKED","Clicked at: ("+tX+", "+tY+")");

        }
        return false;
    }

    private void clickSquare(int tX, int tY) {
        LoopGrid.getInstance().setFieldClicked(tX,tY);
    }

    public double getNoteFrequency(int beat, int note){
        double freq = 0;
        if (note < dimNotes) {
            //freq = LoopGrid.getInstance().getRow(note).getFrequency();
            MatrixRow r = LoopGrid.getInstance().getRow(note);
            LoopGridSquare sq = r.getSqJ(beat);
            if (sq.isClicked()){
                freq = r.getFrequency();
            }
        }
        return freq;
    }

    public void clearGrid() {
        for (int i = 0; i < dimBeats; i++){
            for (int j = 0; j < dimNotes; j++){
                LoopGrid.getInstance().getFieldContent(i,j).setClicked(false);
                invalidate();
            }
        }
    }

}
