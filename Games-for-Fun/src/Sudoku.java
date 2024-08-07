import java.util.Scanner;

public class Sudoku {
    static final int SIZE = 9;
    static final int SUBGRID_SIZE = 3;
    static int[][] board = new int[SIZE][SIZE];

    public static void main(String[] args) {
        // A partially filled board for demonstration purposes
        int[][] initialBoard = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        board = initialBoard;

        Scanner scanner = new Scanner(System.in);
        boolean gameEnd = false;

        while (!gameEnd) {
            displayBoard();
            System.out.print("Enter row (1-9), column (1-9), and number (1-9) separated by spaces, or 'exit' to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                gameEnd = true;
                continue;
            }

            String[] parts = input.split(" ");
            if (parts.length == 3) {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                int number = Integer.parseInt(parts[2]);

                if (isValidMove(row, col, number)) {
                    board[row][col] = number;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Invalid input. Try again.");
            }

            if (isBoardFull()) {
                displayBoard();
                System.out.println("Congratulations! You've completed the Sudoku.");
                gameEnd = true;
            }
        }

        scanner.close();
    }

    static void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            if (i % SUBGRID_SIZE == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % SUBGRID_SIZE == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (board[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static boolean isValidMove(int row, int col, int number) {
        if (board[row][col] != 0) return false;

        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == number || board[i][col] == number) {
                return false;
            }
        }

        int subGridRowStart = (row / SUBGRID_SIZE) * SUBGRID_SIZE;
        int subGridColStart = (col / SUBGRID_SIZE) * SUBGRID_SIZE;
        for (int i = subGridRowStart; i < subGridRowStart + SUBGRID_SIZE; i++) {
            for (int j = subGridColStart; j < subGridColStart + SUBGRID_SIZE; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
