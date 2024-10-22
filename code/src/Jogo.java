public abstract class Jogo {
    protected int numeroDeJogadores;
    protected boolean estadoDoJogo; // true para em andamento, false para terminado

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
}
