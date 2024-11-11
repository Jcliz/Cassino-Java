package Jogos;

import Entidades.Jogador;
import Utilidades.ValorInvalidoException;

import java.util.Scanner;

public abstract class Jogo {
    private int numeroDeJogadores;
    private boolean estadoDoJogo; // true para em andamento, false para terminado

    public Jogo (int numeroDeJogadores, boolean estadoDoJogo) {
        this.numeroDeJogadores = numeroDeJogadores;
        this.estadoDoJogo = estadoDoJogo;
    }

    public Jogo (boolean estadoDoJogo) {
        this.estadoDoJogo = estadoDoJogo;
    }

    public abstract void iniciarJogo();

    public abstract void finalizarJogo();

    public abstract void jogar(Scanner leitor, Jogador jogador) throws ValorInvalidoException;

    public abstract String imprimir();

    public void adicionarJogador() {
        numeroDeJogadores++;
        System.out.println("Jogador adicionado. Total de jogadores: " + numeroDeJogadores);
    }

    public boolean getEstado () {
        return estadoDoJogo;
    }

    public void setEstado(boolean estadoDoJogo) {
        this.estadoDoJogo = estadoDoJogo;
    }

    public void sumNumeroDeJogadores() {
        numeroDeJogadores++;
    }

    public int getNumeroJogadores() {
        return numeroDeJogadores;
    }
}
