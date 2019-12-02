package com.hasonamo_jaberlo.arkanoid;

public class BrickCollection {

    public Brick[][] getCollection_arr() {
        return collection_arr;
    }

    public void setCollection_arr(Brick[][] collection_arr) {
        this.collection_arr = collection_arr;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Brick getBrick(Brick b [][],int row,int col) {
        return b[row][col];
    }

    public void setBrick(Brick b [][],int row,int col) {
        this.brick = brick;
    }

    private Brick collection_arr[][];
    private int col;
    private int row;
    private Brick brick;


    private Brick[][] colection;

    public Brick[][] getColection() {

        return colection;
    }

    public void setColection(Brick[][] colection) {


    }
}
