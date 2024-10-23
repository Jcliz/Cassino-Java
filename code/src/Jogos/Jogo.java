package Jogos;

public abstract class Jogo {
    protected int numeroDeJogadores;
    protected boolean estadoDoJogo; // true para em andamento, false para terminado

    public void iniciarJogo() {
        estadoDoJogo = true;
        System.out.println("Jogos.Jogo iniciado.");
    }

    public void finalizarJogo() {
        estadoDoJogo = false;
        System.out.println("Jogos.Jogo finalizado.");
    }

    public void adicionarJogador() {
        numeroDeJogadores++;
        System.out.println("Package_Entidade.Jogador adicionado. Total de jogadores: " + numeroDeJogadores);
    }
}
