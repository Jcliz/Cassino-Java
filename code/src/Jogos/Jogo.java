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

    public abstract void iniciarJogo();

    public abstract void finalizarJogo();

    public void adicionarJogador() {
        numeroDeJogadores++;
        System.out.println("Jogador adicionado. Total de jogadores: " + numeroDeJogadores);
    }

    public abstract String imprimir(double valorApostado);

    public boolean getEstado () {
        return estadoDoJogo;
    }

    public void setEstado(boolean estadoDoJogo) {
        this.estadoDoJogo = estadoDoJogo;
    }
}
