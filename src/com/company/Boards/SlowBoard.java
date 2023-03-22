package com.company.Boards;

import com.company.Others.Cell;
import com.company.Others.Enums.PlayerColor;

import java.util.ArrayList;

public class SlowBoard extends Board{

    public int redValidTurn;
    public int blueValidTurn;

    public SlowBoard(int height, int width) {
        super(height, width);
        redValidTurn = 0 ;
        blueValidTurn = 0 ;
    }
    public Board copy(){
        int height = this.height;
        int width = this.width;
        SlowBoard newBoard = new SlowBoard(height,width);
        for(int i = 0 ; i < height ; i ++)
        {
            for(int j = 0 ; j < width ; j ++)
            {
                newBoard.board[j][i] = this.board[j][i];
            }
        }
        newBoard.redArr = new ArrayList<Cell>(this.redArr);
        newBoard.blueArr = new ArrayList<Cell>(this.blueArr);
        newBoard.redValidTurn = this.redValidTurn;
        newBoard.blueValidTurn = this.blueValidTurn;
        return newBoard;
    }


    public void putBall(Cell destination,PlayerColor color) {
        char oldC = get(destination);
        char newC = color.getChar();
        int roadN = 0;
        int redN = 0;
        int blueN = 0;
        for(int i = 0 ; i < 25 ; i++)
        {
            char turnC = get(new Cell(destination.x+turnArr[i].x,destination.y+turnArr[i].y));
            if(turnC == road)
            {
                roadN++;
            }
            else if(turnC == red)
            {
                redN++;
            }
            else if(turnC == blue)
            {
                blueN++;
            }
        }
        if(newC != oldC)
        {
            if(oldC == road)
            {
                if(newC == red)
                {
                    redArr.add(destination);
                    redValidTurn  += roadN - redN - 1;
                    blueValidTurn -= blueN;
                }
                else if (newC == blue)
                {
                    blueArr.add(destination);
                    blueValidTurn += roadN - blueN - 1;
                    redValidTurn  -= redN;
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
                blueValidTurn += roadN;
                redValidTurn  -= roadN;
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
                redValidTurn  += roadN;
                blueValidTurn -= roadN;
            }
            put(destination,newC);
        }
    }

    public void putRoad(Cell destination) {
        int roadN = 0;
        int redN = 0;
        int blueN = 0;
        for(int i = 0 ; i < 25 ; i++)
        {
            char turnC = get(new Cell(destination.x+turnArr[i].x,destination.y+turnArr[i].y));
            if(turnC == road)
            {
                roadN++;
            }
            else if(turnC == red)
            {
                redN++;
            }
            else if(turnC == blue)
            {
                blueN++;
            }
        }
        if(isRed(destination))
        {
            redValidTurn += redN - roadN - 1 ;
            blueValidTurn += blueN ;
        }
        if(isBlue(destination))
        {
            blueValidTurn += blueN - roadN - 1 ;
            redValidTurn += redN  ;
        }
        put(destination,road);
    }
    public void putWall(Cell destination) {
        int roadN = cellTurnNum(destination);
        if(isRed(destination))
        {
            redValidTurn -= roadN ;
        }
        if(isBlue(destination))
        {
            blueValidTurn -= roadN ;
        }
        put(destination,wall);
    }

    public int playerTurnNum(PlayerColor color){
        return (color == PlayerColor.red) ? redValidTurn : blueValidTurn ;
    }



}
