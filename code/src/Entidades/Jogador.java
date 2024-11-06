package Entidades;

import Jogos.Carta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jogador extends Usuario implements Serializable {
    private double credito;
    private List<Carta> mao;

    public Jogador(String nome, String dataNascimento, double credito) {
        super(nome, dataNascimento);
        this.credito = credito;
        this.mao = new ArrayList<>();
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public List<Carta> getMao() {
        return mao;
    }

    public String getNome() {
        return super.getNome();
    }
}