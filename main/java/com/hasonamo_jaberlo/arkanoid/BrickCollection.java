package com.hasonamo_jaberlo.arkanoid;

public class BrickCollection     {

    public int[] getCollection_arr() {
        return collection_arr;
    }

    public void setCollection_arr(int[] collection_arr) {
        this.collection_arr = collection_arr;
    }

    private int collection_arr[];
    private int col;
    private Brick brick;
private Brick[][] colection;

    public Brick[][] getColection() {

        return colection;
    }

    public void setColection(Brick[][] colection) {



    }
    public BrickCollection(int row,int col){
       for(int i =0;i<col;i++){
           this.collection_arr[i]=row;
       }

    }
}
