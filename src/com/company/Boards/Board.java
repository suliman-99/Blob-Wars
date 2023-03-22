package com.company.Boards;

import com.company.Others.Cell;
import com.company.Others.Enums.GameState;
import com.company.Others.Enums.PlayerColor;
import com.company.Others.Turn;
import com.company.Others.Enums.TurnState;

import java.util.ArrayList;
import java.util.List;

public abstract class Board {

    public static Cell[] paintArr = new Cell[]{
            new Cell(0,0),
            new Cell(0,1),
            new Cell(1,1),
            new Cell(1,0),
            new Cell(1,-1),
            new Cell(0,-1),
            new Cell(-1,-1),
            new Cell(-1,0),
            new Cell(-1,1),
    };

    public static Cell[] turnArr = new Cell[]{
            new Cell(0,0),
            new Cell(0,1),
            new Cell(1,1),
            new Cell(1,0),
            new Cell(1,-1),
            new Cell(0,-1),
            new Cell(-1,-1),
            new Cell(-1,0),
            new Cell(-1,1),
            new Cell(-1,2),
            new Cell(0,2),
            new Cell(1,2),
            new Cell(2,2),
            new Cell(2,1),
            new Cell(2,0),
            new Cell(2,-1),
            new Cell(2,-2),
            new Cell(1,-2),
            new Cell(0,-2),
            new Cell(-1,-2),
            new Cell(-2,-2),
            new Cell(-2,-1),
            new Cell(-2,0),
            new Cell(-2,1),
            new Cell(-2,2),
    };

    public static char road = '.' ;
    public static char wall = '#' ;
    public static char red = 'R' ;
    public static char blue = 'B' ;
    public static char inValid = '!' ;





    public char[][] board;
    public int height;
    public int width;

    public List<Cell> redArr;
    public List<Cell> blueArr;



    public Board(int height , int width) {
        this.height = height;
        this.width = width;
        board = new char[height][width];
        for(int i = 0 ; i < height ; i ++)
        {
            for(int j = 0 ; j < width ; j ++)
            {
                putRoad(new Cell(j,i));
            }
        }
        redArr = new ArrayList<Cell>();
        blueArr = new ArrayList<Cell>();
    }

    public static Board defaultFastBoard(int height,int width) {
        Board newBoard = new FastBoard(height,width);
        newBoard.putBall(new Cell(0,0), PlayerColor.blue);
        newBoard.putBall(new Cell(width-1,0),PlayerColor.blue);
        newBoard.putBall(new Cell(0,height-1),PlayerColor.red);
        newBoard.putBall(new Cell(width-1,height-1),PlayerColor.red);

        return newBoard;
    }
    public static Board defaultSlowBoard(int height,int width) {
//        Board newBoard = new SlowBoard(height,width);
        Board newBoard = new FastBoard(height,width);
        newBoard.putBall(new Cell(0,0), PlayerColor.blue);
        newBoard.putBall(new Cell(width-1,0),PlayerColor.blue);
        newBoard.putBall(new Cell(0,height-1),PlayerColor.red);
        newBoard.putBall(new Cell(width-1,height-1),PlayerColor.red);

        return newBoard;
    }
    public abstract Board copy();





    public boolean isInBoard(Cell source){
        return (source.x >= 0 && source.x < width && source.y >= 0 && source.y < height);
    }
    public char get(Cell source){
        return  (isInBoard(source)) ? board[source.y][source.x] : inValid ;
    }
    public void put(Cell destination,char c) {
        if(isInBoard(destination)){board[destination.y][destination.x] = c;}
    }

    public boolean isRoad(Cell source){
        return (get(source) == road);
    }
    public boolean isWall(Cell source){
        return (get(source) == wall);
    }
    public boolean isRed(Cell source){
        return (get(source) == red);
    }
    public boolean isBlue(Cell source){
        return (get(source) == blue);
    }
    public boolean isBall(Cell source){
        return (isRed(source)||isBlue(source));
    }



    public abstract void putBall(Cell destination,PlayerColor color);


    public abstract void putRoad(Cell destination);
    public abstract void putWall(Cell destination);

