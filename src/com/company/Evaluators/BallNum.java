package com.company.Evaluators;

import com.company.Boards.Board;
import com.company.Others.Enums.GameState;
import com.company.Others.Enums.PlayerColor;

public class BallNum extends Evaluator {

    public int evaluate(Board board, boolean isMax, PlayerColor color) {
        int ret = 0 ;
        GameState state = board.state() ;
        if(state == GameState.active)
        {
            int red = board.redArr.size();
            int blue = board.blueArr.size() ;
            ret = red - blue;
        }
        else
        {
            if(state == GameState.redWin)
            {
                ret = winValue ;
            }
            else if (state == GameState.blueWin)
            {
                ret = looseValue;
            }
            else if (state == GameState.draw)
            {
                ret = 0;
            }
        }
        // will reverse the ret if (   (    (color is blue) && (not max)    )  ||  (    (color is not blue) and (max)    )   )
        if( (color == PlayerColor.blue) == (isMax) )
        {
            ret = -ret;
        }
        return ret;
    }

}
