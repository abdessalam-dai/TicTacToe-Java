package Computer;

import Player.Player;
import TicTacToe.TicTacToe;
import Cell.Cell;

import java.util.Random;

public class Computer extends Player {
    public static Random random = new Random();
    private final String difficulty;

    public Computer(String name, char symbol, String difficulty) {
        super(name, symbol);
        this.difficulty = difficulty;
    }

    // check if there is a possible winning cell
    public int possibleWinCell(TicTacToe gameCopy, char p) {
        // all possible winning patterns
        int[][] winPatterns = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };

        for (int[] winPattern : winPatterns) {
            int c1 = winPattern[0], c2 = winPattern[1], c3 = winPattern[2];
            Cell cell1 = new Cell(c1);
            Cell cell2 = new Cell(c2);
            Cell cell3 = new Cell(c3);

            int x1 = cell1.x, y1 = cell1.y;
            int x2 = cell2.x, y2 = cell2.y;
            int x3 = cell3.x, y3 = cell3.y;

            if (gameCopy.board[x1][y1] == p && gameCopy.board[x2][y2] == p && gameCopy.cellIsEmpty(x3, y3)) {
                return cell3.n;
            }

            if (gameCopy.board[x1][y1] == p && gameCopy.board[x3][y3] == p && gameCopy.cellIsEmpty(x2, y2)) {
                return cell2.n;
            }

            if (gameCopy.board[x2][y2] == p && gameCopy.board[x3][y3] == p && gameCopy.cellIsEmpty(x1, y1)) {
                return cell1.n;
            }
        }
        return -1; // no winning possible
    }

    // easy computer plays randomly
    public int getEasyMove(char[][] board) {
        // Create a copy of the game with that board
        TicTacToe gameCopy = TicTacToe.createGameCopy(this, board);

        int n = random.nextInt(9) + 1;
        Cell randomCell = new Cell(n);
        randomCell.setCoordinates(n);
        while (!gameCopy.cellIsEmpty(randomCell.x, randomCell.y)) {
            n = random.nextInt(9) + 1;
            randomCell.setCoordinates(n);
        }

        return n;
    }

    // hard computer always tries to win, and it blocks opponent's possible wins
    public int getHardMove(char[][] board) {
        // Create a copy of the game with that board
        TicTacToe gameCopy = TicTacToe.createGameCopy(this, board);

        // check if the computer has a possible winning cell to play
        int myPossibleWinCell = this.possibleWinCell(gameCopy, this.getSymbol());
        if (myPossibleWinCell != -1) {
            return myPossibleWinCell;
        }

        // check if the opponent has a possible winning cell, so that the computer can block it
        char opponentSymbol = this.getSymbol() == 'X' ? 'O' : 'X';
        int playerPossibleWinCell = this.possibleWinCell(gameCopy, opponentSymbol);
        if (playerPossibleWinCell != -1) {
            return playerPossibleWinCell;
        }

        // else, play like an easy computer
        return this.getEasyMove(board);
    }

    // get computer's move by looking at the game's board
    public int getMove(char[][] board) {
        switch (this.difficulty) {
            case "easy" -> {
                return this.getEasyMove(board);
            }
            case "hard" -> {
                return this.getHardMove(board);
            }
        }

        return 1;
    }
}
