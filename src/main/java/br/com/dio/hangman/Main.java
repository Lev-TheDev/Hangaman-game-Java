package br.com.dio.hangman;

import br.com.dio.hangman.model.HangmanChar;
import br.com.dio.hangman.model.HangmanGame;

import java.util.stream.Stream;

public class Main {
    public static void main(String... args) {
        var characters = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new)
                .toList();
        System.out.println(characters);
        var hangmanGame = new HangmanGame(characters);
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('a');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('a');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('c');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('d');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('f');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('g');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('h');
    }
}
