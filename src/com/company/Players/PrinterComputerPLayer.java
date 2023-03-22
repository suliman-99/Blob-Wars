package com.company.Players;

import com.company.Algorithms.Algorithm;
import com.company.Games.Game;
import com.company.Others.Enums.PlayerColor;
import com.company.Others.Turn;

public class PrinterComputerPLayer extends Player {

    public Algorithm algorithm;
    public CommandPlayer printer;

    public PrinterComputerPLayer(Algorithm algorithm) {
        this.algorithm = algorithm;
        printer = new CommandPlayer();
    }


    public void startGame(Game game, PlayerColor color){
        this.game = game;
        this.color = color;
        printer.startGame(game,color);
    }



    public void doTurn(){
        Turn turn = algorithm.getBestTurn(game.board,color);
        game.board.doTurn(turn,color);
    }

    public void finishGame() {
        printer.finishGame();
        this.game = null;
        this.color = null;
    }

    public void showBoard(PlayerColor color){
        printer.showBoard(color);
    }
}
