package com.example.maxcembalest.loops.grid;

import com.example.maxcembalest.loops.Frequencies;

import static com.example.maxcembalest.loops.LoopActivity.dimBeats;
import static com.example.maxcembalest.loops.LoopActivity.dimNotes;

/**
 * Created by maxcembalest on 11/21/16.
 */
public class LoopGrid {

    private static final String KEY_DEFAULT = "KEY_DEFAULT";
    private static LoopGrid instance = null;
    private Frequencies f = new Frequencies();

    private LoopGrid(){
        populateGrid();
    }

    public static LoopGrid getInstance() {
        if (instance == null) {
            instance = new LoopGrid();
        }
        return instance;
    }


    //private LoopGridSquare[][] model = new LoopGridSquare[LoopGridView.dimBeats][LoopGridView.dimNotes];
    private ToneMatrix model = new ToneMatrix(null,null,null,null,null,null,null,null);

    public void populateGrid(){
        for (int i = 0; i < 8; i++){
            if (i<= dimNotes){
                MatrixRow newRow = new MatrixRow(null,null,null,null,null,null,null,null,f.determineFreq(i),KEY_DEFAULT);
                model.setRowI(i,newRow);
                for (int j = 0; j < 8; j++){
                    if (j <= dimBeats){
                        model.getRowI(i).setSqJ(j,new LoopGridSquare(false));
                    }
                }
            }
        }
    }

    public void populateGrid(ToneMatrix tm) {
        model = tm;
    }

    public LoopGridSquare getFieldContent(int x, int y){
        //return model[x][y];}
        return model.getRowI(y).getSqJ(x);
    }

    public void setFieldClicked(int beat, int note){

        model.getRowI(note).getSqJ(beat).setClicked(!model.getRowI(note).getSqJ(beat).isClicked());
    }

    public MatrixRow getRow(int x){
        return model.getRowI(x);
    }

   // public void setField



}


