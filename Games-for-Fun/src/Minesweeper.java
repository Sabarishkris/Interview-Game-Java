import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    static final char MINE = '*';
    static final char EMPTY = '.';
    static final char HIDDEN = '#';
    static final int[] DX = {-1, -1, -1, 0, 1, 1, 1, 0};
    static final int[] DY = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter game board dimensions (rows columns): ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        System.out.print("Enter number of mines: ");
        int mines = scanner.nextInt();

        char[][] board = new char[rows][columns];
        char[][] displayBoard = new char[rows][columns];

        initializeBoard(board, rows, columns, mines);
        initializeDisplayBoard(displayBoard, rows, columns);

        boolean gameOver = false;

        while (!gameOver) {
            displayBoard(displayBoard);
            System.out.print("Enter row and column to reveal (0-based index): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || row >= rows || col < 0 || col >= columns) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            if (board[row][col] == MINE) {
                System.out.println("Game Over! You hit a mine.");
                gameOver = true;
                revealAllMines(board, displayBoard);
            } else {
                revealCell(board, displayBoard, row, col);
                if (isWin(board, displayBoard)) {
                    System.out.println("Congratulations! You've cleared the board.");
                    gameOver = true;
                }
            }
        }

        displayBoard(displayBoard);
        scanner.close();
    }

    static void initializeBoard(char[][] board, int rows, int columns, int mines) {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = EMPTY;
            }
        }

        int minesPlaced = 0;
        while (minesPlaced < mines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(columns);
            if (board[row][col] != MINE) {
                board[row][col] = MINE;
                minesPlaced++;
            }
        }
    }

    static void initializeDisplayBoard(char[][] displayBoard, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                displayBoard[i][j] = HIDDEN;
            }
        }
    }

    static void displayBoard(char[][] displayBoard) {
        for (int i = 0; i < displayBoard.length; i++) {
            for (int j = 0; j < displayBoard[i].length; j++) {
                System.out.print(displayBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void revealCell(char[][] board, char[][] displayBoard, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || displayBoard[row][col] != HIDDEN) {
            return;
        }

        int mineCount = countMines(board, row, col);
        if (mineCount == 0) {
            displayBoard[row][col] = EMPTY;
            for (int i = 0; i < DX.length; i++) {
                revealCell(board, displayBoard, row + DX[i], col + DY[i]);
            }
        } else {
            displayBoard[row][col] = (char) ('0' + mineCount);
        }
    }

    static int countMines(char[][] board, int row, int col) {
        int count = 0;
        for (int i = 0; i < DX.length; i++) {
            int newRow = row + DX[i];
            int newCol = col + DY[i];
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && board[newRow][newCol] == MINE) {
                count++;
            }
        }
        return count;
    }

    static void revealAllMines(char[][] board, char[][] displayBoard) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == MINE) {
                    displayBoard[i][j] = MINE;
                }
            }
        }
    }

    static boolean isWin(char[][] board, char[][] displayBoard) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != MINE && displayBoard[i][j] == HIDDEN) {
                    return false;
                }
            }
        }
        return true;
    }
}
