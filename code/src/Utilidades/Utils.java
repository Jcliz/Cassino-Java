package Utilidades;

import Entidades.Jogador;

import java.util.InputMismatchException;
import java.util.Scanner;

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

    public static double apostarRoleta (Jogador jogador, Scanner leitor) {
        try {
            System.out.println("Qual é o valor que você deseja apostar?:");
            double valor = leitor.nextDouble();

            if (Utils.verificarSaldoAposta(valor, jogador)) {
                jogador.retirarCreditos(valor);
                System.out.println("Aposta realizada com sucesso!" + "\n");
                return valor;

            } else {
                throw new SaldoInvalidoException("Saldo insuficiente, ou valor inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            leitor.nextLine();

        } catch (SaldoInvalidoException e) {
            System.out.println(e.getMessage());
        }
        return 0; //Aposta não realizada
    }
}
