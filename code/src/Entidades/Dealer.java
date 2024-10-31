package Entidades;

import Jogos.Carta;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Bot {
    private List<Carta> mao;

    public Dealer() {
        super();
        this.mao = new ArrayList<>();
    }

    @Override
    public void executarAcao() {
        // Implement the dealer's action logic here
        System.out.println("Dealer está executando uma ação.");
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public List<Carta> getMao() {
        return mao;
    }

    public String getNome() {
        return "Dealer";
    }
}