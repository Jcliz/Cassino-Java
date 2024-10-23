package Entidades;

public class Jogador extends Usuario {
    private double credito;

    public Jogador(String nome, String dataNascimento, double credito){
        super(nome, dataNascimento);
        this.credito = credito;
    }
}
