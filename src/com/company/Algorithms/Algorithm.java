package com.company.Algorithms;

import com.company.Boards.Board;
import com.company.Evaluators.Evaluator;
import com.company.Others.Enums.PlayerColor;
import com.company.Others.Turn;

public abstract class Algorithm {

    public Evaluator evaluator;
    public int level ;

    public Algorithm(Evaluator evaluator,int level){
        this.evaluator = evaluator;
        this.level = level;
    }

    public abstract Turn getBestTurn(Board board,PlayerColor color);

}
