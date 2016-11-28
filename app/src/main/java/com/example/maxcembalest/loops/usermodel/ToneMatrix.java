package com.example.maxcembalest.loops.usermodel;

/**
 * Created by maxcembalest on 11/28/16.
 */

public class ToneMatrix {

    private MatrixRow row1;
    private MatrixRow row2;
    private MatrixRow row3;
    private MatrixRow row4;
    private MatrixRow row5;
    private MatrixRow row6;
    private MatrixRow row7;
    private MatrixRow row8;

    public ToneMatrix(){

    }

    public ToneMatrix(MatrixRow r1, MatrixRow r2,MatrixRow r3, MatrixRow r4, MatrixRow r5, MatrixRow r6, MatrixRow r7, MatrixRow r8){
        row1=r1;
        row2=r2;
        row3=r3;
        row4=r4;
        row5=r5;
        row6=r6;
        row7=r7;
        row8=r8;
    }

    public MatrixRow getRow1() {
        return row1;
    }

    public void setRow1(MatrixRow row1) {
        this.row1 = row1;
    }

    public MatrixRow getRow2() {
        return row2;
    }

    public void setRow2(MatrixRow row2) {
        this.row2 = row2;
    }

    public MatrixRow getRow3() {
        return row3;
    }

    public void setRow3(MatrixRow row3) {
        this.row3 = row3;
    }

    public MatrixRow getRow4() {
        return row4;
    }

    public void setRow4(MatrixRow row4) {
        this.row4 = row4;
    }

    public MatrixRow getRow5() {
        return row5;
    }

    public void setRow5(MatrixRow row5) {
        this.row5 = row5;
    }

    public MatrixRow getRow6() {
        return row6;
    }

    public void setRow6(MatrixRow row6) {
        this.row6 = row6;
    }

    public MatrixRow getRow7() {
        return row7;
    }

    public void setRow7(MatrixRow row7) {
        this.row7 = row7;
    }

    public MatrixRow getRow8() {
        return row8;
    }

    public void setRow8(MatrixRow row8) {
        this.row8 = row8;
    }

    public MatrixRow getRowI(int i){
        MatrixRow ret = null;
        switch (i){
            case 0: ret =  row1;
            case 1: ret =  row2;
            case 2: ret =  row3;
            case 3: ret =  row4;
            case 4: ret =  row5;
            case 5: ret =  row6;
            case 6: ret =  row7;
            case 7: ret =  row8;
        }
        return ret;
    }

    public void setRowI(int i, MatrixRow rowI) {
        switch (i){
            case 0: setRow1(rowI);break;
            case 1: setRow2(rowI);break;
            case 2: setRow3(rowI);break;
            case 3: setRow4(rowI);break;
            case 4: setRow5(rowI);break;
            case 5: setRow6(rowI);break;
            case 6: setRow7(rowI);break;
            case 7: setRow8(rowI);break;
        }
    }
}
