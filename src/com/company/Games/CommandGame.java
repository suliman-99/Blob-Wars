package com.company.Games;

import com.company.Algorithms.Algorithm;
import com.company.Algorithms.AlphaBeta;
import com.company.Algorithms.MiniMax;
import com.company.Boards.Board;
import com.company.Evaluators.BallNum;
import com.company.Evaluators.Evaluator;
import com.company.Evaluators.ValidTurnNum;
import com.company.Others.Enums.PlayerColor;
import com.company.Players.CommandPlayer;
import com.company.Players.ComputerPlayer;
import com.company.Players.Player;
import com.company.Players.PrinterComputerPLayer;

import java.io.File;
import java.util.Scanner;

public class CommandGame extends Game {

    public void start(){

        Scanner scanner;

        System.out.println("Choose The Scan Method Of The Game Settings : ");
        System.out.println("From The File : 1 . ");
        System.out.println("From The Terminal : 2 . ");
        Scanner scnr = new Scanner(System.in);
        int scn = scnr.nextInt();

        if(scn == 1)
        {
            System.out.println("Choose The File : ");
            System.out.println(" 1 : 1.txt :    You                      VS    (BallNum)(LVL 2)         (default)  ");
            System.out.println(" 2 : 2.txt :    You                      VS    (ValidTurnNum)(LVL 2)               ");
            System.out.println(" 3 : 3.txt :    (BallNum)(LVL 2)         VS    (ValidTurnNum)(LVL 2)               ");
            System.out.println(" 4 : 4.txt :    (BallNum)(LVL 2)         VS    (BallNum)(LVL 1)                    ");
            System.out.println(" 5 : 5.txt :    (ValidTurnNum)(LVL 2)    VS    (ValidTurnNum)(LVL 1)               ");
            System.out.println(" 6 : 6.txt :    You                      VS    You                                 ");
            int file = scnr.nextInt();
            if(file > 5 || file < 1)
            {
                file = 1 ;
            }

            try{
                File text = new File("src/com/company/"+file+".txt");
                scanner = new Scanner(text);

            }catch (Exception e) {
                System.out.println(e);
                scanner = new Scanner(System.in);
            }
        }
        else
        {
            scanner = new Scanner(System.in);
        }


        System.out.println("Enter The height Of The Board :");
        int height = scanner.nextInt();
        System.out.println("Enter The Width Of The Board :");
        int width = scanner.nextInt();
        Board board;
        Player redPlayer;
        Player bluePlayer;
        System.out.println("Choose Play Mode :");
        System.out.println("With Computer : 1 . ");
        System.out.println("Tow Players : 2 . ");
        System.out.println("Tow Computers : 3 . ");
        int mode = scanner.nextInt();
        if(mode == 3)
        {
            board = Board.defaultFastBoard(height,width);
            System.out.println("Enter The Level Of The Blue Computer :");
            int level2 = scanner.nextInt();
            System.out.println("Enter The Level Of The Red Computer :");
            int level1 = scanner.nextInt();

            Evaluator evaluator2;
            System.out.println("Choose The Evaluator Method Of The Blue Computer : ");
            System.out.println("Ball Number  :  1 . (Recommended) ");
            System.out.println("Valid Turn Number  :  2 . ");
            int evC2 = scanner.nextInt();
            if(evC2 == 2)
            {
                evaluator2 = new ValidTurnNum();
            }
            else
            {
                evaluator2 = new BallNum();
            }

            Evaluator evaluator1;
            System.out.println("Choose The Evaluator Method Of The Red Computer : ");
            System.out.println("Ball Number  :  1 . (Recommended) ");
            System.out.println("Valid Turn Number  :  2 . ");
            int evC1 = scanner.nextInt();
            if(evC1 == 2)
            {
                evaluator1 = new ValidTurnNum();
            }
            else
            {
                evaluator1 = new BallNum();
            }

            Algorithm algorithm2;
            System.out.println("Choose The Algorithm Of The Blue Computer : ");
            System.out.println("Mini Max  :  1 . ");
            System.out.println("Alpha Beta  :  2 . (Recommended) ");
            int algoC2 = scanner.nextInt();
            if(algoC2 == 1)
            {
                algorithm2 = new MiniMax(evaluator2,level2);
            }
            else
            {
                algorithm2 = new AlphaBeta(evaluator2,level2);
            }
            bluePlayer = new PrinterComputerPLayer(algorithm2);

            Algorithm algorithm1;
            System.out.println("Choose The Algorithm Of The Red Computer : ");
            System.out.println("Mini Max  :  1 . ");
            System.out.println("Alpha Beta  :  2 . (Recommended) ");
            int algoC1 = scanner.nextInt();
            if(algoC1 == 1)
            {
                algorithm1 = new MiniMax(evaluator1,level1);
            }
            else
            {
                algorithm1 = new AlphaBeta(evaluator1,level1);
            }
            redPlayer = new ComputerPlayer(algorithm1);
        }
        else if (mode == 2)
        {
            redPlayer = new CommandPlayer();
            bluePlayer = new CommandPlayer();
            board = Board.defaultFastBoard(height,width);

        }
        else
        {
            System.out.println("Enter The Level Of The Game :");
            int level = scanner.nextInt();

            Evaluator evaluator;
            System.out.println("Choose The Evaluator Method : ");
            System.out.println("Ball Number  :  1 . (Recommended)  ");
            System.out.println("Valid Turn Number  :  2 .");
            int evC = scanner.nextInt();
            if (evC == 2)
            {
                evaluator = new ValidTurnNum();
                board = Board.defaultSlowBoard (height,width);
            }
            else
            {
                evaluator = new BallNum();
                board = Board.defaultFastBoard(height,width);
            }

            Algorithm algorithm;
            System.out.println("Choose The Algorithm : ");
            System.out.println("Mini Max  :  1 . ");
            System.out.println("Alpha Beta  :  2 . (Recommended) ");
            int algoC = scanner.nextInt();
            if(algoC == 1)
            {
                algorithm = new MiniMax(evaluator,level);
            }
            else
            {
                algorithm = new AlphaBeta(evaluator,level);
            }
            redPlayer = new ComputerPlayer(algorithm);
            bluePlayer = new CommandPlayer();
            System.out.println("Computer Color Is Red");
        }
        System.out.println("Choose The Start Turn Color : ");
        System.out.println("blue : 1 . ");
        System.out.println("red : 2 . ");
        int c = scanner.nextInt();
        PlayerColor startColor = (c == 1) ? PlayerColor.blue : PlayerColor.red ;

        this.board = board;
        this.bluePlayer = bluePlayer;
        this.redPlayer = redPlayer;
        bluePlayer.startGame(this, PlayerColor.blue);
        redPlayer.startGame(this,PlayerColor.red);

        play(startColor);
    }

}
