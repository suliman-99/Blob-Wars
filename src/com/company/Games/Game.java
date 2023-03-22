package com.company.Games;

import com.company.Boards.Board;
import com.company.Others.Enums.PlayerColor;
import com.company.Players.Player;


public abstract class Game {

    public Board board;
    public Player redPlayer;
    public Player bluePlayer;


    public void play(PlayerColor color) {

        redPlayer.showBoard(color);
        bluePlayer.showBoard(color);


        if(board.isFinished())
        {
            redPlayer.finishGame();
            bluePlayer.finishGame();
        }
        else
        {
            if (color == PlayerColor.blue) {
                bluePlayer.doTurn();
            } else if (color == PlayerColor.red) {
                redPlayer.doTurn();
            }

            PlayerColor otherColor = (color == PlayerColor.blue) ? PlayerColor.red : PlayerColor.blue ;
            play(otherColor);
        }


    }


    public abstract void start();

}
