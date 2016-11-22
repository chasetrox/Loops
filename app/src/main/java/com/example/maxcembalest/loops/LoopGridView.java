package com.example.maxcembalest.loops;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by maxcembalest on 11/19/16.
 */

public class LoopGridView extends View{

    private Paint paintLine;
    private Paint paintBgSquare;
    public static final int dim = 4;

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
        for (int i = 1; i < dim; i++) {
            canvas.drawLine(0, i * getHeight() / dim, getWidth(), i * getHeight() / dim, paintLine);
            canvas.drawLine(i * getWidth() / dim, 0, i * getWidth() / dim, getHeight(), paintLine);
        }
    }

    private void fillSquares(Canvas canvas) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                drawSquareAt(canvas, i, j);
            }
        }
    }

    private void drawSquareAt(Canvas canvas, int i, int j) {
        LoopGridSquare sqcontent = LoopGrid.getInstance().getFieldContent(i,j);
        if (sqcontent.isClicked()){
            //canvas.drawBitmap(flagResized,i * getWidth() / 5,j * getHeight() / 5,paintLine);
            canvas.drawRect(i * getHeight() / dim, (j)*getHeight()/dim,(i+1) * getHeight() / dim, (j+1)*getHeight()/dim, paintBgSquare);
        }
        else {
            //canvas.drawBitmap(mineResized,i * getWidth() / 5 + 5,j * getHeight() / 5 + 5,paintLine);
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
            int tX = ((int) event.getX()) / (getWidth() / dim);
            int tY = ((int) event.getY()) / (getHeight() / dim);
            if (tX < dim && tY < dim) {clickSquare(tX, tY);}
            invalidate();
            Log.d("TAG_CLICKED","Clicked at: ("+tX+", "+tY+")");

        }
        return false;
    }

    private void clickSquare(int row, int column) {
        LoopGrid.getInstance().setFieldContent(row,column);
    }

    public double getNoteFrequency(int noteY){
        double freq = 0;
        try {
            freq = LoopGrid.getInstance().getFieldContent(getSelectedNote(noteY), noteY).getFrequency();
        } catch (Exception e)
        {
            Log.d("TAG_ERROR", "uh oh");
            e.printStackTrace();
        }finally {
            return freq;
        }
    }

    public int getSelectedNote(int noteY){
        for (int x = 0; x < dim; x ++){
            if (LoopGrid.getInstance().getFieldContent(x,noteY).isClicked()){return x;}
        }
        return 0;
    }


}
