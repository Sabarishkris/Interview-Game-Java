import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class SnakeGame {
    static final char EMPTY = '.';
    static final char SNAKE = 'S';
    static final char FOOD = 'F';
    static int score = 0;

    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    static final String DIRECTIONS = "RDLU";

    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter game box dimensions (n m): ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] box = new char[n][m];
        initializeBox(box);

        LinkedList<Point> snake = new LinkedList<>();
        snake.add(new Point(n / 2, m / 2));
        box[n / 2][m / 2] = SNAKE;

        placeFood(box, random);

        boolean gameOver = false;
        while (!gameOver) {
            displayBox(box);

            System.out.print("Enter direction (R, D, L, U): ");
            char direction = scanner.next().charAt(0);
            int dirIndex = DIRECTIONS.indexOf(direction);
            if (dirIndex == -1) {
                System.out.println("Invalid direction! Use R, D, L, U.");
                continue;
            }

            Point head = snake.getFirst();
            Point newHead = new Point(head.x + DX[dirIndex], head.y + DY[dirIndex]);

            if (newHead.x < 0 || newHead.x >= n || newHead.y < 0 || newHead.y >= m || box[newHead.x][newHead.y] == SNAKE) {
                System.out.println("Game Over!");
                gameOver = true;
            } else {
                if (box[newHead.x][newHead.y] == FOOD) {
                    score += 10;
                    placeFood(box, random);
                } else {
                    Point tail = snake.removeLast();
                    box[tail.x][tail.y] = EMPTY;
                }
                snake.addFirst(newHead);
                box[newHead.x][newHead.y] = SNAKE;
            }

            System.out.println("Score: " + score);
        }

        scanner.close();
    }

    static void initializeBox(char[][] box) {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                box[i][j] = EMPTY;
            }
        }
    }

    static void displayBox(char[][] box) {
        for (char[] row : box) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    static void placeFood(char[][] box, Random random) {
        int n = box.length;
        int m = box[0].length;
        int foodX, foodY;
        do {
            foodX = random.nextInt(n);
            foodY = random.nextInt(m);
        } while (box[foodX][foodY] != EMPTY);
        box[foodX][foodY] = FOOD;
    }
}
