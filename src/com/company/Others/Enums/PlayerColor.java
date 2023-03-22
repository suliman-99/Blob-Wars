package com.company.Others.Enums;

import com.company.Boards.Board;

public enum PlayerColor{
    blue,
    red;


    public char getChar(){
        return (this == red) ? Board.red : Board.blue ;
    }
    public PlayerColor getOther(){
        return (this == red) ? blue : red ;
    }
    public char getOtherChar(){
        return (this == red) ? Board.blue : Board.red ;
    }
}
