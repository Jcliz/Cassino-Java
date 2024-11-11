package Jogos;

import Entidades.Dealer;
import Entidades.Jogador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho extends Blackjack {
    protected List<Carta> cartas; // Baralho do truco

    public Baralho(ArrayList<Carta> cartas, Jogador jogador, Dealer dealer) {
        super(jogador, dealer);
        this.cartas = cartas;
    }

    public Baralho () {
        super(false);
    }

    public Carta distribuirCarta() {
        return cartas.removeFirst(); // Remove a primeira carta do baralho e a retorna no "receberCarta"
    }

    @Override
    public String imprimir() {
        return "Baralho com " + cartas.size() + " cartas restantes."; // Retorna a quantidade de cartas restantes no baralho
    }
}