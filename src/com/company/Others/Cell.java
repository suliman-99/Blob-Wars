package com.company.Others;

public class Cell {
    public int x;
    public int y ;

    public Cell(int x, int y) {
     this.x = x;
     this.y = y;
    }

    public int distance(Cell c) {
        int x = Math.abs(this.x - c.x);
        int y = Math.abs(this.y - c.y);
        return Math.max(x,y);
    }

    public boolean isEqual(Cell c){
        if(this.x != c.x)
        {
            return false;
        }
        if(this.y != c.y){
            return false;
        }
        return true;
    }
}
