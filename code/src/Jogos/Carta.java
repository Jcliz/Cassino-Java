package Jogos;

public class Carta extends Baralho {
    protected String naipe;
    protected String valor;

    public Carta(String naipe, String valor) {
        super();
        this.naipe = naipe; // Define o naipe da carta para imprimir
        this.valor = valor; // Define o valor da carta para imprimir
    }

    public String getNaipe() {
        return naipe;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String imprimir(double valorApostado) {
        return "Carta: " + valor + " de " + naipe; // Retorna a carta no formato "Valor de Naipe" (ex: "A de Copas")
    }
}