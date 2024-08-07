import java.util.Random;
import java.util.Scanner;

public class WordGuessingGame {
    static final String[] WORDS = {"apple", "banana", "orange", "grape", "kiwi", "pear"};
    static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Random random = new Random();
        String wordToGuess = WORDS[random.nextInt(WORDS.length)];
        char[] guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        System.out.println("Welcome to Word Guessing Game!");
        System.out.println("Try to guess the word:");

        while (attempts < MAX_ATTEMPTS) {
            System.out.println("Word: " + String.valueOf(guessedWord));
            System.out.print("Enter a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);
            boolean found = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    found = true;
                }
            }
            if (!found) {
                attempts++;
                System.out.println("Incorrect guess! Remaining attempts: " + (MAX_ATTEMPTS - attempts));
            } else {
                System.out.println("Correct guess!");
            }
            if (String.valueOf(guessedWord).equals(wordToGuess)) {
                System.out.println("Congratulations! You guessed the word: " + wordToGuess);
                break;
            }
        }

        if (attempts == MAX_ATTEMPTS) {
            System.out.println("Sorry, you couldn't guess the word. The correct word was: " + wordToGuess);
        }

        scanner.close();
    }
}
