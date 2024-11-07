package Jogos;

import Entidades.Dealer;
import Entidades.Jogador;
import Utilidades.ValorInvalidoException;

import java.util.*;

public class Blackjack extends Jogo {
    private Baralho baralho;
    private Jogador jogador;
    private Dealer dealer;
    private double resultado;

    public Blackjack(Jogador jogador, Dealer dealer) {
        super(2, false);

        //EM REVISÃO

//        ArrayList<Carta> cartas = new ArrayList<>(); // Inicializa a lista de cartas
//        String[] naipes = {"Copas", "Espadas", "Ouros", "Paus"}; // Define os naipes das cartas
//        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; // Define os valores das cartas
//
//        // Loop para criar todas as combinações de naipes e valores
//        for (String naipe : naipes) { // Para cada naipe (começa com "Copas") que vai de 0 a 3 (4 naipes)
//            for (String valor : valores) { // Para cada valor (começa com "A") que vai de 0 a 12 (13 valores)
//                cartas.add(new Carta(naipe, valor)); // Adiciona uma nova carta à lista (começa com "A de Copas")
//            }
//        }
//
//        Collections.shuffle(cartas);// Embaralha as cartas

        //this.baralho = new Baralho(cartas, null, dealer);
        this.jogador = jogador;
        this.dealer = dealer;
    }

    public Blackjack(boolean estadoDeJogo) {
        super(estadoDeJogo);
    }

    //EM REVISÃO
//    public double jogar(Scanner leitor, double valorAposta) throws ValorInvalidoException {
//        if (!apostar(leitor, valorAposta)) {
//            return 0;
//        }
//
//        iniciarJogo();
//        resultado = 0;
//
//        while (super.getEstado()) {
//            System.out.println("""
//                    +-+-+-+-+-+-+-+-+ BLACKJACK +-+-+-+-+-+-+-+-+
//                    Opções:
//
//                    [1] - Começar o jogo
//
//                    [0] - Voltar ao início""");
//
//            try {
//                switch (leitor.nextInt()) {
//                    case 1:
//                        iniciarJogo();
//                        break;
//
//                    case 0:
//                        System.out.println("Voltando à página inicial....");
//                        finalizarJogo();
//                        break;
//
//                    default:
//                        throw new ValorInvalidoException("Opção inválida, tente novamente.");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        finalizarJogo();
//        return verificarResultados(valorAposta);
//    }
//
//    private boolean apostar(Scanner leitor, double valorAposta) {
//        if (!jogador.retirarCreditos(valorAposta)) {
//            System.out.println("Créditos insuficientes para apostar.");
//            return false;
//        }
//        return true;
//    }

//    public double verificarResultados(double valorAposta) {
//        int pontuacaoJogador = calcularPontuacao(jogador.getMao());
//        int pontuacaoDealer = calcularPontuacao(dealer.getMao());
//
//        if (pontuacaoDealer > 21 || pontuacaoJogador > pontuacaoDealer) {
//            System.out.println("Jogador vence!");
//            resultado = valorAposta * 2;
//            jogador.depositarCreditos(resultado);
//        } else if (pontuacaoJogador < pontuacaoDealer) {
//            System.out.println("Dealer vence!");
//            resultado = 0;
//        } else {
//            System.out.println("Empate!");
//            resultado = valorAposta;
//            jogador.depositarCreditos(resultado);
//        }
//        return resultado;
//    }

    public String imprimir(double valorApostado) {
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Valor apostado: " + valorApostado + "\n" +
                "Resultado (em créditos): " + resultado + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }

