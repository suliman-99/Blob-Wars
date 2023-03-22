package com.company.Algorithms;

import com.company.Boards.Board;
import com.company.Evaluators.Evaluator;
import com.company.Others.Cell;
import com.company.Others.Enums.PlayerColor;
import com.company.Others.Turn;

import java.util.List;


public class MiniMax extends Algorithm {

    public MiniMax(Evaluator evaluator,int level){
        super(evaluator,level);
    }


    public int miniMaxTry(Board board, int depth, boolean isMax ,PlayerColor color) {

        List<Cell> sourceArr = (color == PlayerColor.red) ? board.redArr : board.blueArr ;

        PlayerColor color2 = color.getOther() ;

        if(board.isFinished() || depth <= 0)
        {
            return evaluator.evaluate(board,isMax,color);
        }
        else
        {
            if (isMax)
            {
                int r = (int)(Math.random() * 25);
                int bestValue = Evaluator.lowStart;
                for(int i = 0 ; i < sourceArr.size() ; i++)
                {
                    Cell source = sourceArr.get(i);
                    for(int k = 0 ; k < 25 ; k++)
                    {
                        Cell destination = new Cell(source.x+Board.turnArr[(k+r)%25].x,source.y+Board.turnArr[(k+r)%25].y);
                        if(board.isRoad(destination))
                        {
                            Board newBoard = board.copy();
                            Turn turn = new Turn(source,destination);
                            newBoard.doTurn(turn,color);
                            int temp = miniMaxTry(newBoard,depth-1,false,color2);
                            if(temp == Evaluator.winValue)
                            {
                                return temp;
                            }
                            else if(temp > bestValue)
                            {
                                bestValue = temp;
                            }
                        }
                    }
                }
                return bestValue;
            }
            else
            {
                int r = (int)(Math.random() * 25);
                int bestValue = Evaluator.highStart;
                for(int i = 0 ; i < sourceArr.size() ; i++)
                {
                    Cell source = sourceArr.get(i);
                    for(int k = 0 ; k < 25 ; k++)
                    {
                        Cell destination = new Cell(source.x+Board.turnArr[(k+r)%25].x,source.y+Board.turnArr[(k+r)%25].y);
                        if(board.isRoad(destination))
                        {
                            Board newBoard = board.copy();
                            Turn turn = new Turn(source,destination);
                            newBoard.doTurn(turn, color);
                            int temp = miniMaxTry(newBoard,depth-1,true,color2);
                            if(temp == Evaluator.looseValue)
                            {
                                return temp;
                            }
                            else if(temp < bestValue)
                            {
                                bestValue = temp;
                            }
                        }
                    }
                }
                return bestValue;
            }
        }
    }


    public Turn getBestTurn(Board board,PlayerColor maxColor){

        PlayerColor minColor = maxColor.getOther() ;

        List<Cell> sourceArr = (maxColor == PlayerColor.red) ? board.redArr : board.blueArr ;

        int bestValue = Evaluator.lowStart ;
        Cell bestSource = new Cell(-1,-1);
        Cell bestDestination = new Cell(-1,-1);

        int r = (int)(Math.random() * 25);
        for(int i = 0 ; i < sourceArr.size() ; i++)
        {
            Cell source = sourceArr.get(i);
            for(int k = 0 ; k < 25 ; k++)
            {
                Cell destination = new Cell(source.x+Board.turnArr[(k+r)%25].x,source.y+Board.turnArr[(k+r)%25].y);
                if(board.isRoad(destination))
                {
                    Board newBoard = board.copy();
                    Turn turn = new Turn(source,destination);
                    newBoard.doTurn(turn, maxColor);
                    int temp = miniMaxTry(newBoard,level,false,minColor);
                    if(temp > bestValue)
                    {
                        bestValue = temp;
                        bestSource = source;
                        bestDestination = destination;
                    }
                }
            }
        }
        Turn turn = new Turn(bestSource,bestDestination);
        return turn;
    }



}
