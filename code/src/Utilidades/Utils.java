package Utilidades;

import Entidades.Jogador;

public class Utils {
    public static String capitalize(String nome) {
        if (nome == null || nome.isEmpty()) {
            return nome;
        }

        String[] palavras = nome.split(" ");
        StringBuilder strBuilder = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                if (palavra.length() > 2) {
                    strBuilder.append(palavra.substring(0, 1).toUpperCase())
                            .append(palavra.substring(1).toLowerCase());
                } else {
                    strBuilder.append(palavra.toLowerCase());
                }
            }
            strBuilder.append(" ");
        }

        return strBuilder.toString().trim();
    }

    public static boolean verificarSaldoAposta (double saldo, Jogador jogador) {
        //if-else simplificado
        return saldo <= jogador.getCreditos() && saldo > 0;
    }
}
