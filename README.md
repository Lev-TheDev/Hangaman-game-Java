# 🎮 Jogo da Forca (Hangman Game)

> Implementação robusta do clássico **Jogo da Forca**, desenvolvida em **Java**, com foco em **Orientação a Objetos**, **manipulação de arquivos (I/O)** e **lógica matemática para renderização de gráficos ASCII no console**.

---

## 📌 Sobre o Projeto

O jogo seleciona aleatoriamente uma palavra de um **banco de dados externo (`.csv`)**, oculta seus caracteres e desafia o usuário a adivinhá-la **letra por letra**.

A cada erro, uma parte do corpo do boneco é **desenhada progressivamente no console**, até a vitória ou derrota.

✨ **Destaques do projeto:**
- Arquitetura bem definida
- Tratamento de exceções personalizado
- Código resiliente e extensível
- Compatibilidade entre diferentes sistemas operacionais

---

## 🧠 Arquitetura e Conceitos Técnicos

### 🧱 1. Padrões de Projeto e Organização

O projeto segue o princípio de **Separação de Responsabilidades (SoC)**, dividindo a lógica em camadas bem definidas:

#### 📦 Model  
`br.com.dio.hangman.model`

- Contém a **regra de negócios**
- A classe `HangmanGame` encapsula:
  - Estado do jogo
  - Vidas
  - Letras descobertas
  - Estado atual do desenho
- ❗ Não realiza saída em tela (sem dependência de I/O)

#### 🗄️ Repository  
`br.com.dio.hangman.utils`

- Implementação simplificada do **padrão Repository**
- A classe `WordRepository` é responsável exclusivamente por:
  - Leitura do arquivo CSV
  - Sorteio aleatório de palavras
- Isola completamente a lógica de I/O do restante do jogo

#### 🖥️ View / Controller  
`Main.java`

- Gerencia:
  - Entrada e saída do usuário
  - Loop principal do jogo
  - Fluxo geral da aplicação

---

### 📊 2. Estruturas de Dados Utilizadas

- **`List<HangmanChar>` / `ArrayList`**
  - Representa dinamicamente os caracteres da palavra secreta
  - Permite uso eficiente da **Stream API**

- **`StringBuilder`**
  - Essencial para a renderização do boneco
  - Permite manipular strings mutáveis
  - Evita criação excessiva de objetos `String` (melhor performance)

- **Stream API**
  - Filtragem de letras encontradas
  - Verificação de vitória (`noneMatch`)
  - Processamento da leitura do CSV

---

### 🧩 3. Lógica de Renderização (Desafio Técnico)

Um dos principais desafios foi garantir o **alinhamento correto do desenho ASCII** em diferentes sistemas operacionais.

#### ⚠️ Problema

- **Windows:** usa `\r\n` (2 caracteres)
- **Linux / macOS:** usam `\n` (1 caractere)

Isso causava desalinhamento no cálculo das posições do boneco.

#### ✅ Solução

- Uso de `System.lineSeparator().length()`
- Cálculo dinâmico dos índices da matriz de caracteres
- Garantia de renderização correta **independente do sistema operacional**

---

## 🚀 Funcionalidades Principais

✔️ Leitura de palavras a partir de arquivo CSV externo  
✔️ Renderização progressiva do boneco em ASCII  
✔️ Normalização de entradas (**case insensitive**)  
✔️ Tratamento de exceções personalizadas  

### ⚠️ Exceções de Domínio

- `LetterAlreadyInputtedException`  
  > Impede que o jogador perca vidas ao repetir letras

- `GameIsFinishedException`  
  > Bloqueia interações após o término do jogo

---

## 🗂️ Estrutura de Diretórios

```text
src/main/java/br/com/dio/hangman/
├── Main.java                     # Entry point e loop principal
├── model/
│   ├── HangmanGame.java           # Lógica central e estado do jogo
│   ├── HangmanChar.java           # Representação de cada letra
│   └── HangmanGameStatus.java     # Enum: WON, LOST, IN_PROGRESS
├── utils/
│   └── WordRepository.java        # Leitura de CSV e sorteio de palavras
└── exception/
    └── (Exceções de domínio)
```

## ▶️ Como Executar

### 🔧 Pré-requisitos
- Java JDK 11 ou superior

### 📝 Passo a Passo

1. Clone o repositório:

       git clone <url-do-repositorio>

2. Acesse a pasta do projeto:

       cd hangman-game-java

3. Verifique o arquivo de palavras:

   O arquivo `palavras.csv` deve estar na raiz do projeto, no mesmo nível da pasta `src`.

4. Compile o projeto:

       javac -d bin src/main/java/br/com/dio/hangman/**/*.java src/main/java/br/com/dio/hangman/*.java

5. Execute o jogo:

       java -cp bin br.com.dio.hangman.Main

---

## 📄 Exemplo do Arquivo CSV

    desenvolvimento,java,computador,logica,algoritmo,bombeiro,escola

---

## 🤝 Contribuição

Contribuições são **bem-vindas**.  
Sinta-se à vontade para abrir **issues**, enviar **pull requests** ou sugerir melhorias.

---

⭐ Se este projeto foi útil, considere deixar uma **estrela no repositório**!
