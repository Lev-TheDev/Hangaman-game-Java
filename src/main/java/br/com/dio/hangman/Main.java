package br.com.dio.hangman;

import br.com.dio.hangman.exception.GameIsFinishedException;
import br.com.dio.hangman.exception.LetterAlreadyInputtedException;
import br.com.dio.hangman.model.HangmanChar;
import br.com.dio.hangman.model.HangmanGame;
import br.com.dio.hangman.model.HangmanGameStatus;
import br.com.dio.hangman.utils.WordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String... args) {

        System.out.println("Welcome to Hangman Game! Try to guess the word. Good luck!");
        WordRepository repository = new WordRepository();
        String chosenWord = repository.getRandomWord();
        System.out.println("The chosen word has " + chosenWord.length() + " letters.");
        List<HangmanChar> hangmanChars = new ArrayList<>();
        for (char c : chosenWord.toCharArray()) {
            hangmanChars.add(new HangmanChar(c));
        }
        HangmanGame hangmanGame = new HangmanGame(hangmanChars);

        System.out.println(hangmanGame);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1 - Input a letter");
            System.out.println("2 - Verify game status");
            System.out.println("0 - Exit");

            int option = -1;
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            } else {
                scanner.next();
            }

            if (option == 1) {
                inputLetter(hangmanGame);
                if (hangmanGame.getHangmanGameStatus() != HangmanGameStatus.IN_PROGRESS) {
                    System.out.println("\n--- THAT'S THE END ---");
                    if (hangmanGame.getHangmanGameStatus() == HangmanGameStatus.WON) {
                        System.out.println("Congratulations! You've won the game!");
                    } else {
                        System.out.println("Sorry, you've lost the game. Better luck next time! The word was: " + chosenWord);
                    }
                    break;
                }
            }
            else if (option == 2) {
                var status = hangmanGame.getHangmanGameStatus();
                switch (status) {
                    case WON -> System.out.println("Congratulations! You've won the game!");
                    case LOST -> System.out.println("Sorry, you've lost the game. Better luck next time!");
                    case IN_PROGRESS -> System.out.println("Game is still in progress.");
                }
            }
            else if (option == 0) {
                System.out.println("Exiting the game. Thanks for playing!");
                System.exit(0);
            }
            else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void inputLetter ( final HangmanGame hangmanGame){
        System.out.print("Enter a letter: ");
        var letter = scanner.next().toLowerCase().charAt(0);
        try {
            hangmanGame.inputCharacter(letter);
        } catch (LetterAlreadyInputtedException e) {
            System.out.println("Warning: " + e.getMessage());
        } catch (GameIsFinishedException e) {
            System.out.println("Warning: " + e.getMessage());
            System.exit(0);
        }
        System.out.println(hangmanGame);
    }
}

