package com.company.Evaluators;

import com.company.Boards.Board;
import com.company.Others.Enums.PlayerColor;


public abstract class Evaluator {

    public static int winValue = 100000;
    public static int looseValue = -100000;

    public static int lowStart = -999999;
    public static int highStart = 999999;

    public abstract int evaluate(Board board, boolean isMax, PlayerColor color);



}
