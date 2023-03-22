package com.company.Algorithms;

import com.company.Boards.Board;
import com.company.Evaluators.Evaluator;
import com.company.Others.Cell;
import com.company.Others.Enums.PlayerColor;
import com.company.Others.Turn;

import java.util.List;


public class AlphaBeta extends Algorithm {

    public AlphaBeta(Evaluator evaluator,int level){
        super(evaluator,level);
    }


    public int alphaBetaTry(Board board, int depth, boolean isMax, int alpha , int beta,PlayerColor color) {

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
                            int temp = alphaBetaTry(newBoard,depth-1,false,alpha,beta,color2);
                            if(temp == Evaluator.winValue || temp >= beta)
                            {
                                return temp;
                            }
                            else if(temp > alpha)
                            {
                                alpha = temp;
                            }
                        }

                    }
                }
                return alpha;
            }
            else
            {
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
                            newBoard.doTurn(turn, color);
                            int temp = alphaBetaTry(newBoard,depth-1,true,alpha,beta,color2);
                            if(temp == Evaluator.looseValue || temp <= alpha)
                            {
                                return temp;
                            }
                            else if(temp < beta)
                            {
                                beta = temp;
                            }

                        }

                    }
                }
                return beta;
            }
        }
    }


    public Turn getBestTurn(Board board,PlayerColor maxColor){

        PlayerColor minColor = maxColor.getOther() ;

        List<Cell> sourceArr = (maxColor == PlayerColor.red) ? board.redArr : board.blueArr ;

        int alpha = Evaluator.lowStart , beta = Evaluator.highStart ;
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
                    int temp = alphaBetaTry(newBoard,level,false,alpha,beta,minColor);
                    if(temp > alpha)
                    {
                        alpha = temp;
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
