package Jogos;

import Entidades.Dealer;
import Entidades.Jogador;
import java.util.List;
import java.util.Scanner;

public class Blackjack extends Jogo {
    private Baralho baralho; // Baralho de cartas
    private Jogador jogador; // Jogador
    private Dealer dealer; // Dealer

    public Blackjack() {
        super(2, false); // Chama o construtor da superclasse Jogo
        baralho = new Baralho(); // Inicializa o baralho
        jogador = new Jogador("Jogador", "01/01/1990", 1000.0); // Inicializa o jogador TESTE (tirar no codigo final)
        dealer = new Dealer(); // Inicializa o dealer
    }

    @Override
    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        // Distribui duas cartas para cada jogador
        jogador.receberCarta(baralho.distribuirCarta()); // em baralho.distribuirCarta() a carta é removida da lista de cartas (baralho) qu e esta em "Baralho"
        jogador.receberCarta(baralho.distribuirCarta());
        dealer.receberCarta(baralho.distribuirCarta());
        dealer.receberCarta(baralho.distribuirCarta());

        System.out.println("Cartas do jogador: " + jogador.getMao()); // Mostra as cartas do jogador
        System.out.println("Cartas do dealer: " + dealer.getMao().get(0) + " e [carta oculta]"); // Mostra a primeira carta do dealer

        // Loop para as ações do jogador
        while (true) {
            System.out.println("Escolha uma ação: 1. Pedir carta  2. Parar");
            int escolha = scanner.nextInt();

            if (escolha == 1) { // Se escolher pedir carta
                jogador.receberCarta(baralho.distribuirCarta());
                System.out.println("Cartas do jogador: " + jogador.getMao());
                if (calcularPontuacao(jogador.getMao()) > 21) { // Se a pontuação do jogador estourar ＞﹏＜
                    System.out.println("Você estourou! Dealer vence."); // Que cara ruim, perdeu para um bot
                    return; // Sai do loop
                }
            } else if (escolha == 2) { // Se escolher parar
                break;
            } else { // Da para usar tratamento de exceção aqui @João \^o^/ (nao sei como)
                System.out.println("Escolha inválida.");
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

    @Override
    public String imprimir(double valorApostado) { // Método para imprimir o valor apostado
        return "Valor apostado: " + valorApostado; // Retorna o valor apostado
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
}