package com.example.maxcembalest.loops.grid;

import com.example.maxcembalest.loops.Frequencies;
import com.example.maxcembalest.loops.usermodel.MatrixRow;
import com.example.maxcembalest.loops.usermodel.ToneMatrix;

import static com.example.maxcembalest.loops.grid.LoopGridView.dimBeats;
import static com.example.maxcembalest.loops.grid.LoopGridView.dimNotes;

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
        for (int i = 0; i < 7; i++){
            if (i<= dimNotes){
                model.setRowI(i,new MatrixRow(null,null,null,null,null,null,null,null));
                for (int j = 0; j < 7; j++){
                    if (j <= dimBeats){
                        model.getRowI(i).setSqJ(j,new LoopGridSquare(f.determineFreq(i),KEY_DEFAULT,false));
                    }
                }
            }
        }
    }

    public LoopGridSquare getFieldContent(int x, int y){
        //return model[x][y];}
        return model.getRowI(y).getSqJ(x);
    }

    public void setFieldClicked(int x, int y){
        //model[x][y].setClicked(!model[x][y].isClicked());
        model.getRowI(y).getSqJ(x).setClicked(!model.getRowI(y).getSqJ(x).isClicked());
    }

   // public void setField


}


