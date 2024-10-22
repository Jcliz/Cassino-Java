import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Baralho extends Jogo {
    private List<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();
        for (Naipe naipe : Naipe.values()) {
            for (Valor valor : Valor.values()) {
                cartas.add(new Carta(naipe, valor));
            }
        }
        embaralhar();
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Carta distribuirCarta() {
        if (cartas.isEmpty()) {
            throw new IllegalStateException("O baralho está vazio");
        }
        return cartas.remove(cartas.size() - 1);
    }

    public int cartasRestantes() {
        return cartas.size();
    }

    public enum Naipe {
        COPAS, OUROS, ESPADAS, PAUS;
    }

    public enum Valor {
        DOIS(2), TRES(3), QUATRO(4), CINCO(5), SEIS(6), SETE(7), OITO(8), NOVE(9), DEZ(10), VALETE(10), DAMA(10), REI(10), AS(1);

        private final int valor;

        Valor(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

    public class Carta {
        private final Naipe naipe;
        private final Valor valor;

        public Carta(Naipe naipe, Valor valor) {
            this.naipe = naipe;
            this.valor = valor;
        }

        public Naipe getNaipe() {
            return naipe;
        }

        public Valor getValor() {
            return valor;
        }

        public int getValorParaBlackjack(boolean asComoOnze) {
            if (valor == Valor.AS) {
                return asComoOnze ? 11 : 1;
            }
            return valor.getValor();
        }

        @Override
        public String toString() {
            return valor + " de " + naipe;
        }
    }
}