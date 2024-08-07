import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadders {
    static final int BOARD_SIZE = 100;
    static final int[] LADDERS_START = {2, 7, 20, 27, 35, 46, 59, 68, 81};
    static final int[] LADDERS_END = {38, 14, 31, 84, 44, 65, 82, 91, 99};
    static final int[] SNAKES_HEAD = {19, 30, 47, 55, 60, 75, 83, 93, 97};
    static final int[] SNAKES_TAIL = {7, 12, 16, 23, 37, 28, 39, 64, 78};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[] playerPositions = {0, 0}; // Player 1 and Player 2 positions
        int currentPlayer = 0;

        System.out.println("Welcome to Snake and Ladders!");

        while (true) {
            System.out.println("Player " + (currentPlayer + 1) + "'s turn. Press enter to roll the dice.");
            scanner.nextLine();
            int diceRoll = random.nextInt(6) + 1;
            System.out.println("You rolled a " + diceRoll + ".");

            playerPositions[currentPlayer] += diceRoll;
            playerPositions[currentPlayer] = adjustPosition(playerPositions[currentPlayer]);

            System.out.println("Player " + (currentPlayer + 1) + " moved to square " + playerPositions[currentPlayer] + ".");

            if (playerPositions[currentPlayer] == BOARD_SIZE) {
                System.out.println("Player " + (currentPlayer + 1) + " wins!");
                break;
            }

            if (isLadder(playerPositions[currentPlayer])) {
                int ladderIndex = getLadderIndex(playerPositions[currentPlayer]);
                playerPositions[currentPlayer] = LADDERS_END[ladderIndex];
                System.out.println("Player " + (currentPlayer + 1) + " climbed a ladder to square " + playerPositions[currentPlayer] + ".");
            } else if (isSnake(playerPositions[currentPlayer])) {
                int snakeIndex = getSnakeIndex(playerPositions[currentPlayer]);
                playerPositions[currentPlayer] = SNAKES_TAIL[snakeIndex];
                System.out.println("Player " + (currentPlayer + 1) + " slid down a snake to square " + playerPositions[currentPlayer] + ".");
            }

            currentPlayer = (currentPlayer + 1) % 2; // Switch players
        }

        scanner.close();
    }

    static int adjustPosition(int position) {
        if (position > BOARD_SIZE) {
            return BOARD_SIZE - (position - BOARD_SIZE);
        }
        return position;
    }

    static boolean isLadder(int position) {
        for (int i = 0; i < LADDERS_START.length; i++) {
            if (LADDERS_START[i] == position) {
                return true;
            }
        }
        return false;
    }

    static int getLadderIndex(int position) {
        for (int i = 0; i < LADDERS_START.length; i++) {
            if (LADDERS_START[i] == position) {
                return i;
            }
        }
        return -1;
    }

    static boolean isSnake(int position) {
        for (int i = 0; i < SNAKES_HEAD.length; i++) {
            if (SNAKES_HEAD[i] == position) {
                return true;
            }
        }
        return false;
    }

    static int getSnakeIndex(int position) {
        for (int i = 0; i < SNAKES_HEAD.length; i++) {
            if (SNAKES_HEAD[i] == position) {
                return i;
            }
        }
        return -1;
    }
}

//How the Game Works:
//The game initializes the player positions to 0.
//Players take turns rolling the dice by pressing Enter.
//The dice roll determines how many squares the player moves forward.
//        Players' positions are adjusted to ensure they stay within the board limits.
//If a player lands on the start of a ladder, they climb to the end of the ladder.
//If a player lands on the head of a snake, they slide down to the tail of the snake.
//        7