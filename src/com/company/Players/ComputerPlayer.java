package com.company.Players;

import com.company.Algorithms.Algorithm;
import com.company.Others.Enums.PlayerColor;
import com.company.Others.Turn;


public class ComputerPlayer extends Player {

    public Algorithm algorithm;

    public ComputerPlayer(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void doTurn(){
        Turn turn = algorithm.getBestTurn(game.board,color);
        game.board.doTurn(turn,color);
    }

    public void finishGame() {
        this.game = null;
        this.color = null;
    }

    public void showBoard(PlayerColor color){}
}
