package TicTacToe;

import Cell.Cell;
import Computer.Computer;
import Player.Player;

import java.util.Scanner;

public class TicTacToe {
    public static Scanner scanner = new Scanner(System.in);
    public Player player1;
    public Player player2;
    public char[][] board = new char[3][3];
    public boolean ended;

    public TicTacToe(Player player1, Player player2, boolean ended) {
        this.player1 = player1;
        this.player2 = player2;
        this.ended = ended;
    }

    // static method for creating a copy of the game (given its board)
    public static TicTacToe createGameCopy(Player p, char[][] board) {
        TicTacToe gameCopy;
        if (p.getSymbol() == 'X') {
            gameCopy = new TicTacToe(p, new Player("Opponent", 'O'), false);
        } else {
            gameCopy = new TicTacToe(new Player("Opponent", 'X'), p, false);
        }
        gameCopy.setBoard(board);
        return gameCopy;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
    public void setBoard(char[][] board) {
        this.board = board;
    }

    // check if the cell (x, y) is empty
    public boolean cellIsEmpty(int x, int y) {
        return this.board[x][y] == ' ';
    }

    // check if there are some cells available (at least one)
    public boolean cellsAvailable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.cellIsEmpty(i, j)) return true;
            }
        }
        return false;
    }

    // play a move by player at position x, y
    public void playMove(char player, int x, int y) {
        if (this.cellIsEmpty(x, y)) {
            this.board[x][y] = player;
        }
    }

    // reset board
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    // Display board
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            res.append("-".repeat(12)).append("\n").append(" |");
            for (int j = 0; j < 3; j++) {
                res.append(this.board[i][j]).append(" |");
            }
            res.append("\n");
        }
        res.append("-".repeat(12));
        return res.toString();
    }

    // check if a player has won
    public boolean hasWon(char player) {
        // verify all possible winning patterns
        return (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] == player) || (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player) || (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] == player) || (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] == player) || (board[2][0] == board[1][1] && board[0][0] == board[0][2] && board[2][0] == player) || (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] == player) || (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] == player) || (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] == player);
    }

    public void play() {
        this.setEnded(false);
        this.resetBoard();
        System.out.println(this);
        Player turn = this.player1;

        while (!this.ended) {
            int n = 1;
            Cell cell = new Cell(n);

            // get a move from the player / computer
            n = turn.getMove(this.board);
            cell.setCoordinates(n);
            this.playMove(turn.getSymbol(), cell.x, cell.y);

            // display the board
            System.out.println(this);

            if (this.hasWon(turn.getSymbol())) {
                // check for the winner
                System.out.println("GAME OVER ! " + turn.getName() + " has won the game");
                this.ended = true;
            } else if (!this.cellsAvailable() && !this.hasWon('X') && !this.hasWon('Y')) {
                // check if there is a draw
                System.out.println("GAME OVER ! Draw");
                this.ended = true;
            }

            // switch turns
            turn = turn == this.player1 ? this.player2 : this.player1;
        }
    }
}