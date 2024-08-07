import java.util.Random;
import java.util.Scanner;

public class MazeRunner {
    static final int MAZE_SIZE = 10;
    static final char WALL = '#';
    static final char START = 'S';
    static final char EXIT = 'E';
    static final char PATH = '.';

    static char[][] maze = new char[MAZE_SIZE][MAZE_SIZE];
    static int playerRow;
    static int playerCol;

    public static void main(String[] args) {
        generateMaze();
        placePlayer();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Maze Runner!");

        while (true) {
            displayMaze();

            System.out.print("Enter your move (WASD or exit): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("Game over. Exiting...");
                break;
            }

            char move = input.charAt(0);
            if (isValidMove(move)) {
                movePlayer(move);
                if (maze[playerRow][playerCol] == EXIT) {
                    System.out.println("Congratulations! You've reached the exit.");
                    break;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    static void generateMaze() {
        Random random = new Random();

        // Create an empty maze
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                maze[i][j] = WALL;
            }
        }

        // Place start and exit points
        maze[0][0] = START;
        maze[MAZE_SIZE - 1][MAZE_SIZE - 1] = EXIT;

        // Create a path from start to exit
        int row = 0;
        int col = 0;
        while (row != MAZE_SIZE - 1 || col != MAZE_SIZE - 1) {
            int direction = random.nextInt(2); // 0 for vertical, 1 for horizontal
            if (direction == 0) { // vertical
                int newRow = row + (random.nextBoolean() ? 1 : -1); // move up or down
                if (newRow >= 0 && newRow < MAZE_SIZE) {
                    maze[newRow][col] = PATH;
                    row = newRow;
                }
            } else { // horizontal
                int newCol = col + (random.nextBoolean() ? 1 : -1); // move left or right
                if (newCol >= 0 && newCol < MAZE_SIZE) {
                    maze[row][newCol] = PATH;
                    col = newCol;
                }
            }
        }
    }

    static void placePlayer() {
        playerRow = 0;
        playerCol = 0;
    }

    static void displayMaze() {
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isValidMove(char move) {
        switch (move) {
            case 'W':
                return playerRow > 0 && maze[playerRow - 1][playerCol] != WALL;
            case 'A':
                return playerCol > 0 && maze[playerRow][playerCol - 1] != WALL;
            case 'S':
                return playerRow < MAZE_SIZE - 1 && maze[playerRow + 1][playerCol] != WALL;
            case 'D':
                return playerCol < MAZE_SIZE - 1 && maze[playerRow][playerCol + 1] != WALL;
            default:
                return false;
        }
    }

    static void movePlayer(char move) {
        // Update the player's position based on the move
        switch (move) {
            case 'W':
                if (isValidMove(move)) {
                    maze[playerRow][playerCol] = PATH;
                    playerRow--;
                }
                break;
            case 'A':
                if (isValidMove(move)) {
                    maze[playerRow][playerCol] = PATH;
                    playerCol--;
                }
                break;
            case 'S':
                if (isValidMove(move)) {
                    maze[playerRow][playerCol] = PATH;
                    playerRow++;
                }
                break;
            case 'D':
                if (isValidMove(move)) {
                    maze[playerRow][playerCol] = PATH;
                    playerCol++;
                }
                break;
        }
        // Mark the new player position in the maze
        maze[playerRow][playerCol] = START;
    }
}
