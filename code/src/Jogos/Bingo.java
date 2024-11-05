package Jogos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Bingo extends Jogo {
    private Set<Integer> numerosSorteados;
    private List<Cartela> jogadores;
    private double resultado;

    public Bingo(boolean estadoJogo) {
        super(estadoJogo);
        this.numerosSorteados = new HashSet<>();
        this.jogadores = new ArrayList<>();
    }

    public void sortearNumero() {
        Random random = new Random();
        int numero = random.nextInt(50) + 1;
        while (numerosSorteados.contains(numero)) {
            numero = random.nextInt(50) + 1;
        }
        numerosSorteados.add(numero);
        System.out.println("Número sorteado: " + numero);
        for (Cartela cartela : jogadores) {
            cartela.marcarNumero(numero);
        }
    }

    public boolean verificarVencedor(Cartela cartela) {
        return cartela.verificarCartelaCompleta(numerosSorteados);
    }

    public void adicionarJogador(Cartela cartela) {
        jogadores.add(cartela);
        super.adicionarJogador();
    }

    public void jogar(Scanner scanner) {
        iniciarJogo();
        while (super.getEstado()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Sortear próximo número? (s/n)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                sortearNumero();
                for (Cartela cartela : jogadores) {
                    cartela.imprimirCartela();
                }

                //Não foi utilizado o for each, pois, a variável i será utilizada para verificação
                for (int i = 0; i < jogadores.size(); i++) {
                    Cartela cartela = jogadores.get(i);

                    if (verificarVencedor(cartela)) {
                        if (i == 0) {
                            this.resultado = cartela.getResultado();
                        }

                        System.out.println(cartela.getNome() + " venceu!");
                        System.out.println("Valor ganho pelo jogador " + cartela.getNome() + ":" + cartela.getResultado());
                        cartela.imprimirCartela();
                        finalizarJogo();
                        break;
                    } else {
                        cartela.setResultado(0);
                    }
                }
            } else {
                finalizarJogo();
            }
        }
    }

    @Override
    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Partida finalizada! Obrigado por jogar conosco.");
    }

    @Override
    public void iniciarJogo () {
        super.setEstado(true);
        System.out.println("Bingo iniciado, preparem as suas cartelas!");
    }

    public String imprimir (double valorApostado) {
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Valor Apostado: " + valorApostado + "\n" +
                "Jogadores: " + jogadores + "\n" +
                "Numeros sorteados: " + numerosSorteados + "\n" +
                "Resultado do jogador 1 (em créditos): " + resultado + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }
}