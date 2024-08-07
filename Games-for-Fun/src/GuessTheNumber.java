import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        int maxAttempts = 10;
        int attempts = 0;
        int guess;

        System.out.println("Welcome to Guess the Number!");
        System.out.println("I've selected a number between " + minRange + " and " + maxRange + ". Try to guess it.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
            guess = scanner.nextInt();

            if (guess < minRange || guess > maxRange) {
                System.out.println("Please enter a number within the range.");
                continue;
            }

            attempts++;

            if (guess == targetNumber) {
                System.out.println("Congratulations! You've guessed the number " + targetNumber + " correctly in " + attempts + " attempts.");
                break;
            } else if (guess < targetNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you've used all your attempts. The correct number was " + targetNumber + ".");
        }

        scanner.close();
    }
}
