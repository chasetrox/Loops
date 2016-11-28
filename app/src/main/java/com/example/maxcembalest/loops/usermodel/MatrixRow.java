package com.example.maxcembalest.loops.usermodel;

import com.example.maxcembalest.loops.grid.LoopGridSquare;

/**
 * Created by maxcembalest on 11/28/16.
 */

public class MatrixRow {

    public LoopGridSquare sq1;
    public LoopGridSquare sq2;
    public LoopGridSquare sq3;
    public LoopGridSquare sq4;
    public LoopGridSquare sq5;
    public LoopGridSquare sq6;
    public LoopGridSquare sq7;
    public LoopGridSquare sq8;

    public MatrixRow() {
    }

    public MatrixRow(LoopGridSquare sq1, LoopGridSquare sq2, LoopGridSquare sq3, LoopGridSquare sq4, LoopGridSquare sq5, LoopGridSquare sq6, LoopGridSquare sq7, LoopGridSquare sq8) {
        this.sq1 = sq1;
        this.sq2 = sq2;
        this.sq3 = sq3;
        this.sq4 = sq4;
        this.sq5 = sq5;
        this.sq6 = sq6;
        this.sq7 = sq7;
        this.sq8 = sq8;
    }

    public LoopGridSquare getSq1() {
        return sq1;
    }

    public void setSq1(LoopGridSquare sq1) {
        this.sq1 = sq1;
    }

    public LoopGridSquare getSq2() {
        return sq2;
    }

    public void setSq2(LoopGridSquare sq2) {
        this.sq2 = sq2;
    }

    public LoopGridSquare getSq3() {
        return sq3;
    }

    public void setSq3(LoopGridSquare sq3) {
        this.sq3 = sq3;
    }

    public LoopGridSquare getSq4() {
        return sq4;
    }

    public void setSq4(LoopGridSquare sq4) {
        this.sq4 = sq4;
    }

    public LoopGridSquare getSq5() {
        return sq5;
    }

    public void setSq5(LoopGridSquare sq5) {
        this.sq5 = sq5;
    }

    public LoopGridSquare getSq6() {
        return sq6;
    }

    public void setSq6(LoopGridSquare sq6) {
        this.sq6 = sq6;
    }

    public LoopGridSquare getSq7() {
        return sq7;
    }

    public void setSq7(LoopGridSquare sq7) {
        this.sq7 = sq7;
    }

    public LoopGridSquare getSq8() {
        return sq8;
    }

    public void setSq8(LoopGridSquare sq8) {
        this.sq8 = sq8;
    }

    public LoopGridSquare getSqJ(int j){
        LoopGridSquare sq = null;
        switch (j){
            case 0: sq = getSq1();
            case 1: sq = getSq2();
            case 2: sq = getSq3();
            case 3: sq = getSq4();
            case 4: sq = getSq5();
            case 5: sq = getSq6();
            case 6: sq = getSq7();
            case 7: sq = getSq8();
        }
        return sq;
    }

    public void setSqJ(int j, LoopGridSquare sq){
        switch (j){
            case 0: setSq1(sq);
            case 1: setSq2(sq);
            case 2: setSq3(sq);
            case 3: setSq4(sq);
            case 4: setSq5(sq);
            case 5: setSq6(sq);
            case 6: setSq7(sq);
            case 7: setSq8(sq);
        }
    }
}
