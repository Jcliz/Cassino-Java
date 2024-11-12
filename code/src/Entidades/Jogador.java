package Entidades;

import java.io.Serializable;

public class Jogador extends Usuario implements Serializable {
    private double creditos;

    public Jogador(String nome, int idade, double creditosIniciais) {
        super(nome, idade);
    }

    @Override
    public String toString() {
        return  "\n" + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Nome: " + super.getNome() + "\n" +
                "Idade: " + super.getIdade() + "\n" +
                "Carteira: " + this.creditos + " créditos" + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }

    public double getCreditos() {
        return creditos;
    }

    public void depositarCreditos(double valor) {
        this.creditos += valor;
    }

    public void retirarCreditos(double valor) {
        if (this.creditos >= valor) {
            this.creditos -= valor;
        }
    }
}