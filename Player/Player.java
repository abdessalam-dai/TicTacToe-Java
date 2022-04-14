package Player;

import Cell.Cell;
import TicTacToe.TicTacToe;

import java.util.Scanner;

public class Player {
    public static Scanner scanner = new Scanner(System.in);
    private String name; // player's name
    private char symbol; // player's symbol (X or O)
    private int numberOfWins; // number of times the player has won

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.numberOfWins = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public String getName() {
        return this.name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    // read a cell from the user
    public int getMove(char[][] board) {
        // Create a copy of the game with that board
        TicTacToe gameCopy = TicTacToe.createGameCopy(this, board);

        Cell cell = new Cell(1);
        String cellStr = "";
        boolean cellIsEmpty = false,
                cellIsValid = false;

        // read cell number from player
        // And check if the input is between 1 and 9 and if the cell is not taken
        while (!cellIsValid || !cellIsEmpty) {
            System.out.println(this.getName());
            System.out.print("Choose a cell between 1 and 9 : ");
            cellStr = scanner.next();
            cellIsValid = cellStr.matches("[1-9]");

            if (cellIsValid) {
                cell.setCoordinates(Integer.parseInt(cellStr));

                cellIsEmpty = gameCopy.cellIsEmpty(cell.x, cell.y);
                if (!cellIsEmpty) System.out.println("This cell is already taken !");
            }
        }

        return Integer.parseInt(cellStr);
    }
}
