package com.company.Boards;

import com.company.Others.Cell;
import com.company.Others.Enums.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class FastBoard extends Board {



    public FastBoard(int height, int width) {
        super(height, width);
    }

    public Board copy(){
        int height = this.height;
        int width = this.width;
        Board newBoard = new FastBoard(height,width);
        for(int i = 0 ; i < height ; i ++)
        {
            for(int j = 0 ; j < width ; j ++)
            {
                newBoard.board[j][i] = this.board[j][i];
            }
        }
        newBoard.redArr = new ArrayList<Cell>(this.redArr);
        newBoard.blueArr = new ArrayList<Cell>(this.blueArr);
        return newBoard;
    }



    public void putBall(Cell destination,PlayerColor color) {
        char oldC = get(destination);
        char newC = color.getChar();
        if(newC != oldC)
        {
            if(oldC == road)
            {
                if(newC == red)
                {
                    redArr.add(destination);
                }
                else if (newC == blue)
                {
                    blueArr.add(destination);
                }
            }
            else if(oldC == red)
            {
                for(int i = 0 ; i < redArr.size() ; i++)
                {
                    if(redArr.get(i).isEqual(destination))
                    {
                        redArr.remove(i);
                        break;
                    }
                }
                blueArr.add(destination);
            }
            else if(oldC == blue)
            {
                for(int i = 0 ; i < blueArr.size() ; i++)
                {
                    if(blueArr.get(i).isEqual(destination))
                    {
                        blueArr.remove(i);
                        break;
                    }
                }
                redArr.add(destination);
            }
            put(destination,newC);
        }
    }


    public void putRoad(Cell destination) {
        if(isRed(destination))
        {
            for(int i = 0 ; i < redArr.size() ; i++)
            {
                if(redArr.get(i).isEqual(destination))
                {
                    redArr.remove(i);
                    break;
                }
            }
        }
        if(isBlue(destination))
        {
            for(int i = 0 ; i < blueArr.size() ; i++)
            {
                if(blueArr.get(i).isEqual(destination))
                {
                    blueArr.remove(i);
                    break;
                }
            }
        }
        put(destination,road);
    }
    public void putWall(Cell destination) {
        if(isRed(destination))
        {
            for(int i = 0 ; i < redArr.size() ; i++)
            {
                if(redArr.get(i).isEqual(destination))
                {
                    redArr.remove(i);
                    break;
                }
            }
        }
        if(isBlue(destination))
        {
            for(int i = 0 ; i < blueArr.size() ; i++)
            {
                if(blueArr.get(i).isEqual(destination))
                {
                    blueArr.remove(i);
                    break;
                }
            }
        }
        put(destination,wall);
    }

    public int playerTurnNum(PlayerColor color){
        int sum = 0 ;
        List<Cell> sourceArr = (color == PlayerColor.red) ? redArr : blueArr ;
        for(int i = 0 ; i < sourceArr.size() ; i ++)
        {
            sum += cellTurnNum(sourceArr.get(i));
        }
        return sum;
    }



}
