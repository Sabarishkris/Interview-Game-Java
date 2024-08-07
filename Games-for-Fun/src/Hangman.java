import java.util.Scanner;

public class Hangman {
    static final int MAX_GUESSES = 6;
    static final String[] WORDS = {"hangman", "computer", "java", "programming", "debugging"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = selectWord();
        char[] guessedLetters = new char[word.length()];
        int incorrectGuesses = 0;

        while (incorrectGuesses < MAX_GUESSES && !isWordGuessed(guessedLetters)) {
            displayHangman(incorrectGuesses);
            displayWord(word, guessedLetters);
            System.out.print("Enter a letter guess: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (!isLetter(guess)) {
                System.out.println("Invalid guess. Please enter a letter.");
                continue;
            }

            if (hasGuessed(guessedLetters, guess)) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            if (word.contains(String.valueOf(guess))) {
                updateGuessedLetters(word, guessedLetters, guess);
            } else {
                incorrectGuesses++;
            }
        }

        displayHangman(incorrectGuesses);
        displayWord(word, guessedLetters);

        if (isWordGuessed(guessedLetters)) {
            System.out.println("Congratulations! You guessed the word: " + word);
        } else {
            System.out.println("Sorry, you ran out of guesses. The word was: " + word);
        }

        scanner.close();
    }

    static String selectWord() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }

    static void displayWord(String word, char[] guessedLetters) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (hasGuessed(guessedLetters, c)) {
                System.out.print(c + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    static boolean hasGuessed(char[] guessedLetters, char letter) {
        for (char c : guessedLetters) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    static boolean isWordGuessed(char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == '\0') {
                return false;
            }
        }
        return true;
    }

    static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z');
    }

    static void updateGuessedLetters(String word, char[] guessedLetters, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedLetters[i] = guess;
            }
        }
    }

    static void displayHangman(int incorrectGuesses) {
        String[] hangmanImages = {
                "  +---+\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "========="
        };

        System.out.println(hangmanImages[incorrectGuesses]);
    }
}
