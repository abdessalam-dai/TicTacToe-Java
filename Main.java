import Computer.Computer;
import TicTacToe.TicTacToe;
import Player.Player;

import java.util.Scanner;

class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void startPlayerVSPlayerGame() {
        System.out.print("Player 1 (X) name : ");
        String playerName1 = scanner.next();

        System.out.print("Player 2 (O) name : ");
        String playerName2 = scanner.next();

        Player player1 = new Player(playerName1, 'X');
        Player player2 = new Player(playerName2, 'O');

        TicTacToe game = new TicTacToe(player1, player2, false);

        String playAgain = "y";
        while (playAgain.equals("y")) {
            game.play();
            System.out.print("Play again ? [y/n] ");
            playAgain = scanner.next();
        }
    }

    public static void startPlayerVSComputerGame(String difficulty) {
        // check if the play wants to play first (with the symbol X)
        System.out.print("Do you want to play first ? [y/n] ");
        String playFirst = scanner.next();
        char playerSymbol = 'O', computerSymbol = 'X';

        if (playFirst.equals("y")) {
            playerSymbol = 'X';
            computerSymbol = 'O';
        }

        System.out.print("Player (" + playerSymbol + ") name : ");
        String playerName = scanner.next();

        Player player = new Player(playerName, playerSymbol);
        Computer computer = new Computer("Computer (" + difficulty + ")", computerSymbol, difficulty);

        TicTacToe game;
        if (playFirst.equals("y")) {
            game = new TicTacToe(player, computer, false);
        } else {
            game = new TicTacToe(computer, player, false);
        }

        String playAgain = "y";
        while (playAgain.equals("y")) {
            game.play();
            System.out.print("Play again ? [y/n] ");
            playAgain = scanner.next();
        }
    }

    public static void main(String[] args) {
        boolean continuePlaying = true;
        while (continuePlaying) {
            System.out.println("--- WELCOME TO TIC TAC TOE ! ---\n");
            System.out.println("[1]. Player VS Player");
            System.out.println("[2]. Player VS Computer (Easy)");
            System.out.println("[3]. Player VS Computer (Hard)");
            System.out.println("[99]. Quit\n");

            System.out.print("command : ");
            String option = scanner.next().trim();

            switch (option) {
                case "1" -> {
                    startPlayerVSPlayerGame();
                }
                case "2" -> {
                    startPlayerVSComputerGame("easy");
                }
                case "3" -> {
                    startPlayerVSComputerGame("hard");
                }
                case "99" -> {
                    continuePlaying = false;
                }
            }
        }
    }
}