package Jogos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CacaNiquel extends Jogo {

    private ArrayList<String> simbolos = new ArrayList<>(Arrays.asList(
            "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Cereja", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Limão", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Sino", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Joker", "Diamante", "Diamante", "Diamante", "Diamante", "Diamante", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer", "Lancer"
    ));
    private double saldo;

    public CacaNiquel(int numeroJogadores, boolean estadoDoJogo) {
        super(numeroJogadores, estadoDoJogo);
    }

    public void sortearSimbolos(double aposta) {
        Collections.shuffle(simbolos);
        String simbolo1 = simbolos.get(0);
        String simbolo2 = simbolos.get(1);
        String simbolo3 = simbolos.get(2);
        System.out.println(simbolo1);
        System.out.println(simbolo2);
        System.out.println(simbolo3);

        if (simbolo1.equals(simbolo2) && simbolo2.equals(simbolo3)) {
            switch (simbolo1) {
                case "Cereja":
                    System.out.println("Você acertou 3 Cerejas!");
                    saldo += 100;
                    break;
                case "Limão":
                    System.out.println("Você acertou 3 Limões!");
                    saldo += 300;
                    break;
                case "Uva":
                    System.out.println("Você acertou 3 Uvas!");
                    saldo += 500;
                    break;
                case "Melancia":
                    System.out.println("Você acertou 3 Melancias!");
                    saldo += 700;
                    break;
                case "Laranja":
                    System.out.println("Você acertou 3 Laranjas!");
                    saldo += 900;
                    break;
                case "Sino":
                    System.out.println("Você acertou 3 Sinhos!");
                    saldo += 1200;
                    break;
                case "BAR":
                    System.out.println("Você acertou 3 BARS!");
                    saldo += 2000;
                    break;
                case "Ferradura":
                    System.out.println("Você acertou 3 Ferraduras!");
                    saldo += 3000;
                    break;
                case "Estrela":
                    System.out.println("Você acertou 3 Estrelas!");
                    saldo += 4000;
                    break;
                case "Joker":
                    System.out.println("Você acertou 3 Jokers!");
                    saldo += 10000;
                    break;
                case "Diamante":
                    System.out.println("Você acertou 3 Diamantes!");
                    saldo += 25000;
                    break;
                case "Lancer":
                    System.out.println("Você acertou 3 Lancers!");
                    saldo += 100000;
                    break;
                default:
                    System.out.println("Três símbolos iguais, mas não reconhecidos.");
            }

        } else {
            System.out.println("A sequência não foi acertada. Boa sorte na próxima!");
        }
    }

    //FAZER LAÇO DE ITERAÇÃO EM RELAÇÃO A REPETIÇÕES NA OPERAÇÃO DE RODAR O CAÇA NÍQUEL

    @Override
    public void finalizarJogo() {
        super.setEstado(false);
        System.out.println("Finalizando sua jogatina na máquina de Caça Níqueis!");
    }

    @Override
    public void iniciarJogo() {
        super.setEstado(true);
        System.out.println("Iniciando o caça níquel!");
    }

    public double getSaldo() {
        return saldo;
    }

    public String imprimir (double valorApostado) {
        return "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n" +
                "Valor Apostado: " + valorApostado + "\n" +
                "Resultado: " + saldo + "\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + "\n";
    }


}


