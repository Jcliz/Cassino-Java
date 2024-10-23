package Entidades;

import Jogos.Baralho;

public class Dealer extends Bot {
    public Dealer() {
        super();
    }

    @Override
    public void executarAcao() {
        // Implementar lógica do dealer para executar uma ação
        System.out.println("Entidades.Dealer está executando uma ação.");
    }

    public void distribuirCartas(Baralho baralho) {
        // Implementar lógica do dealer para distribuir cartas
        System.out.println("Entidades.Dealer está distribuindo cartas.");
    }
}