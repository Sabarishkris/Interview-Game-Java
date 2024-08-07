import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordSearch {
    static final int GRID_SIZE = 10;
    static final char[][] grid = new char[GRID_SIZE][GRID_SIZE];
    static final String[] words = {"JAVA", "PYTHON", "CPLUSPLUS", "RUBY", "HTML", "CSS", "JAVASCRIPT"};

    public static void main(String[] args) {
        initializeGrid();
        placeWords();
        fillGrid();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Word Search!");

        while (true) {
            displayGrid();
            System.out.print("Enter a word to search or 'exit' to quit: ");
            String word = scanner.nextLine().toUpperCase();

            if (word.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (findWord(word)) {
                System.out.println("Word '" + word + "' found!");
            } else {
                System.out.println("Word '" + word + "' not found.");
            }
        }

        scanner.close();
    }

    static void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.';
            }
        }
    }

    static void placeWords() {
        Random random = new Random();
        for (String word : words) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(GRID_SIZE);
                int col = random.nextInt(GRID_SIZE);
                boolean horizontal = random.nextBoolean();
                if (canPlaceWord(word, row, col, horizontal)) {
                    placeWord(word, row, col, horizontal);
                    placed = true;
                }
            }
        }
    }

    static boolean canPlaceWord(String word, int row, int col, boolean horizontal) {
        if (horizontal && col + word.length() <= GRID_SIZE) {
            for (int i = 0; i < word.length(); i++) {
                if (grid[row][col + i] != '.') {
                    return false;
                }
            }
            return true;
        } else if (!horizontal && row + word.length() <= GRID_SIZE) {
            for (int i = 0; i < word.length(); i++) {
                if (grid[row + i][col] != '.') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static void placeWord(String word, int row, int col, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < word.length(); i++) {
                grid[row][col + i] = word.charAt(i);
            }
        } else {
            for (int i = 0; i < word.length(); i++) {
                grid[row + i][col] = word.charAt(i);
            }
        }
    }

    static void fillGrid() {
        Random random = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == '.') {
                    grid[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }
    }

    static void displayGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean findWord(String word) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (findWordFromCell(word, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean findWordFromCell(String word, int row, int col) {
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

        for (int dir = 0; dir < 8; dir++) {
            int r = row, c = col;
            int index = 0;
            while (index < word.length() && r >= 0 && r < GRID_SIZE && c >= 0 && c < GRID_SIZE && grid[r][c] == word.charAt(index)) {
                r += dx[dir];
                c += dy[dir];
                index++;
            }
            if (index == word.length()) {
                return true;
            }
        }
        return false;
    }
}
