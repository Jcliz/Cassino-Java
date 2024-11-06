package Entidades;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
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
