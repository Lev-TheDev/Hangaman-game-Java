package br.com.dio.hangman.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordRepository {

    // Caminho do arquivo. Se estiver na raiz do projeto, basta o nome.
    private static final String FILE_PATH = "palavras.csv";

    public String getRandomWord() {
        try {
            // 1. Lê todo o conteúdo do arquivo como uma única String
            String content = Files.readString(Paths.get(FILE_PATH));

            // 2. Separa as palavras usando a vírgula como delimitador
            // O trim() remove espaços extras que possam existir
            List<String> words = Arrays.stream(content.split(","))
                    .map(String::trim)
                    .filter(w -> !w.isEmpty()) // Garante que não pegue strings vazias
                    .toList();

            if (words.isEmpty()) {
                throw new IllegalStateException("O arquivo de palavras está vazio.");
            }

            // 3. Sorteia um índice aleatório
            Random random = new Random();
            int randomIndex = random.nextInt(words.size());

            // 4. Retorna a palavra sorteada em minúsculas (para padronizar)
            return words.get(randomIndex).toLowerCase();

        } catch (IOException e) {
            // Se der erro ao ler o arquivo, retorna uma palavra padrão de fallback
            System.err.println("Erro ao ler o arquivo CSV. Usando palavra padrão. Erro: " + e.getMessage());
            return "bombeiro";
        }
    }
}