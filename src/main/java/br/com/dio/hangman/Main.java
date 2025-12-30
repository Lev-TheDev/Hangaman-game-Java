package br.com.dio.hangman;

import br.com.dio.hangman.exception.GameIsFinishedException;
import br.com.dio.hangman.exception.LetterAlreadyInputtedException;
import br.com.dio.hangman.model.HangmanChar;
import br.com.dio.hangman.model.HangmanGame;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String... args) {
        var characters = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new)
                .toList();
        System.out.println(characters);
        var hangmanGame = new HangmanGame(characters);
        System.out.println("Welcome to Hangman Game! Try to guess the word. Good luck!");
        System.out.println(hangmanGame);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1 - Input a letter");
            System.out.println("2 - Verify game status");
            System.out.println("3 - Exit");
            var option = scanner.nextInt();
            if ((option > 3) || (option < 0)) {
                System.out.println("Invalid option. Please try again.");
            }
            if (option == 1) {
                inputLetter(hangmanGame);
            }
            if (option == 2) {
                var status = hangmanGame.getHangmanGameStatus();
                switch (status.name()) {
                    case "WON" -> System.out.println("Congratulations! You've won the game!");
                    case "LOST" -> System.out.println("Sorry, you've lost the game. Better luck next time!");
                    case "IN_PROGRESS" -> System.out.println("Game is still in progress.");
                }
            }
            if (option == 0) {
                System.out.println("Exiting the game. Thanks for playing!");
                System.exit(0);
            }
        }
    }

    private static void inputLetter ( final HangmanGame hangmanGame){
        System.out.print("Enter a letter: ");
        var letter = scanner.next().toLowerCase().charAt(0);
        try {
            hangmanGame.inputCharacter(letter);
        } catch (LetterAlreadyInputtedException e) {
            System.out.println(e.getMessage());
        } catch (GameIsFinishedException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println(hangmanGame);
    }
}

