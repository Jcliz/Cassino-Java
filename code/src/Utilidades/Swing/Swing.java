package Utilidades.Swing;

import Entidades.Jogador;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Swing {
    private static String[][] cartela;  // Cartela global para manter o estado
    private static JFrame cartelaFrame;
    private static JFrame janelaCacaNiquel;
    private static JLabel saldoLabel;
    private static JLabel resultadoLabel;
    private static JLabel numeroLabel;
    private static final Set<Integer> numerosSorteados = new HashSet<>();
    private static final int MAX_NUMEROS = 50;// Referência para a janela da cartela
    private static JFrame mainFrame;

    public static void mostrarMenuPrincipal(Jogador jogador, boolean opcao7Disponivel) {
        mainFrame = new JFrame("Cassino");
        mainFrame.setSize(300, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(0, 1));

        JButton btnCentralCreditos = new JButton("Central de créditos");
        btnCentralCreditos.addActionListener(e -> mostrarCentralDeCreditos(jogador));

        JButton btnBingo = new JButton("Bingo");
        btnBingo.addActionListener(e -> iniciarBingo(jogador));

        JButton btnCacaNiquel = new JButton("Caça Níquel");
        btnCacaNiquel.addActionListener(e -> iniciarCacaNiquel(jogador));

        JButton btnRoleta = new JButton("Roleta");
        btnRoleta.addActionListener(e -> iniciarRoleta(jogador));

        JButton btnBlackJack = new JButton("BlackJack");
        btnBlackJack.addActionListener(e -> iniciarBlackjack(jogador));

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> mainFrame.dispose());

        mainFrame.add(btnCentralCreditos);
        mainFrame.add(btnBingo);
        mainFrame.add(btnCacaNiquel);
        mainFrame.add(btnRoleta);
        mainFrame.add(btnBlackJack);

        if (opcao7Disponivel) {
            JButton btnApostarLancer = new JButton("Apostar o Lancer");
            btnApostarLancer.addActionListener(e -> {
                jogador.depositarCreditos(60000);
                JOptionPane.showMessageDialog(mainFrame, "Você recebeu R$ 60.000! Créditos totais: R$ " + jogador.getCreditos());
                btnApostarLancer.setEnabled(false); // Desabilitar o botão após uso
            });
            mainFrame.add(btnApostarLancer);
        }

        mainFrame.add(btnSair);
        mainFrame.setVisible(true);
    }

    private static void mostrarCentralDeCreditos(Jogador jogador) {
        JFrame frameCentralCreditos = new JFrame("Central de Créditos");
        frameCentralCreditos.setSize(300, 200);
        frameCentralCreditos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCentralCreditos.setLayout(new GridLayout(0, 1));

        JButton btnDeposito = new JButton("Depósito");
        btnDeposito.addActionListener(e -> depositoDeCreditos(jogador));

        JButton btnSaldo = new JButton("Saldo");
        btnSaldo.addActionListener(e -> JOptionPane.showMessageDialog(frameCentralCreditos,
                "Créditos atuais: R$ " + jogador.getCreditos()));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> frameCentralCreditos.dispose());

        frameCentralCreditos.add(btnDeposito);
        frameCentralCreditos.add(btnSaldo);
        frameCentralCreditos.add(btnVoltar);

        frameCentralCreditos.setVisible(true);
    }

    public static void iniciarBlackjack(Jogador jogador) {
        JFrame janelaBlackjack = new JFrame("Jogo de Blackjack");
        janelaBlackjack.setSize(400, 300);
        janelaBlackjack.setLayout(new FlowLayout());
        janelaBlackjack.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha apenas a janela de Blackjack

        JLabel lblStatus = new JLabel("Bem-vindo ao Blackjack!");
        janelaBlackjack.add(lblStatus);

        // Exibição do saldo
        saldoLabel = new JLabel("Créditos: R$ " + jogador.getCreditos());
        janelaBlackjack.add(saldoLabel, BorderLayout.NORTH);

        // Painel para exibir as cartas
        JPanel painelBlackjack = new JPanel();
        painelBlackjack.setPreferredSize(new Dimension(400, 150));
        painelBlackjack.setLayout(new BorderLayout());

        // JLabel para mostrar o resultado do jogo
        resultadoLabel = new JLabel("Clique em 'Jogar' para começar", JLabel.CENTER);
        painelBlackjack.add(resultadoLabel, BorderLayout.CENTER);
        janelaBlackjack.add(painelBlackjack, BorderLayout.CENTER);

        // Botão de aposta
        JButton iniciarButton = new JButton("Jogar");
        iniciarButton.addActionListener(_ -> apostarEIniciarJogo(jogador));
        janelaBlackjack.add(iniciarButton, BorderLayout.SOUTH);

        // Tornar a janela visível
        janelaBlackjack.setVisible(true);
    }

    // Função para apostar e iniciar o jogo
    private static void apostarEIniciarJogo(Jogador jogador) {
        // Solicitar ao jogador que aposte uma quantidade de créditos
        String apostaInput = JOptionPane.showInputDialog("Quanto deseja apostar?");
        int aposta = Integer.parseInt(apostaInput);  // Converter para int

        // Validar a aposta
        if (aposta <= 0 || aposta > jogador.getCreditos()) {
            JOptionPane.showMessageDialog(null,
                    "Aposta inválida! Você não tem créditos suficientes.");
        } else {
            // Chama o método jogarBlackjack com os parâmetros do jogador e aposta
            jogarBlackjack(jogador, aposta);
        }
    }

    // Método para jogar uma rodada de Blackjack
    public static void jogarBlackjack(Jogador jogador, int aposta) {
        // Verificar se o jogador tem créditos suficientes para jogar
        if (jogador.getCreditos() < aposta) {
            JOptionPane.showMessageDialog(null,
                    "Você não tem créditos suficientes para jogar!");
            return;
        }

        // Deduzir a aposta dos créditos do jogador
        jogador.depositarCreditos(-aposta);

        // Iniciar a rodada de Blackjack
        Random random = new Random();

        // Simular a distribuição das cartas (valores de 2 a 10 e figuras como 10)
        int cartaJogador1 = random.nextInt(9) + 2;  // Número entre 2 e 10
        int cartaJogador2 = random.nextInt(9) + 2;  // Número entre 2 e 10
        int cartaDealer1 = random.nextInt(9) + 2;   // Número entre 2 e 10
        int cartaDealer2 = random.nextInt(9) + 2;   // Número entre 2 e 10

        // Calcular a pontuação do jogador e do dealer
        int pontuacaoJogador = cartaJogador1 + cartaJogador2;
        int pontuacaoDealer = cartaDealer1 + cartaDealer2;

        // Atualizar o painel com as cartas e pontuação
        resultadoLabel.setText("Cartas: " + cartaJogador1 + " e " + cartaJogador2 +
                " | Pontuação: " + pontuacaoJogador);

        // Mostrar as cartas do dealer
        JOptionPane.showMessageDialog(null, "O dealer tem: "
                + cartaDealer1 + " e " + cartaDealer2);

        // Verificar se o jogador ultrapassou 21 (bust)
        String resultado;
        if (pontuacaoJogador > pontuacaoDealer) {
            resultado = "Você venceu o dealer!";
            jogador.depositarCreditos(aposta * 2);  // Vencer dobra a aposta
        } else if (pontuacaoJogador < pontuacaoDealer) {
            resultado = "Você perdeu para o dealer.";
        } else {
            resultado = "Empate! Nenhum vencedor.";
            jogador.depositarCreditos(aposta);  // Empate devolve a aposta
        }

        // Exibir o resultado final
        JOptionPane.showMessageDialog(null, resultado);
        saldoLabel.setText("Créditos: R$ " + jogador.getCreditos());
    }
    // Método para iniciar a roleta (sem opções de número e cor)
    public static void iniciarRoleta(Jogador jogador) {
        // Criando a janela de roleta
        JFrame janelaRoleta = new JFrame("Jogo de Roleta");
        janelaRoleta.setSize(400, 300);
        janelaRoleta.setLayout(new BorderLayout());
        janelaRoleta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Exibição do saldo
        saldoLabel = new JLabel("Créditos: R$ " + jogador.getCreditos());
        janelaRoleta.add(saldoLabel, BorderLayout.NORTH);

        // Painel para exibir o número e a cor sorteada
        JPanel painelRoleta = new JPanel();
        painelRoleta.setPreferredSize(new Dimension(400, 100));
        painelRoleta.setLayout(new BorderLayout());

        // JLabel para mostrar número e cor da roleta
        numeroLabel = new JLabel("Número da Roleta: 0 | Cor: Verde", JLabel.CENTER);
        painelRoleta.add(numeroLabel, BorderLayout.CENTER);
        janelaRoleta.add(painelRoleta, BorderLayout.CENTER);

        // Botão para iniciar o giro da roleta
        JButton iniciarButton = getButton(jogador);
        janelaRoleta.add(iniciarButton, BorderLayout.SOUTH);

        // Tornar a janela visível
        janelaRoleta.setVisible(true);
    }

    private static JButton getButton(Jogador jogador) {
        JButton iniciarButton = new JButton("Girar a Roleta");
        iniciarButton.addActionListener(_ -> {
            // Solicitar ao jogador para escolher um número e uma cor antes de girar a roleta
            String numeroInput = JOptionPane.showInputDialog("Escolha um número (0 a 36):");
            int numeroEscolhido = Integer.parseInt(numeroInput);  // Converter para int

            String corEscolhida = JOptionPane.showInputDialog("Escolha uma cor (Vermelho, Preto, Verde):");

            // Validar as entradas do jogador
            if (numeroEscolhido < 0 || numeroEscolhido > 36) {
                JOptionPane.showMessageDialog(null,
                        "Número inválido! Escolha um número entre 0 e 36.");
            } else if (!(corEscolhida.equalsIgnoreCase("Vermelho") ||
                    corEscolhida.equalsIgnoreCase("Preto") ||
                    corEscolhida.equalsIgnoreCase("Verde"))) {
                JOptionPane.showMessageDialog(null,
                        "Cor inválida! Escolha entre Vermelho, Preto ou Verde.");
            } else {
                // Chama o método girarRoleta com os parâmetros do jogador, número e cor escolhidos
                girarRoleta(jogador, numeroEscolhido, corEscolhida);
            }
        });
        return iniciarButton;
    }


    // Método para girar a roleta (com animação e verificação de acertos)
    public static void girarRoleta(Jogador jogador, int numeroEscolhido, String corEscolhida) {
        // Verificar se o jogador tem créditos suficientes
        if (jogador.getCreditos() < 10) {
            JOptionPane.showMessageDialog(null,
                    "Você não tem créditos suficientes para jogar!");
            return;
        }

        // Deduzir 10 créditos por rodada
        jogador.depositarCreditos(-10);

        // Sorteia o número da roleta de uma vez antes da animação
        Random random = new Random();
        int numeroRoleta = random.nextInt(37);  // Números de 0 a 36 (roleta com 37 números)
        String cor = (numeroRoleta == 0) ? "Verde" : (numeroRoleta % 2 == 0) ? "Preto" : "Vermelho";

        // Iniciar animação de giro da roleta (simulação rápida)
        new Thread(() -> {
            int numeroAtual;
            String corAtual;
            for (int i = 0; i < 30; i++) {  // Mostrar números aleatórios durante a animação
                numeroAtual = random.nextInt(37);
                corAtual = (numeroAtual == 0) ? "Verde" : (numeroAtual % 2 == 0) ? "Preto" : "Vermelho";// Mostrar números aleatórios
                atualizarRoleta(numeroAtual, corAtual);  // Atualiza a roleta com o número atual
                try {
                    Thread.sleep(50);  // Tempo de "animação" (50ms por número)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Após a animação, exibe o número real e a cor
            atualizarRoleta(numeroRoleta, cor);  // Atualiza com o número final
            String resultado = "Número sorteado: " + numeroRoleta + "\nCor: " + cor;

            // Verificar se o jogador ganhou e atualizar os créditos
            if (numeroRoleta == numeroEscolhido && cor.equals(corEscolhida)) {
                jogador.depositarCreditos(100);  // Prêmio maior por acertar número e cor
                resultado += "\nVocê ganhou 100 créditos por acertar o número e a cor!";
            } else if (numeroRoleta == numeroEscolhido) {
                jogador.depositarCreditos(50);  // Prêmio menor por acertar apenas o número
                resultado += "\nVocê ganhou 50 créditos por acertar o número!";
            } else if (cor.equals(corEscolhida)) {
                jogador.depositarCreditos(30);  // Prêmio por acertar a cor
                resultado += "\nVocê ganhou 30 créditos por acertar a cor!";
            } else {
                resultado += "\nVocê não acertou. Tente novamente!";
            }

            // Atualizar interface com o resultado final
            JOptionPane.showMessageDialog(null, resultado);
            saldoLabel.setText("Créditos: R$ " + jogador.getCreditos());
        }).start();
    }

    // Atualizar o painel da roleta com o número e cor sorteados
    private static void atualizarRoleta(int numero, String cor) {
        numeroLabel.setText("Número da Roleta: " + numero + " | Cor: " + cor);  // Atualiza número e cor
    }

    private static void iniciarCacaNiquel(Jogador jogador) {
        janelaCacaNiquel = new JFrame("Jogo de Caça-Níquel");
        janelaCacaNiquel.setSize(300, 200);
        janelaCacaNiquel.setLayout(new FlowLayout());
        janelaCacaNiquel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        saldoLabel = new JLabel("Créditos: R$ " + jogador.getCreditos());
        janelaCacaNiquel.add(saldoLabel);

        JButton btnRodar = new JButton("Rodar Máquina");
        btnRodar.addActionListener(e -> rodarMaquina(jogador));

        janelaCacaNiquel.add(btnRodar);
        janelaCacaNiquel.setVisible(true);
    }

    // Método para abrir o menu do Caça-Níquel
    // Função para rodar a máquina de Caça-Níquel
    private static void rodarMaquina(Jogador jogador) {
        if (jogador.getCreditos() < 10) {
            JOptionPane.showMessageDialog(janelaCacaNiquel, "Você não tem créditos suficientes para jogar!");
            return;
        }

        // Deduzir 10 créditos por rodada
        jogador.depositarCreditos(-10);

        // Definir os símbolos e suas probabilidades de vitória
        String[] simbolos = {"🍒", "🍋", "🍊", "🍇", "🍉"};
        Random random = new Random();

        int chance = random.nextInt(100);  // Gera um número de 0 a 99
        String resultado;

        // Condições de vitória conforme solicitado
        if (chance < 5) {  // 5% de chance
            resultado = "🍒 🍒 🍒";
            jogador.depositarCreditos(50);
            JOptionPane.showMessageDialog(janelaCacaNiquel, "Parabéns! Você ganhou 50 créditos! Resultado: " + resultado);
            tocarSomWinner();
        } else if (chance < 15) {  // 10% de chance
            resultado = "🍋 🍋 🍋";
            jogador.depositarCreditos(20);
            JOptionPane.showMessageDialog(janelaCacaNiquel, "Você ganhou 20 créditos! Resultado: " + resultado);
            tocarSomWinner();
        } else if (chance < 30) {  // 15% de chance
            resultado = "🍊 🍊 🍊";
            jogador.depositarCreditos(10);
            JOptionPane.showMessageDialog(janelaCacaNiquel, "Você ganhou 10 créditos! Resultado: " + resultado);
            tocarSomWinner();
        } else if (chance < 35) {  // 5% de chance para "🍇 🍇 🍇"
            resultado = "🍇 🍇 🍇";
            jogador.depositarCreditos(10);
            JOptionPane.showMessageDialog(janelaCacaNiquel, "Você ganhou 10 créditos! Resultado: " + resultado);
            tocarSomWinner();
        } else if (chance < 45) {  // 10% de chance
            resultado = "🍉 🍉 🍉";
            jogador.depositarCreditos(5);
            JOptionPane.showMessageDialog(janelaCacaNiquel, "Você ganhou 5 créditos! Resultado: " + resultado);
            tocarSomWinner();
        } else {
            // Gerar um resultado aleatório quando não há vitória
            resultado = simbolos[random.nextInt(simbolos.length)] + " " +
                    simbolos[random.nextInt(simbolos.length)] + " " +
                    simbolos[random.nextInt(simbolos.length)];
            JOptionPane.showMessageDialog(janelaCacaNiquel, "Resultado: " + resultado);
            tocarSom();
        }

        // Atualizar o saldo na tela
        saldoLabel.setText("Créditos: R$ " + jogador.getCreditos());
    }

    // Método para tocar o som
    private static void tocarSom() {
        try {
            // Remova as aspas extras no caminho do arquivo
            File soundFile = new File("code/src/Utilidades/Swing/Jogada.wav"); // Substitua com o caminho correto
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Erro ao reproduzir o som: " + e.getMessage());
        }
    }

    public static void tocarSomEntrada() {
        try {
            // Remova as aspas extras no caminho do arquivo
            File soundFile = new File("code/src/Utilidades/Swing/Letsgo.wav"); // Substitua com o caminho correto
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Erro ao reproduzir o som: " + e.getMessage());
        }
    }

    private static void tocarSomWinner() {
        try {
            // Remova as aspas extras no caminho do arquivo
            File soundFile = new File("code/src/Utilidades/Swing/Winner.wav"); // Substitua com o caminho correto
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Erro ao reproduzir o som: " + e.getMessage());
        }
    }

    // Método de depósito de créditos
    private static void depositoDeCreditos(Jogador jogador) {
        String valorInput = JOptionPane.showInputDialog("Digite o valor para depósito:");

        try {
            double valor = Double.parseDouble(valorInput);
            jogador.depositarCreditos(valor);  // Atualiza os créditos do jogador
            JOptionPane.showMessageDialog(null, "Depósito de R$ " +
                    valor + " realizado com sucesso! Créditos totais: R$ " + jogador.getCreditos());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido. Tente novamente.");
        }
    }

    private static void iniciarBingo(Jogador jogador) {
        int pontosPelaCartela = 50;  // Exemplo de valor fixo para a cartela de Bingo

        // Gerar a cartela uma vez e descontar os créditos
        if (cartela == null) {
            if (jogador.getCreditos() >= pontosPelaCartela) {
                jogador.retirarCreditos(pontosPelaCartela);
                cartela = gerarCartela();  // Gera a cartela
                JOptionPane.showMessageDialog(null,
                        "Cartela gerada com sucesso! Você pagou R$ " + pontosPelaCartela + " pela cartela.");
                exibirCartela();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Você não tem créditos suficientes para gerar a cartela.");
                return;  // Retorna ao menu caso não tenha créditos suficientes
            }
        }

        // Loop de sorteios contínuos no Bingo
        boolean continuarBingo = true;
        while (continuarBingo) {
            String opcaoBingo = JOptionPane.showInputDialog("""
            Jogo de Bingo:
            [1] - Sortear um número
            [2] - Ver créditos após o jogo

            [0] - Voltar ao menu
            """);

            switch (opcaoBingo) {
                case "1":
                    // Realizar sorteio
                    int numeroSorteado = sortearNumero();
                    JOptionPane.showMessageDialog(null, "Número sorteado: " + numeroSorteado);
                    marcarNumeroNaCartela(numeroSorteado);
                    exibirCartela();  // Exibe a cartela após marcar o número

                    // Verificar condição de vitória
                    if (verificarVitoria()) {
                        JOptionPane.showMessageDialog(null, "Parabéns! Você venceu o Bingo!");
                        jogador.depositarCreditos(100);
                        cartelaFrame.setVisible(false);
                        continuarBingo = false;  // Encerra o loop do Bingo ao vencer
                    }
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Créditos atuais: R$ " +
                            jogador.getCreditos());
                    break;

                case "0":
                    JOptionPane.showMessageDialog(null, "Voltando ao menu principal...");
                    cartelaFrame.setVisible(false);
                    continuarBingo = false;  // Sai do loop de bingo e volta ao menu principal
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    // Método para gerar a cartela de Bingo
    private static String[][] gerarCartela() {
        String[][] cartela = new String[5][5];
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 5; i++) {
            Set<Integer> numerosGerados = new HashSet<>();
            int[] coluna = new int[5];
            for (int j = 0; j < 5; j++) {
                int numero;
                do {
                    numero = random.nextInt(10) + i * 10;
                } while (numerosGerados.contains(numero));
                numerosGerados.add(numero);
                coluna[j] = numero;
            }
            Arrays.sort(coluna);
            for (int j = 0; j < 5; j++) {
                cartela[j][i] = String.valueOf(coluna[j]);
            }
        }
        return cartela;
    }

    // Método para exibir a cartela na interface gráfica
    private static void exibirCartela() {
        // Verifica se já existe uma janela aberta da cartela
        if (cartelaFrame != null && cartelaFrame.isVisible()) {
            // Limpa a janela atual antes de adicionar os novos componentes
            cartelaFrame.getContentPane().removeAll();
        } else {
            // Cria a nova janela da cartela
            cartelaFrame = new JFrame("Cartela de Bingo");
            cartelaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cartelaFrame.setSize(400, 400);
            cartelaFrame.setLayout(new GridLayout(5, 5));
        }

        // Adiciona os componentes da cartela atualizada
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel label = new JLabel(cartela[i][j], SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setPreferredSize(new Dimension(60, 60));
                label.setFont(new Font("Arial", Font.PLAIN, 20));
                cartelaFrame.add(label);
            }
        }

        // Revalida e repinta a janela para garantir que ela seja atualizada corretamente
        cartelaFrame.revalidate();
        cartelaFrame.repaint();
        cartelaFrame.setVisible(true);
    }

    // Método para sortear um número
    private static Integer sortearNumero() {
        if (numerosSorteados.size() >= MAX_NUMEROS) {
            JOptionPane.showMessageDialog(null, "Todos os números já foram sorteados!");
            return null;  // Retorna null para indicar que não há mais números a serem sorteados
        }

        Random random = new Random();
        int numero;
        do {
            numero = random.nextInt(MAX_NUMEROS) + 1;
        } while (numerosSorteados.contains(numero));

        numerosSorteados.add(numero);  // Adiciona o número sorteado ao conjunto
        return numero;
    }

    // Método para marcar um número na cartela
    private static void marcarNumeroNaCartela(int numeroSorteado) {
        // Percorrer a cartela e marcar com "X" se o número sorteado for encontrado
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cartela[i][j].equals(String.valueOf(numeroSorteado))) {
                    cartela[i][j] = "X";  // Marca com "X"
                }
            }
        }
    }

    private static boolean verificarVitoria() {
        // Verificar linhas
        for (int i = 0; i < 5; i++) {
            if (Arrays.stream(cartela[i]).allMatch("X"::equals)) {
                JOptionPane.showMessageDialog(cartelaFrame, "Parabéns! Você completou uma linha e ganhou!");
                return true;
            }
        }

        // Verificar colunas
        for (int j = 0; j < 5; j++) {
            boolean colunaCompleta = true;
            for (int i = 0; i < 5; i++) {
                if (!cartela[i][j].equals("X")) {
                    colunaCompleta = false;
                    break;
                }
            }
            if (colunaCompleta) {
                JOptionPane.showMessageDialog(cartelaFrame, "Parabéns! Você completou uma coluna e ganhou!");
                return true;
            }
        }

        // Verificar diagonal principal
        boolean diagonalPrincipalCompleta = true;
        for (int i = 0; i < 5; i++) {
            if (!cartela[i][i].equals("X")) {
                diagonalPrincipalCompleta = false;
                break;
            }
        }
        if (diagonalPrincipalCompleta) {
            JOptionPane.showMessageDialog(cartelaFrame,
                    "Parabéns! Você completou a diagonal principal e ganhou!");
            return true;
        }

        // Verificar diagonal secundária
        boolean diagonalSecundariaCompleta = true;
        for (int i = 0; i < 5; i++) {
            if (!cartela[i][4 - i].equals("X")) {
                diagonalSecundariaCompleta = false;
                break;
            }
        }
        if (diagonalSecundariaCompleta) {
            JOptionPane.showMessageDialog(cartelaFrame,
                    "Parabéns! Você completou a diagonal secundária e ganhou!");
            return true;
        }
        return false;
    }
}