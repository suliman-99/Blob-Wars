package com.company.Players;

import com.company.Games.Game;
import com.company.Others.Enums.PlayerColor;

public abstract class Player {
    public Game game;
    public PlayerColor color;


    public void startGame(Game game, PlayerColor color){
        this.game = game;
        this.color = color;
    };

    public abstract void doTurn();

    public abstract void showBoard(PlayerColor color);

    public abstract void finishGame();

}
