import java.util.Random;
import java.util.Scanner;

public class Game2048 {
    static final int SIZE = 4;
    static final int WIN_TILE = 2048;
    static int[][] board = new int[SIZE][SIZE];
    static Random random = new Random();

    public static void main(String[] args) {
        initializeBoard();
        boolean gameEnd = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameEnd) {
            addRandomTile();
            displayBoard();

            if (isGameOver()) {
                System.out.println("Game Over! No more moves available.");
                gameEnd = true;
                continue;
            }

            System.out.print("Enter move (WASD): ");
            char move = scanner.nextLine().toUpperCase().charAt(0);

            if (moveBoard(move)) {
                if (hasWon()) {
                    displayBoard();
                    System.out.println("Congratulations! You've reached " + WIN_TILE + "!");
                    gameEnd = true;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    static void addRandomTile() {
        int row, col;
        do {
            row = random.nextInt(SIZE);
            col = random.nextInt(SIZE);
        } while (board[row][col] != 0);

        board[row][col] = random.nextInt(10) == 0 ? 4 : 2;
    }

    static void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean moveBoard(char direction) {
        boolean moved = false;

        switch (direction) {
            case 'W': moved = moveUp(); break;
            case 'A': moved = moveLeft(); break;
            case 'S': moved = moveDown(); break;
            case 'D': moved = moveRight(); break;
            default: return false;
        }

        return moved;
    }

    static boolean moveUp() {
        boolean moved = false;
        for (int col = 0; col < SIZE; col++) {
            int[] column = new int[SIZE];
            int index = 0;
            for (int row = 0; row < SIZE; row++) {
                if (board[row][col] != 0) {
                    column[index++] = board[row][col];
                }
            }
            moved = mergeAndMove(column) || moved;
            for (int row = 0; row < SIZE; row++) {
                board[row][col] = column[row];
            }
        }
        return moved;
    }

    static boolean moveLeft() {
        boolean moved = false;
        for (int row = 0; row < SIZE; row++) {
            int[] newRow = new int[SIZE];
            int index = 0;
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] != 0) {
                    newRow[index++] = board[row][col];
                }
            }
            moved = mergeAndMove(newRow) || moved;
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = newRow[col];
            }
        }
        return moved;
    }

    static boolean moveDown() {
        boolean moved = false;
        for (int col = 0; col < SIZE; col++) {
            int[] column = new int[SIZE];
            int index = SIZE - 1;
            for (int row = SIZE - 1; row >= 0; row--) {
                if (board[row][col] != 0) {
                    column[index--] = board[row][col];
                }
            }
            moved = mergeAndMove(column) || moved;
            for (int row = 0; row < SIZE; row++) {
                board[row][col] = column[row];
            }
        }
        return moved;
    }

    static boolean moveRight() {
        boolean moved = false;
        for (int row = 0; row < SIZE; row++) {
            int[] newRow = new int[SIZE];
            int index = SIZE - 1;
            for (int col = SIZE - 1; col >= 0; col--) {
                if (board[row][col] != 0) {
                    newRow[index--] = board[row][col];
                }
            }
            moved = mergeAndMove(newRow) || moved;
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = newRow[col];
            }
        }
        return moved;
    }

    static boolean mergeAndMove(int[] line) {
        boolean moved = false;
        for (int i = 0; i < SIZE - 1; i++) {
            if (line[i] != 0 && line[i] == line[i + 1]) {
                line[i] *= 2;
                line[i + 1] = 0;
                moved = true;
            }
        }
        int[] newLine = new int[SIZE];
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            if (line[i] != 0) {
                newLine[index++] = line[i];
            }
        }
        System.arraycopy(newLine, 0, line, 0, SIZE);
        return moved;
    }

    static boolean hasWon() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == WIN_TILE) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (board[i][j] == board[i][j + 1]) {
                    return false;
                }
            }
        }

        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE - 1; i++) {
                if (board[i][j] == board[i + 1][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