    public void paint(Cell destination,PlayerColor color) {
        if(isBall(destination))
        {
            putBall(destination,color);
        }
    }
    public void paintAround(Cell destination,PlayerColor color) {
        for(int i = 0 ; i < 9 ; i++)
        {
            Cell near = new Cell(destination.x+paintArr[i].x,destination.y+paintArr[i].y);
            paint(near,color);
        }
    }
    public void makeNewBall(Cell destination,PlayerColor color) {
        putBall(destination,color);
        paintAround(destination,color);
    }
    public void moveTheBall(Turn turn, PlayerColor color) {
        putRoad(turn.source);
        putBall(turn.destination,color);
        paintAround(turn.destination,color);
    }



    public TurnState turnState(Turn turn, PlayerColor color) {
        int distance = turn.source.distance(turn.destination);
        char ColorC = color.getChar() ;

        if(get(turn.source) == ColorC && get(turn.destination) == road)
        {
            if (distance == 1 )
            {
                return TurnState.MakeNewBall;
            }
            else if (distance == 2)
            {
                return TurnState.MoveTheBall;
            }
        }
        return TurnState.InValid;
    }
    public void doTurn(Turn turn, PlayerColor color) {
        TurnState state = turnState(turn,color) ;
        if (state == TurnState.MakeNewBall)
        {
            makeNewBall(turn.destination,color);
        }
        else if (state == TurnState.MoveTheBall)
        {
            moveTheBall(turn,color);
        }
    }

    public int cellTurnNum(Cell source){
        int count = 0 ;
        for(int i = 0 ; i < 25 ; i++)
        {
            Cell destination = new Cell(source.x+Board.turnArr[i].x,source.y+Board.turnArr[i].y);
            if(isRoad(destination))
            {
                count++;
            }
        }
        return count;
    }
    public abstract int playerTurnNum(PlayerColor color);

    public boolean cellCanTurn(Cell source){
        for(int i = 0 ; i < 25 ; i++)
        {
            Cell destination = new Cell(source.x+Board.turnArr[i].x,source.y+Board.turnArr[i].y);
            if(isRoad(destination))
            {
                return true;
            }

        }
        return false;
    }
    public boolean playerCanTurn(PlayerColor color){
        List<Cell> sourceArr = (color == PlayerColor.red) ? redArr : blueArr ;
        for(int i = 0 ; i < sourceArr.size() ; i++)
        {
            Cell source = sourceArr.get(i);
            if(cellCanTurn(source))
            {
                return true;
            }
        }
        return false;
    }
    public boolean towCanTurn() {
        if(playerCanTurn(PlayerColor.red) && playerCanTurn(PlayerColor.blue))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public GameState state(){
        int redN = 0 , blueN = 0 ,roadN = 0;
        for(int i = 0 ; i < height ; i++)
        {
            for(int j = 0 ; j < width ; j++)
            {
                char c = get(new Cell(j,i)) ;
                if( c == road)
                {
                    roadN ++;
                }
                else if (c == red)
                {
                    redN ++;
                }
                else if (c == blue)
                {
                    blueN ++;
                }
            }
        }
        if (roadN == 0)
        {
            if(redN == blueN)
            {
                return GameState.draw;
            }
            else if ( redN > blueN )
            {
                return GameState.redWin;
            }
            else if ( redN < blueN )
            {
                return GameState.blueWin;
            }
        }
        else
        {
            if(redN == 0)
            {
                return GameState.blueWin;
            }
            else if (blueN == 0)
            {
                return GameState.redWin;
            }
            else if(towCanTurn())
            {
                return GameState.active;
            }
            else
            {
                if(!playerCanTurn(PlayerColor.red))
                {
                    int all = height * width;
                    if(all == 2 * redN)
                    {
                        return  GameState.draw;
                    }
                    else if(all > 2 * redN)
                    {
                        return  GameState.blueWin;
                    }
                    else if(all < 2 * redN)
                    {
                        return  GameState.redWin;
                    }
                }
                else if(!playerCanTurn(PlayerColor.blue))
                {
                    int all = height * width;
                    if(all == 2 * blueN)
                    {
                        return  GameState.draw;
                    }
                    else if(all > 2 * blueN)
                    {
                        return  GameState.redWin;
                    }
                    else if(all < 2 * blueN)
                    {
                        return  GameState.blueWin;
                    }
                }
            }
        }
        return GameState.active;
    }
    public boolean isFinished(){
        return (state() != GameState.active) ;
    }






}
