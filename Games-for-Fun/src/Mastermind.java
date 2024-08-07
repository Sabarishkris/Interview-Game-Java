import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    static final int CODE_LENGTH = 4;
    static final int MAX_GUESSES = 10;
    static final char[] COLORS = {'R', 'G', 'B', 'Y', 'O', 'P'};

    static char[] secretCode;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Generate secret code
        secretCode = new char[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            secretCode[i] = COLORS[random.nextInt(COLORS.length)];
        }

        System.out.println("Welcome to Mastermind! Try to guess the secret code.");
        System.out.println("The code consists of " + CODE_LENGTH + " colors: R G B Y O P");

        boolean gameWon = false;

        for (int guess = 1; guess <= MAX_GUESSES; guess++) {
            System.out.println("\nGuess #" + guess + ":");
            char[] guessCode = scanner.next().toUpperCase().toCharArray();

            if (guessCode.length != CODE_LENGTH || !isValidCode(guessCode)) {
                System.out.println("Invalid guess. Please enter " + CODE_LENGTH + " colors.");
                continue;
            }

            int exactMatches = countExactMatches(guessCode);
            int partialMatches = countPartialMatches(guessCode);

            System.out.println("Exact matches: " + exactMatches);
            System.out.println("Partial matches: " + partialMatches);

            if (exactMatches == CODE_LENGTH) {
                gameWon = true;
                break;
            }
        }

        if (gameWon) {
            System.out.println("\nCongratulations! You've guessed the secret code: " + String.valueOf(secretCode));
        } else {
            System.out.println("\nGame over! You've run out of guesses. The secret code was: " + String.valueOf(secretCode));
        }

        scanner.close();
    }

    static boolean isValidCode(char[] code) {
        for (char c : code) {
            boolean isValid = false;
            for (char color : COLORS) {
                if (c == color) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                return false;
            }
        }
        return true;
    }

    static int countExactMatches(char[] guessCode) {
        int count = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guessCode[i] == secretCode[i]) {
                count++;
            }
        }
        return count;
    }

    static int countPartialMatches(char[] guessCode) {
        int count = 0;
        boolean[] secretCodeUsed = new boolean[CODE_LENGTH];
        boolean[] guessCodeUsed = new boolean[CODE_LENGTH];

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guessCode[i] == secretCode[i]) {
                secretCodeUsed[i] = true;
                guessCodeUsed[i] = true;
            }
        }

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (!guessCodeUsed[i]) {
                for (int j = 0; j < CODE_LENGTH; j++) {
                    if (!secretCodeUsed[j] && guessCode[i] == secretCode[j]) {
                        count++;
                        secretCodeUsed[j] = true;
                        break;
                    }
                }
            }
        }

        return count;
    }
}
