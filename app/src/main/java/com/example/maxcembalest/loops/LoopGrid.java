package com.example.maxcembalest.loops;

/**
 * Created by maxcembalest on 11/21/16.
 */
public class LoopGrid {

    private static LoopGrid instance = null;

    private LoopGrid(){

    }

    public static LoopGrid getInstance() {
        if (instance == null) {
            instance = new LoopGrid();
        }
        return instance;
    }


    private LoopGridSquare[][] model =
            {
                    {new LoopGridSquare(0,0),new LoopGridSquare(0,1), new LoopGridSquare(0,2), new LoopGridSquare(0,3)},
                    {new LoopGridSquare(1,0),new LoopGridSquare(1,1), new LoopGridSquare(1,2), new LoopGridSquare(1,3)},
                    {new LoopGridSquare(2,0),new LoopGridSquare(2,1), new LoopGridSquare(2,2), new LoopGridSquare(2,3)},
                    {new LoopGridSquare(3,0),new LoopGridSquare(3,1), new LoopGridSquare(3,2), new LoopGridSquare(3,3)}
            };

    public LoopGridSquare getFieldContent(int x, int y){return model[x][y];}

    public void setFieldContent(int x, int y){model[x][y].setClicked(!model[x][y].isClicked());}


}


