package com.company.Players;

import com.company.Others.Cell;
import com.company.Others.Enums.GameState;
import com.company.Others.Enums.PlayerColor;
import com.company.Others.Turn;
import com.company.Others.Enums.TurnState;

import java.util.Scanner;

public class CommandPlayer extends Player {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String BG_ANSI_RESET = "\u001B[0m";
    public static final String BG_ANSI_BLACK = "\u001B[40m";
    public static final String BG_ANSI_RED = "\u001B[41m";
    public static final String BG_ANSI_GREEN = "\u001B[42m";
    public static final String BG_ANSI_YELLOW = "\u001B[43m";
    public static final String BG_ANSI_BLUE = "\u001B[44m";
    public static final String BG_ANSI_PURPLE = "\u001B[45m";
    public static final String BG_ANSI_CYAN = "\u001B[46m";
    public static final String BG_ANSI_WHITE = "\u001B[47m";





    public void doTurn() {

        Scanner scanner;
        scanner = new Scanner(System.in);

        boolean played = false;
        while(!played) {
            System.out.println( ( (color == PlayerColor.blue) ? ANSI_BLUE + "Blue Turn : " : ANSI_RED + "Red Turn : " ) );
            System.out.println( ( (color == PlayerColor.blue) ? ANSI_BLUE : ANSI_RED ) + "Enter The Source And The destination coordinates for your turn ");
            System.out.println("Source X : ");
            int sx = scanner.nextInt();
            sx--;
            System.out.println("Source Y : ");
            int sy = scanner.nextInt();
            sy--;
            System.out.println("Destination X : ");
            int dx = scanner.nextInt();
            dx--;
            System.out.println("Destination Y : " + BG_ANSI_RESET);
            int dy = scanner.nextInt();
            dy--;
            Cell source = new Cell(sx,sy);
            Cell destination = new Cell(dx,dy);
            Turn turn = new Turn(source,destination);
            if(game.board.turnState(turn,color) != TurnState.InValid) {
                game.board.doTurn(turn,color);
                played = true;
            }
            else
            {
                System.out.println("InValid !!!!!");
            }
        }


    }

    public void finishGame() {
            GameState state = game.board.state();
            if(state == GameState.draw) {
                System.out.println(" Draw -_- .");
            } else if (state == GameState.redWin) {
                System.out.println("The Red Player Win .");
            } else if (state == GameState.blueWin) {
                System.out.println("The Blue Player Win .");
            } else {
                System.out.println("Error !!!!!!!!!!!!!!!!!!!!!!!!!!!!! .");
            }
            this.game = null;
            this.color = null;
    }



    public void showBoard(PlayerColor color){
        System.out.println((color == PlayerColor.red) ? ( ANSI_BLUE + "Blue Played . " + BG_ANSI_RESET ) : ( ANSI_RED + "Red Played . " + BG_ANSI_RESET ) );
        printBoard();
    }
    public void printLine() {
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }
    public void printCell(char c){
        if(c == game.board.road)
        {
            System.out.print("   " + BG_ANSI_WHITE + ANSI_BLACK + " " + game.board.road + " " + ANSI_RESET + BG_ANSI_RESET);
        }
        else if(c == game.board.wall)
        {
            System.out.print("   " + BG_ANSI_BLACK + ANSI_BLACK + " " + game.board.wall + " " + ANSI_RESET + BG_ANSI_RESET);
        }
        else if(c == game.board.red)
        {
            System.out.print("   " + BG_ANSI_RED + ANSI_BLACK + " " + game.board.red + " " + ANSI_RESET + BG_ANSI_RESET);
        }
        else if(c == game.board.blue)
        {
            System.out.print("   " + BG_ANSI_BLUE + ANSI_BLACK + " " + game.board.blue + " " + ANSI_RESET + BG_ANSI_RESET);
        }
    }
    public void printBoard() {
        printLine();
        System.out.print("  ");
        for(int i = 1 ; i <= game.board.height ; i++)
        {
            System.out.print("    " + i + " ");
        }
        System.out.println();
        System.out.println();

        for(int i = 0 ; i < game.board.height ; i++)
        {
            System.out.print(i+1+" ");
            for(int j = 0 ; j < game.board.width ; j++)
            {
                printCell(game.board.get(new Cell(j,i)));
            }
            System.out.println();
            System.out.println();
        }
        printLine();
    }

}
