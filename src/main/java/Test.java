/**
 * Implementation of the game occurs in Test class.
 */

import java.util.Scanner;
import java.util.Random;

public class Test {

    /**
     * main method for the user to play and test the game
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type how many rows the puzzle will consists of: ");
        int size = scanner.nextInt(); // size of one row/column
        scanner.nextLine();
        String line;
        char[][] matrix = new char[size][size]; // n x n matrix
        int row = 0;
        for (int index = 0; index < size; index++) { // traverses all indexes of the matrix
            System.out.println("Please type the next line (write big o (O) instead of zero): ");
            line = scanner.nextLine();
            for (int column = 0; column < size; column++) { // traverses all columns of the current row
                char c = line.charAt(column);
                matrix[row][column] = c; // char at (current row, current column) of the matrix, which can be O, X or T
            }
            row = row + 1;
        }
        System.out.println("Correct puzzle is last printed puzzle.");
        puzzle(matrix);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("Execution time is " + executionTime + " milliseconds.");
    }

    /**
     * controls if the n x n matrix obeys the rules
     * @param pieces n x n matrix of pieces
     * @return true if the n x n matrix obeys the rules, false otherwise
     */
    public static boolean valid(Piece[][] pieces) {
        if (pieces == null) {
            return false;
        }
        int size = pieces.length;
        int numberOfTrees = 0;
        int numberOfTents = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Piece piece = pieces[row][column];
                if (piece.getType() == 'X') {
                    numberOfTents++;
                    if (row > 0 && column > 0) {
                        if (pieces[row-1][column-1].getType() == 'X') {
                            return false;
                        }
                    }
                    if (column > 0 && row < size-1) {
                        if (pieces[row+1][column-1].getType() == 'X') {
                            return false;
                        }
                    }
                    if (row < size-1 && column < size-1) {
                        if (pieces[row+1][column+1].getType() == 'X') {
                            return false;
                        }
                    }
                    if (row > 0 && column < size-1) {
                        if (pieces[row-1][column+1].getType() == 'X') {
                            return false;
                        }
                    }
                    if (row < size-1) {
                        if (pieces[row+1][column].getType() == 'X') {
                            return false;
                        }
                    }
                    if (column < size-1) {
                        if (pieces[row][column+1].getType() == 'X') {
                            return false;
                        }
                    }
                    if (row > 0) {
                        if (pieces[row-1][column].getType() == 'X') {
                            return false;
                        }
                    }
                    if (column > 0) {
                        if (pieces[row][column-1].getType() == 'X') {
                            return false;
                        }
                    }
                }
                if (piece.getType() == 'T') {
                    numberOfTrees++;
                }
            }
        }
        if (numberOfTrees != numberOfTents) {
            return false;
        }
        return true;
    }

    /**
     * @param matrix n x n char matrix having the value of O, X or T
     * @return n x n puzzle consisting of pieces
     */
    public static Piece[][] puzzle(char[][] matrix) {
        int length = matrix[0].length;
        Piece[][] puzzle = new Piece[length][length];
        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                char ch = matrix[row][column];
                Piece piece = new Piece(ch);
                if (ch == 'T') {
                    if (column < length-1) {
                        String string = "right";
                        piece.legalTents.add(string);
                    }
                    if (column > 0) {
                        String string = "left";
                        piece.legalTents.add(string);
                    }
                    if (row < length-1) {
                        String string = "bottom";
                        piece.legalTents.add(string);
                    }
                    if (row > 0) {
                        String string = "top";
                        piece.legalTents.add(string);
                    }
                }
                puzzle[row][column] = piece;
            }
        }
        State state = new State(puzzle);
        Queue queue = new Queue(1000);
        queue.enqueue(new Token(state));
        int count = 0;
        Piece[][] temp = new Piece[length][length];
        while (!queue.isEmpty() && count != Math.pow(length, length)) {
            state = queue.dequeue().getData();
            if (valid(state.getPieces())) {
                return state.getPieces();
            }
            for (int row = 0; row < temp.length; row++) {
                for (int column = 0; column < temp[row].length; column++) {
                    char ch = puzzle[row][column].getType();
                    temp[row][column] = new Piece(ch);
                }
            }
            Random random = new Random();
            for (int row = 0; row < temp.length; row++) {
                for (int column = 0; column < temp[row].length; column++) {
                    if (temp[row][column].getType() == 'T') {
                        int size = puzzle[row][column].legalTents.size();
                        int rand = random.nextInt(size);
                        String direction = puzzle[row][column].legalTents.get(rand);
                        if (direction.equals("top")) {
                            temp[row-1][column].setType('X');
                        }
                        if (direction.equals("bottom")) {
                            temp[row+1][column].setType('X');
                        }
                        if (direction.equals("right")) {
                            temp[row][column+1].setType('X');
                        }
                        if (direction.equals("left")) {
                            temp[row][column-1].setType('X');
                        }
                    }
                }
            }
            count++;
            for (Piece[] pieces : temp) {
                for (Piece element: pieces) {
                    System.out.print(element.getType());
                }
                System.out.println();
            }
            System.out.println();
            Token token = new Token(new State(temp));
            queue.enqueue(token);
        }
        System.out.println("No legal solution");
        return null;
    }
}
