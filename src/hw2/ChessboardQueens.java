package hw2;

import java.util.Scanner;

public class ChessboardQueens {

    private static int countSolutions = 0;
    private static boolean[] columns;
    private static boolean[] diagMajor;
    private static boolean[] diagMinor;
    private static final char[][] board = new char[8][8];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }
        scanner.close();

        columns = new boolean[8];
        diagMajor = new boolean[15]; // Number of Major Diagonals
        diagMinor = new boolean[15]; // Number of Minor Diagonals

        placeQueens(0); // Starting at row 0 and recursively increment by 1 later
        System.out.println(countSolutions);
    }

    private static void placeQueens(int row) {
        if (row == 8) {
            countSolutions++; // Once all rows are covered, it means all Queens are placed and one full solution is complete
            return;
        }

        for (int col = 0; col < 8; col++) {
            int majorIndex = row - col + 7;
            int minorIndex = row + col;
            if (board[row][col] != '*' && !columns[col] && !diagMajor[majorIndex] && !diagMinor[minorIndex])  {

                columns[col] = true;
                diagMajor[majorIndex] = true;
                diagMinor[minorIndex] = true;

                placeQueens(row + 1); // After one queen is placed, we move to the next one

                columns[col] = false;
                diagMajor[majorIndex] = false;
                diagMinor[minorIndex] = false;
            }
        }
    }
}
