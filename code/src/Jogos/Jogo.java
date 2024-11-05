package Jogos;

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

    public void iniciarJogo() {
        estadoDoJogo = true;
        System.out.println("Jogo iniciado.");
    }

    public void finalizarJogo() {
        estadoDoJogo = false;
        System.out.println("Jogo finalizado.");
    }

    public void adicionarJogador() {
        numeroDeJogadores++;
        System.out.println("Jogador adicionado. Total de jogadores: " + numeroDeJogadores);
    }

    public abstract String imprimir(double valorApostado);

    public boolean getEstado () {
        return estadoDoJogo;
    }
}
