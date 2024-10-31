package Entidades;

public abstract class Usuario {
    private String nome;
    private String dataNascimento;

    public Usuario(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    protected String getNome() {
        return nome;
    }
}
