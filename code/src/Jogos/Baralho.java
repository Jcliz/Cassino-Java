package Jogos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho extends Jogo {
    protected List<Carta> cartas; // Baralho do truco

    public Baralho() {
        super(1, false); // Não consegui fazer funcionar sem isso, culpa de vcs
        cartas = new ArrayList<>(); // Inicializa a lista de cartas
        String[] naipes = {"Copas", "Espadas", "Ouros", "Paus"}; // Define os naipes das cartas
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; // Define os valores das cartas

        // Loop para criar todas as combinações de naipes e valores
        for (String naipe : naipes) { // Para cada naipe (começa com "Copas") que vai de 0 a 3 (4 naipes)
            for (String valor : valores) { // Para cada valor (começa com "A") que vai de 0 a 12 (13 valores)
                cartas.add(new Carta(naipe, valor)); // Adiciona uma nova carta à lista (começa com "A de Copas")
            }
        }
        Collections.shuffle(cartas);// Embaralha as cartas
    }

    public Carta distribuirCarta() {
        return cartas.remove(0); // Remove a primeira carta do baralho e a retorna no "receberCarta"
    }

    @Override
    public String imprimir(double valorApostado) {
        return "Baralho com " + cartas.size() + " cartas restantes."; // Retorna a quantidade de cartas restantes no baralho
    }
}