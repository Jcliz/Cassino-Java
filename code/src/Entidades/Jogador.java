package Entidades;

import Jogos.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jogador extends Usuario implements Serializable {
    private double creditos;
    private List<Carta> mao;

    public Jogador(String nome, String dataNascimento, double creditosIniciais) {
        super(nome, dataNascimento);
        this.creditos = creditosIniciais;
        this.mao = new ArrayList<>();
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public List<Carta> getMao() {
        return mao;
    }

    public double getCreditos() {
        return creditos;
    }

    public void depositarCreditos(double valor) {
        this.creditos += valor;
    }

    public boolean retirarCreditos(double valor) {
        if (this.creditos >= valor) {
            this.creditos -= valor;
            return true;
        }
        return false;
    }
}