    public void jogar(Scanner leitor) {
        iniciarJogo();

        // Distribui duas cartas para cada jogador
        jogador.receberCarta(baralho.distribuirCarta()); // em baralho.distribuirCarta() a carta é removida da lista de cartas (baralho) qu e esta em "Baralho"
        jogador.receberCarta(baralho.distribuirCarta());
        dealer.receberCarta(baralho.distribuirCarta());
        dealer.receberCarta(baralho.distribuirCarta());

        System.out.println("Cartas do jogador: " + jogador.getMao()); // Mostra as cartas do jogador
        System.out.println("Cartas do dealer: " + dealer.getMao().getFirst() + " e [carta oculta]"); // Mostra a primeira carta do dealer

        // Loop para as ações do jogador
        while (super.getEstado()) {
            System.out.println("Escolha uma ação: 1. Pedir carta  2. Parar");
            try {
                int escolha = leitor.nextInt(); // Lê a escolha do jogador

                if (escolha == 1) { // Se escolher pedir carta
                    jogador.receberCarta(baralho.distribuirCarta()); // Distribui uma carta ao jogador
                    System.out.println("Cartas do jogador: " + jogador.getMao()); // Mostra as cartas do jogador
                    if (calcularPontuacao(jogador.getMao()) > 21) { // Se a pontuação do jogador estourar ＞﹏＜
                        System.out.println("Você estourou! Dealer vence."); // Que cara ruim, perdeu para um bot
                        return; // Sai do loop
                    }
                } else if (escolha == 2) { // Se escolher parar
                    break; // Sai do loop
                } else {
                    throw new ValorInvalidoException("Escolha inválida."); // Lança exceção para escolha inválida
                }
            } catch (InputMismatchException e) { // Captura exceção para entrada inválida
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                leitor.next();
            } catch (ValorInvalidoException e) { // Captura exceção para valor inválido
                System.out.println(e.getMessage());
            }
        }
        // Ações do dealer
        while (calcularPontuacao(dealer.getMao()) < 17) { // Enquanto a pontuação do dealer for menor que 17
            dealer.receberCarta(baralho.distribuirCarta()); // O dealer recebe uma carta
        }

        System.out.println("Cartas do dealer: " + dealer.getMao()); // Mostra as cartas do dealer

        // Determina o vencedor
        int pontuacaoJogador = calcularPontuacao(jogador.getMao()); // Calcula a pontuação do jogador
        int pontuacaoDealer = calcularPontuacao(dealer.getMao()); // Calcula a pontuação do dealer

        if (pontuacaoDealer > 21 || pontuacaoJogador > pontuacaoDealer) { // Se o dealer estourar ou a pontuação do jogador for maior
            System.out.println("Jogador vence!");
        } else if (pontuacaoJogador < pontuacaoDealer) { // Se a pontuação do jogador for menor
            System.out.println("Dealer vence!");
        } else {
            System.out.println("Empate!"); // Se for empate
        }
    }

    public void iniciarJogo() {
        super.setEstado(true);
        System.out.println("Com as cartas embaralhadas, iniciaremos o jogo! Boa sorte.");
    }

    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Finalizando a jogatina no BlackJack. Obrigado pela preferência!");
    }

    private int calcularPontuacao(List<Carta> mao) { // Método para calcular a pontuação da mão
        int pontuacao = 0;
        int ases = 0; // Contador de ases para ajustar a pontuação

        // Calcula a pontuação da mão
        for (Carta carta : mao) {
            String valor = carta.getValor(); // Pega o valor da carta (obvio)
            if (valor.equals("A")) { // Se a carta for um ás
                ases++;
                pontuacao += 11;
            } else if (valor.equals("K") || valor.equals("Q") || valor.equals("J")) {
                pontuacao += 10; // Se a carta for um rei, dama ou valete
            } else {
                pontuacao += Integer.parseInt(valor); // Converte o valor da carta para inteiro e adiciona à pontuação
            }
        }

        // Ajusta a pontuação se houver ases e a pontuação for maior que 21
        // Nas regras de Blackjack, um ás pode valer 1 ou 11, então se a pontuação estourar, um ás é convertido de 11 para 1 (づ￣ 3￣)づ
        while (pontuacao > 21 && ases > 0) {
            pontuacao -= 10;
            ases--;
        }

        return pontuacao; // Retorna a pontuação final
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}