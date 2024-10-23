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

    public Bingo() {
        super();
        this.numerosSorteados = new HashSet<>();
        this.jogadores = new ArrayList<>();
        this.numeroDeJogadores = 0;
        this.estadoDoJogo = false;
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
        while (estadoDoJogo) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Sortear próximo número? (s/n)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                sortearNumero();
                for (Cartela cartela : jogadores) {
                    cartela.imprimirCartela();
                }
                for (Cartela cartela : jogadores) {
                    if (verificarVencedor(cartela)) {
                        System.out.println(cartela.getNome() + " venceu!");
                        cartela.imprimirCartela();
                        finalizarJogo();
                        break;
                    }
                }
            } else {
                finalizarJogo();
            }
        }
    }
}