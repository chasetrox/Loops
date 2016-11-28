package com.example.maxcembalest.loops.grid;

/**
 * Created by maxcembalest on 11/21/16.
 */

public class LoopGridSquare {

    private boolean clicked;

    public LoopGridSquare(boolean c){
        clicked = c;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
