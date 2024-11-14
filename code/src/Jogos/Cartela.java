package Jogos;

import java.util.*;

public class Cartela extends Bingo {
    protected String[][] cartela;
    protected String nome;
    protected double valor;

    public Cartela(boolean estadoDoJogo, String nome, double valor) {
        super(estadoDoJogo);
        this.nome = nome;
        this.cartela = gerarCartela();
        this.valor = valor;
    }

    protected String[][] gerarCartela() {
        String[][] cartela = new String[5][5];
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < 5; i++) {
            Set<Integer> numerosGerados = new HashSet<>();
            int[] coluna = new int[5];
            for (int j = 0; j < 5; j++) {
                int numero;
                do {
                    numero = random.nextInt(10) + i * 10;
                } while (numerosGerados.contains(numero));
                numerosGerados.add(numero);
                coluna[j] = numero;
            }
            Arrays.sort(coluna);
            for (int j = 0; j < 5; j++) {
                cartela[j][i] = String.valueOf(coluna[j]);
            }
        }
        return cartela;
    }

    public String imprimirCartela() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" + "Cartela de " + nome + ":\n");

        int contador = 0;
        for (String[] linha : cartela) {
            for (String numero : linha) {
                sb.append(String.format("%-5s", numero)); // Formata para 5 espaços
                contador++;

                // Após 5 números, adiciona uma quebra de linha
                if (contador % 5 == 0) {
                    sb.append("\n");
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public void marcarNumero(int numero) {
        String numStr = String.valueOf(numero);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cartela[i][j].equals(numStr)) {
                    cartela[i][j] = "X";
                }
            }
        }
    }

    public boolean verificarCartelaCompleta(Set<Integer> numerosSorteados) {
        imprimirCartela();
        for (String[] linha : cartela) {
            boolean linhaCompleta = true;
            for (String numero : linha) {
                if (!numero.equals("X") && !numerosSorteados.contains(Integer.parseInt(numero))) {
                    linhaCompleta = false;
                    break;
                }
            }
            if (linhaCompleta) {
                valor *= 2;
                return true;
            }
        }
        for (int col = 0; col < 5; col++) {
            boolean colunaCompleta = true;
            for (String[] linha : cartela) {
                if (!linha[col].equals("X") && !numerosSorteados.contains(Integer.parseInt(linha[col]))) {
                    colunaCompleta = false;
                    break;
                }
            }
            if (colunaCompleta) {
                valor *= 2;
                return true;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public double getResultado() {
        return valor;
    }
}